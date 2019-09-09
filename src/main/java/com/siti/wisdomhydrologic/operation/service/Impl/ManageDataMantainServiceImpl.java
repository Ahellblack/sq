package com.siti.wisdomhydrologic.operation.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ManageDataMantainService;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by dell on 2019/7/30.
 */
@Service
public class ManageDataMantainServiceImpl implements ManageDataMantainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ManageDataMantainMapper reportManageDataMantainMapper;
    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;
    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    //LogOperation log;

    public PageInfo<ReportManageDataMantain> getByCreateDate(int page, int pageSize, String stationName, String alterType, String createDate) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        PageHelper.startPage(page, pageSize);
        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(stationName, alterType, createDate);

        /*list.forEach(data -> {
            if (data.getCreateTime() != null && data.getCreateTime().length() >= 13)
                data.setCreateTime(data.getCreateTime().substring(0, 13));
            if (data.getMissTimeSpace() != null && data.getMissTimeSpace().length() >= 13)
                data.setMissTimeSpace(data.getMissTimeSpace().substring(0, 13));
            if (data.getErrorTimeSpace() != null && data.getErrorTimeSpace().length() >= 13)
                data.setErrorTimeSpace(data.getErrorTimeSpace().substring(0, 13));
        });*/
        return new PageInfo<ReportManageDataMantain>(list);
    }

    @Override
    public int delete(Integer reportId) {
        return reportManageDataMantainMapper.deleteByReportId(reportId);
    }

    @Override
    public int update(ReportManageDataMantain reportManageDataMantain) {
        //数据发生修改时,altertime数据更新为当前时间
        Date date = new Date();
        //格式为YYYY-MM-dd
        reportManageDataMantain.setAlterDate(DateOrTimeTrans.Date2TimeString(date));
        if (reportManageDataMantain.getConfirValue() != null) {
            reportManageDataMantain.setErrorDataReRun(1);
        }
        if (reportManageDataMantain.getMissDataType() != null && reportManageDataMantain.getMissTimeSpace() != null) {
            reportManageDataMantain.setMissDataReRun(1);
        }
        System.out.println("修改后的ReportManageDataMantain：" + reportManageDataMantain);
        int result = reportManageDataMantainMapper.update(reportManageDataMantain);
        return result;
    }

    @Override
    public int insertAbnormalData(String date) {
        List<ConfigAbnormalDictionary> dictionarylist = configAbnormalDictionaryMapper.getList();
        List<ReportManageDataMantainVo> all = abnormalDetailMapper.getALL(date);
        List<ConfigSensorSectionModule> moduleList = configSensorSectionModuleMapper.getStation();
        List<ReportManageDataMantainVo> abnormalall = new ArrayList<>();
        if (all.size() > 0) {
            //获取异常配置参数
            all.forEach(abnormalData -> {
                if (abnormalData.getDataError() != null) {
                    abnormalData.setBrokenAccordingId(abnormalData.getDataError());
                    //赋值测站号和修改日期
                    abnormalData.setStationCode(abnormalData.getSensorCode() / 100);
                    abnormalData.setCreateTime(abnormalData.getDate());
                    /**
                     * 查询上次5分钟内的数据表中是否包含这次测站的这个异常
                     * */
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateTransform.String2Date(abnormalData.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    cal.add(cal.MINUTE, -5);
                    String last5MinuteTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

                    if (reportManageDataMantainMapper.getLastOne(abnormalData.getStationCode(), last5MinuteTime).size() > 0) {
                        abnormalData.setErrorLastestAppearTime(abnormalData.getCreateTime());
                        abnormalData.setErrorTimeSpace(reportManageDataMantainMapper.getLastOne(abnormalData.getStationCode(), last5MinuteTime).get(0).getCreateTime() + "," + abnormalData.getCreateTime());
                        reportManageDataMantainMapper.updateTime(abnormalData);
                    } else {
                        //根据字典获取异常名
                        dictionarylist.forEach(param -> {
                            if (param.getBrokenAccordingId().equals(abnormalData.getBrokenAccordingId())) {
                                abnormalData.setErrorDataReason(param.getErrorName());
                                abnormalData.setBrokenAccordingId(param.getBrokenAccordingId());
                                abnormalData.setErrorDataType(param.getErrorDataId());
                                //修改日期添加时精确到某日
                                abnormalData.setAlterDate(abnormalData.getCreateTime().substring(0, 10));
                                abnormalData.setErrorLastestAppearTime(abnormalData.getCreateTime().substring(0, 10));
                                abnormalData.setErrorTimeSpace(abnormalData.getCreateTime().substring(0, 13)+","+abnormalData.getCreateTime().substring(0, 13));
                                abnormalData.setErrorDataReRun(0);
                                abnormalData.setMissDataReRun(0);
                            }
                        });
                        //结合module表添加测站参数
                        moduleList.forEach(module -> {
                            if (module.getSectionCode() == abnormalData.getSectionCode()) {
                                abnormalData.setAlterSensorTypeName(module.getSectionName().substring((module.getSectionName().length() - 2), module.getSectionName().length()));
                                abnormalData.setAlterSensorTypeId(module.getSectionCode() % 100);
                                abnormalData.setStationName(module.getStationName());
                            }
                        });
                        abnormalall.add(abnormalData);
                    }
                }
            });
            int size = 1000;
            int allsize = abnormalall.size();
            int cycle = allsize % size == 0 ? allsize / size : (allsize / size + 1);
            IntStream.range(0, cycle).forEach(e -> {
                reportManageDataMantainMapper.insertAbnormal(abnormalall.subList(e * size, (e + 1) * size > allsize ? allsize : size * (e + 1)));
            });
            return allsize;
        } else {
            return 0;
        }
    }

    public int insert(ReportManageDataMantain reportManageDataMantain) {
        System.out.println(reportManageDataMantain);
        return reportManageDataMantainMapper.insert(reportManageDataMantain);
    }
}
