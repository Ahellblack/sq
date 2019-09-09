package com.siti.wisdomhydrologic.operation.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.ManageApplicationBrokenService;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class ManageApplicationBrokenServiceImpl implements ManageApplicationBrokenService {
    @Resource
    private ManageApplicationBrokenMapper reportManageApplicationBrokenMapper;
    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    private static final Logger logger = LoggerFactory.getLogger(ManageApplicationBrokenServiceImpl.class);


    private static final int STATUS = 2;

    public PageInfo<ReportManageApplicationBroken> getAll(int page, int pageSize, String createDate, String stationName) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        PageHelper.startPage(page, pageSize);
        List<ReportManageApplicationBroken> all = reportManageApplicationBrokenMapper.getAll(createDate, stationName);
        /*all.forEach(data -> {
            try {
                if (data.getCreateTime() != null && data.getCreateTime().length() >= 13)
                    data.setCreateTime(data.getCreateTime().substring(0, 13));
                if (data.getBrokenrRequestReportTime() != null && data.getBrokenrRequestReportTime().length() >= 13)
                    data.setBrokenrRequestReportTime(data.getBrokenrRequestReportTime().substring(0, 13));
                if (data.getBrokenAskToResolveTime() != null && data.getBrokenAskToResolveTime().length() >= 13)
                    data.setBrokenAskToResolveTime(data.getBrokenAskToResolveTime().substring(0, 13));
                if (data.getBrokenResolveTime() != null && data.getBrokenResolveTime().length() >= 13)
                    data.setBrokenResolveTime(data.getBrokenResolveTime().substring(0, 13));
                if (data.getRequestDesignatingTime() != null && data.getRequestDesignatingTime().length() >= 13)
                    data.setRequestDesignatingTime(data.getRequestDesignatingTime().substring(0, 13));
                if (data.getBrokenResponseTime() != null && data.getBrokenResponseTime().length() >= 13)
                    data.setBrokenResponseTime(data.getBrokenResponseTime().substring(0, 13));
            } catch (Exception e) {
                e.printStackTrace();
            }//data.setBrokenResolveTime(data.getBrokenResolveTime().substring(0,13));
        });*/

        return new PageInfo<>(all);
    }

    @Override
    public int insert(ReportManageApplicationBroken reportManageApplicationBroken) {
        reportManageApplicationBroken.setRequestDesignatingStatus(1);
        return reportManageApplicationBrokenMapper.insert(reportManageApplicationBroken);
    }

    @Override
    public int update(ReportManageApplicationBroken reportManageApplicationBroken) {

        reportManageApplicationBroken.setRequestDesignatingStatus(1);
        if (reportManageApplicationBroken.getRequestDesignatingStatus() == STATUS) {
            //派单处理
            LocalTime localTime = LocalTime.now();
            LocalDate localDate = LocalDate.now();
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zone).toInstant();
            Date date = Date.from(instant);
            reportManageApplicationBroken.setRequestDesignatingTime(DateTransform.Date2String(date, "YYYY-MM-dd HH:mm:ss"));
        }

        return reportManageApplicationBrokenMapper.update(reportManageApplicationBroken);
    }


    public int updateMalStatus(ReportManageApplicationBroken reportManageApplicationBroken, Integer status) {
        reportManageApplicationBroken.setRequestDesignatingStatus(status);
        return reportManageApplicationBrokenMapper.update(reportManageApplicationBroken);
    }

    @Override
    public int delete(Integer reportId) {
        return reportManageApplicationBrokenMapper.deleteById(reportId);
    }


    /**
     * 根据日期获取异常
     */
    @Override
    public int insertDataMantain(String date) {
        /*Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(DateTransform.String2Date(date, "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
        }
        *//**
         * 查询上一个整5分再往前5分钟数据
         * *//*
        cal.add(cal.MINUTE, -5);

        date = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
*/

        List<ConfigAbnormalDictionary> list = configAbnormalDictionaryMapper.getList();
        //根据日期获取异常信息
        List<ReportManageDataMantainVo> all = abnormalDetailMapper.getALL(date);
        List<ConfigSensorSectionModule> moduleList = configSensorSectionModuleMapper.getStation();
        List<ConfigRiverStation> riverStationList = configRiverStationMapper.getAll();
        List<ReportManageApplicationBroken> brokenList = new ArrayList();
        //获取异常配置参数
        if (all.size() > 0) {

            all.forEach(data -> {
                Calendar calendar = Calendar.getInstance();
                ReportManageApplicationBroken applicationBroken = new ReportManageApplicationBroken();
                //异常包含设备异常或数据异常的一种
                if (data.getEquipmentError() != null) {
                    applicationBroken.setBrokenAccordingId(data.getEquipmentError());
                } else if (data.getDataError() != null) {
                    applicationBroken.setBrokenAccordingId(data.getDataError());
                }
                if (applicationBroken.getBrokenAccordingId() != null) {
                    //结合module表添加测站参数
                    moduleList.forEach(module -> {
                        if (module.getSectionCode() == data.getSectionCode()) {
                            applicationBroken.setStationId(module.getStationCode());
                            applicationBroken.setStationName(module.getStationName());
                        }
                    });

                    try {
                        riverStationList.forEach(river -> {
                            if (data.getStationCode() == river.getStationId()) {
                                calendar.setTime(DateTransform.String2Date(data.getDate(), "yyyy-MM-dd HH:mm:ss"));
                                if (river.getStationLevel() == 2) {
                                    //一般站往后3小时内
                                    calendar.add(calendar.HOUR, 3);
                                } else {
                                    //基本站往后1小时内
                                    calendar.add(calendar.HOUR, 1);
                                }
                                applicationBroken.setBrokenResponseTime(DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                            }
                            river.getStationId();
                        });
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                        /*
                        List<String> basicStationList = StationIdUtils.getBasicStationList();
                        //如果包含基本站名,判断为基本站
                        if (basicStationList.contains(data.getStationName())) {
                            calendar.setTime(DateTransform.String2Date(data.getDate(), "yyyy-MM-dd HH:mm:ss"));
                            //基本站往后1小时内
                            calendar.add(calendar.HOUR, 1);
                            applicationBroken.setBrokenResponseTime(calendar.getTime());
                        } else {
                            calendar.setTime(DateTransform.String2Date(data.getDate(), "yyyy-MM-dd HH:mm:ss"));
                            //一般站往后3小时内
                            calendar.add(calendar.HOUR, 3);
                            applicationBroken.setBrokenResponseTime(calendar.getTime());
                        }*/
                    list.forEach(e -> {
                        //根据字典赋值故障判断依据和故障名称
                        if (e.getBrokenAccordingId().equals(applicationBroken.getBrokenAccordingId())) {
                            applicationBroken.setBrokenAccording(e.getBrokenAccording());
                            applicationBroken.setBrokenName(e.getErrorName());
                        }
                    });
                    applicationBroken.setCreateTime(data.getDate());
                    /*List<AbnormalDetailEntity> getLatestData = abnormalDetailMapper.getLatestData(DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"), data.getSectionCode());
                     getLatestData.forEach(abnormal -> {
                       String according_id = applicationBroken.getBrokenAccordingId();
                        if (!(according_id == null && "".equals(according_id))) {
                            //String eq_error = abnormal.getEquipmentError();
                            //String data_error = abnormal.getDateError();
                            //if (!(according_id.equals(eq_error)||according_id.equals(data_error))) {
                            //}
                        }
                    });*/
                    brokenList.add(applicationBroken);
                }
            });

            int size = 1000;
            int allsize = brokenList.size();
            int cycle = allsize % size == 0 ? allsize / size : (allsize / size + 1);
            IntStream.range(0, cycle).forEach(e -> {
                if (allsize > 0) {
                    reportManageApplicationBrokenMapper.insertDataMantain(brokenList.subList(e * size, (e + 1) * size > allsize ? allsize : size * (e + 1)));
                }
            });
            return allsize;
        } else {
            return 0;
        }
    }
}

