package com.siti.wisdomhydrologic.operation.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ManageDataMantainService;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    @Resource
    private RedisBiz redisBiz;
    @Resource
    private UserMapper userMapper;

    public PageInfo<ReportManageDataMantain> getByCreateDate(int page, int pageSize, String stationId, String alterType, String createDate, HttpSession session) {

        User user = (User) redisBiz.get(session.getId());
        List<Org> orgList = userMapper.findOrg(user.getId());
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        PageHelper.startPage(page, pageSize);
        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(stationId, alterType, createDate, orgList.get(0).getId());
        List<ConfigAbnormalDictionary> list1 = configAbnormalDictionaryMapper.getList();

        /**
         * 把页面查询的依据id 替换成依据内容
         * */
        list.forEach(data -> {
            list1.forEach(data2 -> {
                if (data2.getBrokenAccordingId().equals(data.getBrokenAccordingId())) {
                    data.setBrokenAccordingId(data2.getBrokenAccording());
                }
            });
        });

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
        /*reportManageDataMantain.setAlterDate(DateOrTimeTrans.Date2TimeString(date));
        if (reportManageDataMantain.getConfirValue() != null && (!"".equals(reportManageDataMantain.getConfirValue()))) {
            reportManageDataMantain.setErrorDataReRun(1);
        }
        if (reportManageDataMantain.getMissDataType() != null && (!"".equals(reportManageDataMantain.getMissDataType()))) {
            reportManageDataMantain.setMissDataReRun(1);
        }*/
        String createTime = reportManageDataMantain.getCreateTime();
        String errorLastestAppearTime = reportManageDataMantain.getErrorLastestAppearTime();

        if (errorLastestAppearTime != null) {
            reportManageDataMantain.setErrorTimeSpace(createTime + "," + errorLastestAppearTime);
        } else {
            reportManageDataMantain.setErrorTimeSpace(createTime);
        }

        System.out.println("修改后的ReportManageDataMantain:" + reportManageDataMantain);

        try {
            int result = reportManageDataMantainMapper.update(reportManageDataMantain);
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int insertAbnormalData(String date) {
        List<ConfigAbnormalDictionary> dictionarylist = configAbnormalDictionaryMapper.getList();
        List<ReportManageDataMantainVo> all = abnormalDetailMapper.getAllTable2Data();
        List<ConfigSensorSectionModule> moduleList = configSensorSectionModuleMapper.getStation();
        List<ReportManageDataMantainVo> abnormalall = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (all.size() > 0) {
            //获取异常配置参数
            all.forEach(abData -> {
                String errorType = abData.getDataError().split("_")[0];
                if ("data".equals(errorType)) {
                    abData.setBrokenAccordingId(abData.getDataError());

                    /**
                     * 查询上次5分钟内的数据表中是否包含这次测站的这个异常
                     * */
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateTransform.String2Date(abData.getDate(), "yyyy-MM-dd HH:mm:ss"));
                    cal.add(cal.MINUTE, -5);
                    String last5MinuteTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

                    /**
                     * 查询上个五分钟的异常表数据。
                     * */
                    List<AbnormalDetailEntity> latestAbnormalData = abnormalDetailMapper.getLatestData(last5MinuteTime, abData.getSensorCode());

                    //赋值测站号和修改日期
                    abData.setStationCode(abData.getSensorCode() / 100);
                    abData.setAlterSensorTypeId(abData.getSectionCode() % 100);
                    abData.setCreateTime(abData.getDate());

                    /**
                     * 查询数据表二,是否有数据的最后一次出现时间 = 异常表上个5分钟的时间,
                     * 若有，更新最后一次生成时间
                     * */
                    if (latestAbnormalData.size() > 0) {
                        try {
                            int stationId = abData.getStationCode();
                            int sensorTypeId = abData.getAlterSensorTypeId();
                            List<ReportManageDataMantain> latestData = reportManageDataMantainMapper.getLastestData( stationId,sensorTypeId,last5MinuteTime);
                            if (latestData.size() > 0) {
                                //修改最后出现时间
                                abData.setErrorLastestAppearTime(abData.getCreateTime());
                                //修改错误时间段
                                abData.setErrorTimeSpace(latestAbnormalData.get(0).getDate() + "," + abData.getCreateTime());
                                abData.setReportId(latestData.get(0).getReportId());
                                reportManageDataMantainMapper.updateTime(abData);
                                //System.out.println(abData.getStationName() + "的异常" + abData.getBrokenAccordingId() + "表二数据错误时间更替" + abData.getErrorLastestAppearTime());
                            } else {
                                //根据字典获取异常名
                                dictionarylist.forEach(param -> {
                                    if (param.getBrokenAccordingId().equals(abData.getBrokenAccordingId())) {
                                        abData.setErrorDataReason(param.getErrorName());
                                        abData.setBrokenAccordingId(param.getBrokenAccordingId());
                                        abData.setErrorDataType(param.getErrorDataId());
                                        //修改日期添加时精确到某日
                                        abData.setAlterDate(abData.getCreateTime().substring(0, 10));
                                        abData.setErrorLastestAppearTime(abData.getCreateTime());
                                        //状态为实时或小时时,错误时段为时间段
                                        //实时时段精度到时分秒
                                        if (abData.getErrorDataType() == 1) {
                                            abData.setErrorTimeSpace(abData.getCreateTime() + "," + abData.getCreateTime());
                                        }//小时时段精度到时
                                        else if (abData.getErrorDataType() == 3) {
                                            abData.setErrorTimeSpace(abData.getCreateTime().substring(0, 13) + "," + abData.getCreateTime().substring(0, 13));
                                        } else {
                                            //5分钟,一天异常为单个时间
                                            abData.setErrorTimeSpace(abData.getCreateTime());
                                        }
                                        abData.setErrorDataReRun(0);
                                        abData.setMissDataReRun(0);
                                    }
                                });
                                //结合module表添加测站参数
                                moduleList.forEach(module -> {
                                    if (module.getSectionCode() == abData.getSectionCode()) {
                                        abData.setAlterSensorTypeName(module.getSectionName().substring((module.getSectionName().length() - 2), module.getSectionName().length()));
                                        abData.setAlterSensorTypeId(module.getSectionCode() % 100);
                                        abData.setStationName(module.getStationName());
                                    }
                                });
                                abnormalall.add(abData);
                            }
                        } catch (Exception e) {
                            System.out.println("表二数据添加异常");
                        }
                    }
                }
                list.add(abData.getId());
            });
            abnormalDetailMapper.updateTable2Status(list);
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
        try {
            return reportManageDataMantainMapper.insert(reportManageDataMantain);
        } catch (Exception e) {
            return 0;
        }
    }
}
