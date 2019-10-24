package com.siti.wisdomhydrologic.operation.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.configmaintain.entity.ModuleAndStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.entity.AbnormalDetailCurrent;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.AbnormalDetailCurrentMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ManageDataMantainService;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
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
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;
    @Resource
    AbnormalDetailCurrentMapper abnormalDetailCurrentMapper;


    public PageInfo<ReportManageDataMantain> getByCreateDate(int page, int pageSize, String stationId, String alterType, String createDate, HttpSession session) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        PageHelper.startPage(page, pageSize);
        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(stationId, alterType, createDate, orgList.get(0).getId(), 1);

        /**
         * 把页面查询的依据id 替换成依据内容
         * */
     /*   list.forEach(data -> {
            data.setBrokenAccordingId(data.getDescription());
        });
*/
        return new PageInfo<ReportManageDataMantain>(list);
    }

    public PageInfo<ReportManageDataMantain> getDisplayByCreateDate(int page, int pageSize, String stationId, String alterType, String createDate) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        PageHelper.startPage(page, pageSize);
        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(stationId, alterType, createDate, orgList.get(0).getId(), null);
        List<ConfigAbnormalDictionary> list1 = configAbnormalDictionaryMapper.getList();

        /**
         * 把页面查询的依据id 替换成依据内容
         * */
        list.forEach(data -> {
            data.setBrokenAccordingId(data.getDescription());
        });

        return new PageInfo<ReportManageDataMantain>(list);
    }


    @Override
    public int delete(Integer reportId) {
        return reportManageDataMantainMapper.updateDisplayStatus(reportId);
    }

    public int insert(ReportManageDataMantain reportManageDataMantain) {
        System.out.println(reportManageDataMantain);
        try {
            return reportManageDataMantainMapper.insert(reportManageDataMantain);
        } catch (Exception e) {
            return 0;
        }
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


    public int insertAbnormalData() {

        List<ModuleAndStation> moduleList = configSensorSectionModuleMapper.getStationAndModule();
        List<AbnormalDetailCurrent> lastest = abnormalDetailCurrentMapper.get2Lastest();
        List<ReportManageDataMantainVo> brokenList = new ArrayList();
        List<Integer> idList = new ArrayList<>();
        if (lastest.size() > 0) {
            try {
                lastest.forEach(data -> idList.add(data.getId()));
                List<ReportManageDataMantainVo> histroyList = reportManageDataMantainMapper.getById(idList);
                if (histroyList.size() > 0) {
                    histroyList.forEach(data -> {
                        lastest.forEach(lasttime -> {
                            if (data.getReportId() == lasttime.getId()) {
                                String errorSpace = data.getCreateTime() + "," + lasttime.getLastDate();
                                data.setErrorTimeSpace(errorSpace);
                                data.setErrorLastestAppearTime(lasttime.getLastDate());
                                reportManageDataMantainMapper.updateTime(data);
                                abnormalDetailCurrentMapper.update2Status(lasttime.getId());
                                //System.out.println("表二数据错误时间更替" + lasttime.getLastDate());
                            }
                        });
                    });
                }
                List<AbnormalDetailCurrent> newlastest = abnormalDetailCurrentMapper.get2Lastest();
                if (newlastest.size() > 0) {
                    List<Integer> idList2 = new ArrayList<>();
                    newlastest.forEach(data -> {
                        idList2.add(data.getId());
                    });
                    newlastest.forEach(data -> {
                        //只拉取数据异常
                        String errorType = "";
                        if (data.getDataError() != null) {
                            errorType = data.getDataError().split("_")[0];
                        }
                        if ("data".equals(errorType) || "md".equals(errorType)) {

                            ReportManageDataMantainVo entity = new ReportManageDataMantainVo();
                            entity.setReportId(data.getId());
                            entity.setBrokenAccordingId(data.getBrokenAccordingId());
                            entity.setErrorDataReason(data.getErrorName());
                            entity.setBrokenAccordingId(data.getBrokenAccordingId());
                            entity.setErrorDataType(data.getErrorDataId());

                            entity.setCreateTime(data.getDate());
                            entity.setErrorDataReRun(0);
                            entity.setMissDataReRun(0);
                            entity.setErrorLastestAppearTime(data.getLastDate());
                            //修改日期添加时精确到某日
                            entity.setAlterDate(entity.getCreateTime().substring(0, 10));
                            entity.setErrorLastestAppearTime(entity.getCreateTime());
                            //状态为实时或小时时,错误时段为时间段
                            //实时时段精度到时分秒
                            if (entity.getErrorDataType() == 1) {
                                entity.setErrorTimeSpace(entity.getCreateTime() + "," + entity.getCreateTime());
                            }//小时时段精度到时
                            else if (entity.getErrorDataType() == 3) {
                                entity.setErrorTimeSpace(entity.getCreateTime().substring(0, 13) + "," + entity.getCreateTime().substring(0, 13));
                            } else {
                                //5分钟,一天异常为单个时间
                                entity.setErrorTimeSpace(entity.getCreateTime());
                            }
                            moduleList.forEach(module -> {

                                if (data.getSensorCode() == module.getSectionCode()) {
                                    entity.setStationName(module.getStationName());
                                    entity.setStationCode(module.getStationId());
                                    entity.setAlterSensorTypeName(module.getSectionName().substring((module.getSectionName().length() - 2), module.getSectionName().length()));
                                    entity.setAlterSensorTypeId(module.getSectionCode() % 100);
                                }
                            });
                            entity.setErrorValue(data.getErrorValue() + "");
                            entity.setDescription(data.getDescription());
                            brokenList.add(entity);
                        }
                    });
                    //修改状态
                    abnormalDetailCurrentMapper.update2StatusList(idList2);
                }
                int allsize = brokenList.size();
                brokenList.forEach(data -> {

                    reportManageDataMantainMapper.insertAbnormal(data);

                });

                return allsize;
            } catch (Exception e) {
                System.out.println("数据插入异常");
            }
        }else {
            return 0;
        }
        return 0;
    }
}

/*

        List<ConfigAbnormalDictionary> dictionarylist = configAbnormalDictionaryMapper.getList();
        List<ReportManageDataMantainVo> all = abnormalDetailMapper.getAllTable2Data();
        List<ConfigSensorSectionModule> moduleList = configSensorSectionModuleMapper.getStation();
        List<ReportManageDataMantainVo> abnormalall = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        */
/**
 * 一次数据拉取剔除重复
 * *//*

        if (all.size() > 0) {
            //新建拷贝筛选队列
            List<ReportManageDataMantainVo> ALLLIST = all;
            all.forEach(data->{
                list.add(data.getId());
            });
            outer:
            for (int i = 0; i < ALLLIST.size(); i++) {
                String Iaid = ALLLIST.get(i).getDataError();
                Integer Isid = ALLLIST.get(i).getSensorCode();
                Date Idate = DateTransform.String2Date(ALLLIST.get(i).getDate(), "yyyy-MM-dd HH:mm:ss");
                for (int j = i+1; j < ALLLIST.size(); j++) {
                    String Jaid = ALLLIST.get(j).getDataError();
                    Integer Jsid = ALLLIST.get(j).getSensorCode();
                    Date Jdate = DateTransform.String2Date(ALLLIST.get(j).getDate(), "yyyy-MM-dd HH:mm:ss");
                    //同一测站同一异常时,判断
                    if (Iaid.equals(Jaid) && Isid.equals(Jsid)) {
                        //如果第i个的时间在第j个之后，替换时间
                        if (Idate.after(Jdate)) {
                            //如果时间差超过24小时，不删除数据
                            if (Math.abs(Idate.getTime() - Jdate.getTime()) <= ((1000 * 60 * 60) * 24)) {
                                ALLLIST.remove(j);
                                i--;
                                continue outer;
                            }
                        } else {
                            //如果时间差超过24小时，不删除数据
                            if (Math.abs(Idate.getTime() - Jdate.getTime()) <= ((1000 * 60 * 60) * 24)) {
                                ALLLIST.remove(i);
                                i--;
                                continue outer;
                            }
                        }
                    }
                }
            }
            //获取异常配置参数
            ALLLIST.forEach(abData -> {
                String errorType = "";
                if (abData.getDataError() != null) {
                    errorType = abData.getDataError().split("_")[0];
                }
                if ("data".equals(errorType) || "md".equals(errorType)) {
                    abData.setBrokenAccordingId(abData.getDataError());

                  */
/**
 * 查询24小时内的数据表中是否包含这次测站的这个异常
 * *//*

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateTransform.String2Date(abData.getDate(), "yyyy-MM-dd HH:mm:ss"));
                    cal.add(cal.HOUR, -24);
                    String last24HourTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

                   */
/**
 * 查询前24小时内
 * 同一异常
 * 的异常表数据。
 * *//*

                    List<AbnormalDetailEntity> latestAbnormalData = abnormalDetailMapper.getLatestData(last24HourTime, abData.getSensorCode(), abData.getBrokenAccordingId());

                    //赋值测站号和修改日期
                    abData.setStationCode(abData.getSensorCode() / 100);
                    abData.setAlterSensorTypeId(abData.getSectionCode() % 100);
                    abData.setCreateTime(abData.getDate());

                    */
/**
 * 查询数据表二,是否有数据的最后一次出现时间 = 异常表上24小时内的时间,
 * 若有，更新最后一次生成时间
 * *//*

                    if (latestAbnormalData.size() > 0) {
                        try {
                            int stationId = abData.getStationCode();
                            String accordingId = abData.getDataError();
                            List<ReportManageDataMantain> latestData = reportManageDataMantainMapper.getLastestData(stationId,accordingId,last24HourTime);
                            if (latestData.size() > 0) {
                                latestData.forEach(latestdata->{

                                    Date dateOld = DateTransform.String2Date(latestdata.getErrorLastestAppearTime(), "yyyy-MM-dd HH:mm:ss");
                                    Date dateNew = DateTransform.String2Date(abData.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
                                    if (dateOld.after(dateNew)) {
                                        //修改最后出现时间
                                        abData.setErrorLastestAppearTime(latestdata.getErrorLastestAppearTime());
                                        //修改错误时间段
                                        abData.setErrorTimeSpace(abData.getCreateTime() + "," +  abData.getErrorLastestAppearTime());
                                        abData.setReportId(latestdata.getReportId());
                                    }else {
                                        //修改最后出现时间
                                        abData.setErrorLastestAppearTime(abData.getCreateTime());
                                        //修改错误时间段
                                        abData.setErrorTimeSpace(latestdata.getCreateTime() + "," +  abData.getCreateTime());
                                        abData.setReportId(latestdata.getReportId());
                                    }

                                    reportManageDataMantainMapper.updateTime(abData);
                                    System.out.println(abData.getStationName() + "的异常" + abData.getBrokenAccordingId() + "表二数据错误时间更替" + abData.getErrorLastestAppearTime());
                                });
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
            int allsize = abnormalall.size();
            abnormalall.forEach(data->{
                reportManageDataMantainMapper.insertAbnormal(data);
            });
            return allsize;
        } else {
            return 0;
        }
    }


*/