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
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
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
        reportManageDataMantain.setAlterDate(DateOrTimeTrans.Date2TimeString(date));
        if (reportManageDataMantain.getConfirValue() != null) {
            reportManageDataMantain.setErrorDataReRun(1);
        }
        if (reportManageDataMantain.getMissDataType() != null && reportManageDataMantain.getMissTimeSpace() != null) {
            reportManageDataMantain.setMissDataReRun(1);
        }
        String createTime = reportManageDataMantain.getCreateTime();
        String errorLastestAppearTime = reportManageDataMantain.getErrorLastestAppearTime();

        if (errorLastestAppearTime!=null){
            reportManageDataMantain.setErrorTimeSpace(createTime+","+errorLastestAppearTime);
        }else {
            reportManageDataMantain.setErrorTimeSpace(createTime);
        }

        System.out.println("修改后的ReportManageDataMantain：" + reportManageDataMantain);

        try{
            int result = reportManageDataMantainMapper.update(reportManageDataMantain);
            return result;
        }catch (Exception e){
            return 0;
        }
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

                    /**
                     * 查询上次5分钟内的数据表中是否包含这次测站的这个异常
                     * */
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateTransform.String2Date(abnormalData.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    cal.add(cal.MINUTE, -5);
                    String last5MinuteTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                   // List<ReportManageDataMantain> lastOne = reportManageDataMantainMapper.getLastOne(abnormalData.getStationCode(), last5MinuteTime);

                    /**
                     * 查询上个五分钟的异常表数据。
                     * */
                    List<AbnormalDetailEntity> latestData = abnormalDetailMapper.getLatestData(last5MinuteTime, abnormalData.getSensorCode());

                    //赋值测站号和修改日期
                    abnormalData.setStationCode(abnormalData.getSensorCode() / 100);
                    abnormalData.setCreateTime(abnormalData.getDate());

                    if (latestData.size() > 0) {
                        /**
                         * 查询数据表二,是否有数据的最后一次出现时间 = 异常表上个5分钟的时间,
                         * 若有，更新最后一次生成时间
                         * */
                        ReportManageDataMantain lastestData = reportManageDataMantainMapper.getLastestData(abnormalData.getSectionCode(), last5MinuteTime);

                        abnormalData.setErrorLastestAppearTime(abnormalData.getCreateTime());
                        abnormalData.setErrorTimeSpace(lastestData.getCreateTime().substring(0,13) + "," + abnormalData.getCreateTime().substring(0,13));
                        abnormalData.setReportId(lastestData.getReportId());
                        reportManageDataMantainMapper.updateTime(abnormalData);
                        System.out.println(abnormalData.getStationName()+"的异常"+abnormalData.getBrokenAccordingId()+"表二数据错误时间更替" + abnormalData.getErrorLastestAppearTime());
                    } else {
                        //根据字典获取异常名
                        dictionarylist.forEach(param -> {
                            if (param.getBrokenAccordingId().equals(abnormalData.getBrokenAccordingId())) {
                                abnormalData.setErrorDataReason(param.getErrorName());
                                abnormalData.setBrokenAccordingId(param.getBrokenAccordingId());
                                abnormalData.setErrorDataType(param.getErrorDataId());
                                //修改日期添加时精确到某日
                                abnormalData.setAlterDate(abnormalData.getCreateTime().substring(0, 10));
                                abnormalData.setErrorLastestAppearTime(abnormalData.getCreateTime());
                                //状态为实时或小时时,错误时段为时间段
                                if (abnormalData.getErrorDataType() == 1 || abnormalData.getErrorDataType() == 3) {
                                    abnormalData.setErrorTimeSpace(abnormalData.getCreateTime().substring(0, 13) + "," + abnormalData.getCreateTime().substring(0, 13));
                                } else {
                                    abnormalData.setErrorTimeSpace(abnormalData.getCreateTime());
                                }
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
        try{
            return reportManageDataMantainMapper.insert(reportManageDataMantain);
        }catch (Exception e){
            return 0;
        }
    }
}
