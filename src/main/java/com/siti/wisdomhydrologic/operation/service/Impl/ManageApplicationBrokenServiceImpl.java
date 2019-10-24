package com.siti.wisdomhydrologic.operation.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.configmaintain.entity.ModuleAndStation;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.entity.AbnormalDetailCurrent;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.AbnormalDetailCurrentMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.ManageApplicationBrokenService;
import com.siti.wisdomhydrologic.operation.vo.RealDeviceStatus;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.PushMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    AbnormalDetailCurrentMapper abnormalDetailCurrentMapper;

    @Resource
    private SysLogMapper sysLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(ManageApplicationBrokenServiceImpl.class);

    private static final int STATUS = 2;

    public PageInfo<ReportManageApplicationBroken> getAll(HttpSession session, int page, int pageSize, String createDate, String stationId, Integer status) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());

        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        PageHelper.startPage(page, pageSize);
        List<ReportManageApplicationBroken> all = reportManageApplicationBrokenMapper.getAll(createDate, stationId, orgList.get(0).getId(), status, 1);


        return new PageInfo<>(all);
    }

    //查询全部数据
    public PageInfo<ReportManageApplicationBroken> selectAllDisplay(int page, int pageSize, String createDate, String stationId, Integer status) {
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        PageHelper.startPage(page, pageSize);
        List<ReportManageApplicationBroken> all = reportManageApplicationBrokenMapper.getAll(createDate, stationId, orgList.get(0).getId(), status, null);

        return new PageInfo<>(all);
    }

    @Override
    public int insert(ReportManageApplicationBroken entity) {
        ConfigAbnormalDictionary according = configAbnormalDictionaryMapper.getOneByAccording(entity.getBrokenAccording());
        entity.setBrokenAccordingId(according.getBrokenAccordingId());
        entity.setRequestDesignatingStatus(1);
        entity.setErrorLastestAppearTime(entity.getCreateTime());
        Calendar calendar = Calendar.getInstance();
        List<ConfigRiverStation> riverStationList = configRiverStationMapper.getAllstation();
        /*try {
            riverStationList.forEach(river -> {
                if (entity.getStationId() == river.getStationId()) {
                    calendar.setTime(DateTransform.String2Date(entity.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    if (river.getStationLevel() == 2) {
                        //一般站往后3小时内
                        calendar.add(calendar.HOUR, 3);
                    } else {
                        //基本站往后1小时内
                        calendar.add(calendar.HOUR, 1);
                    }
                    entity.setBrokenResponseTime(DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }*/
        try {
            reportManageApplicationBrokenMapper.insert(entity);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int update(ReportManageApplicationBroken entity) {
        ConfigAbnormalDictionary according = configAbnormalDictionaryMapper.getOneByAccording(entity.getBrokenAccording());
        entity.setBrokenAccordingId(according.getBrokenAccordingId());
        entity.setRequestDesignatingStatus(1);
        entity.setErrorLastestAppearTime(entity.getCreateTime());
        Calendar calendar = Calendar.getInstance();
        //List<ConfigRiverStation> riverStationList = configRiverStationMapper.getAllstation();
        /*try {
            riverStationList.forEach(river -> {
                if (entity.getStationId() == river.getStationId()) {
                    calendar.setTime(DateTransform.String2Date(entity.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    if (river.getStationLevel() == 2) {
                        //一般站往后3小时内
                        calendar.add(calendar.HOUR, 3);
                    } else {
                        //基本站往后1小时内
                        calendar.add(calendar.HOUR, 1);
                    }
                    entity.setBrokenResponseTime(DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                }
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }*/
        try {
            reportManageApplicationBrokenMapper.update(entity);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }


    public int updateMalStatus(Integer reportId) {

        ReportManageApplicationBroken reportManageApplicationBroken = reportManageApplicationBrokenMapper.getOne(reportId);

        ConfigRiverStation allByCode = configRiverStationMapper.getAllByCode(reportManageApplicationBroken.getStationId());
        List<String> phoneNumber = reportManageApplicationBrokenMapper.getNumberByRegionId(allByCode.getRegionId());
        try {
            if (reportManageApplicationBroken.getRequestDesignatingStatus() == 1) {
                /**
                 *派单后状态直接更改为3，派单时间 == 处理中时间
                 * */
                reportManageApplicationBroken.setRequestDesignatingStatus(3);
                reportManageApplicationBroken.setRequestDesignatingTime(DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
                reportManageApplicationBroken.setBrokenOnResolveTime(DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
                String numberStr = "";
                for (int i = 0; i < phoneNumber.size(); i++) {
                    numberStr = numberStr + "," + phoneNumber.get(i);
                }
                if (numberStr.length() > 1) {
                    numberStr = numberStr.substring(1, numberStr.length());
                }
                if (numberStr == "") {
                    return 0;
                }
                if (reportManageApplicationBroken.getStationName() != null && reportManageApplicationBroken.getCreateTime() != null && reportManageApplicationBroken.getBrokenAccording() != null) {
                    //发送短信
                    PushMsg.pushMsgToClient(numberStr, reportManageApplicationBroken.getStationName(), reportManageApplicationBroken.getCreateTime(), reportManageApplicationBroken.getBrokenAccording(), reportId + "");
                }
                return reportManageApplicationBrokenMapper.updateStatus(reportManageApplicationBroken);
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int updateBrokenStatus() {
        List<ReportManageApplicationBroken> notResolveList = reportManageApplicationBrokenMapper.getNotResolve();

        Date today = new Date();
        try {
            String date = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateTransform.String2Date(date, "yyyy-MM-dd HH:mm:ss"));
            cal.add(Calendar.MINUTE, -30);

            String latest30minute = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
            notResolveList.forEach(data -> {

                List<AbnormalDetailEntity> latest30minuteDate = abnormalDetailMapper.get30MinuteDate(data.getStationId() + "", data.getBrokenAccordingId(), date, latest30minute);
                //系统异常表最近30分钟无该数据异常,表示恢复
                if (latest30minuteDate.size() == 0) {
                    data.setRequestDesignatingStatus(4);
                    String now = DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
                    if (data.getRequestDesignatingTime() == null) {
                        data.setRequestDesignatingTime(now);
                    }
                    if (data.getBrokenOnResolveTime() == null) {
                        data.setBrokenOnResolveTime(now);
                    }
                    data.setBrokenResolveTime(now);
                    reportManageApplicationBrokenMapper.updateStatus(data);
                    //System.out.println(data.getStationName()+"的异常恢复");
                }
            });
        } catch (Exception e) {
            System.out.println("系统自动恢复异常");
        }
        return 0;
    }



    @Override
    public int delete(Integer reportId) {
        //return reportManageApplicationBrokenMapper.deleteById(reportId);
        return reportManageApplicationBrokenMapper.updateDisplayStatus(reportId);
    }


    /**
     * 根据日期获取异常
     */
    @Override
    public int insertDataMantain() {
        List<ModuleAndStation> moduleList = configSensorSectionModuleMapper.getStationAndModule();

        //根据异常表表四展示状态字段 获取数据
        //获取到的数据状态更新为1
       /* List<ReportManageDataMantainVo> all = abnormalDetailMapper.getALLTable4Data();
*/
        List<AbnormalDetailCurrent> lastest = abnormalDetailCurrentMapper.get4Lastest();

        List<ReportManageApplicationBroken> brokenList = new ArrayList();

        List<Integer> idList = new ArrayList<>();

        if (lastest.size() > 0) {
            //新建拷贝筛选队列
            lastest.forEach(data -> {
                idList.add(data.getId());
            });
            List<ReportManageApplicationBroken> histroyList = reportManageApplicationBrokenMapper.getById(idList);
            if (histroyList.size() > 0) {

                histroyList.forEach(data -> {
                    lastest.forEach(lasttime -> {
                        if (data.getReportId() == lasttime.getId()) {
                            reportManageApplicationBrokenMapper
                                    .updateTime(data.getReportId(), lasttime.getLastDate());
                            abnormalDetailCurrentMapper
                                    .update4Status(lasttime.getId());
                            System.out.println("表四数据错误时间更替" + lasttime.getLastDate());
                        }
                    });
                });
            }
            List<AbnormalDetailCurrent> newlastest = abnormalDetailCurrentMapper.get4Lastest();
            if (newlastest.size() > 0) {
                List<Integer> idList2 = new ArrayList<>();
                newlastest.forEach(data -> {
                    idList2.add(data.getId());
                });
                newlastest.forEach(data -> {
                    ReportManageApplicationBroken entity = new ReportManageApplicationBroken();
                    entity.setReportId(data.getId());
                    entity.setBrokenAccordingId(data.getBrokenAccordingId());
                    entity.setBrokenAccording(data.getBrokenAccording());
                    entity.setDescription(data.getDescription());
                    entity.setCreateTime(data.getDate());
                    entity.setBrokenName(data.getErrorName());
                    entity.setErrorLastestAppearTime(data.getLastDate());
                    moduleList.forEach(module -> {
                        Calendar calendar = Calendar.getInstance();
                        if (data.getSensorCode() == module.getSectionCode()) {
                            calendar.setTime(DateTransform.String2Date(data.getDate(), "yyyy-MM-dd HH:mm:ss"));
                            if (module.getStationLevel() == 2) {
                                //一般站往后3小时内
                                calendar.add(calendar.HOUR, 3);
                            } else {
                                //基本站往后1小时内
                                calendar.add(calendar.HOUR, 1);
                            }
                            entity.setBrokenResponseTime(DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                            entity.setStationName(module.getStationName());
                            entity.setStationId(module.getStationId());
                        }
                    });
                    brokenList.add(entity);
                });
                //修改状态
                abnormalDetailCurrentMapper.update4StatusList(idList2);
            }
            int allsize = brokenList.size();
            brokenList.forEach(data -> {
                try {
                    reportManageApplicationBrokenMapper.insertDataMantain(data);
                } catch (Exception e) {
                    System.out.println("数据插入异常");
                }
            });
            return allsize;
        } else {
            return 0;
        }
    }


    /**
     * 取当前日期的年月日
     *
     * @return
     * @throws ParseException
     */
    public static Date getMinDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = sdf.parse(sdf.format(date));
        return newDate;
    }

    /**
     * 获取最近的整5分时间点real表数据
     *
     * @Param dateFormat dateFormat的格式 如 YYYY-MM-dd
     * @Param date 当前时间
     * @Param min 相隔时间
     */
    public static String getCloseDate(String dateFormat, Date date, long min) throws Exception {
        long dateTime = date.getTime();
        long needTime = 0;
        if (min >= 8 * 60) {
            return new SimpleDateFormat(dateFormat).format(getMinDate(date));
        } else {
            needTime = dateTime - dateTime % (min * 60L * 1000L);
        }
        return new SimpleDateFormat(dateFormat).format(new Date(needTime));
    }

 /* outer:
            for (int i = 0; i < ALLLIST.size(); i++) {
                String Iaid = ALLLIST.get(i).getDataError();
                Integer Isid = ALLLIST.get(i).getSensorCode();
                Date Idate = DateTransform.String2Date(ALLLIST.get(i).getDate(), "yyyy-MM-dd HH:mm:ss");
                for (int j = i + 1; j < ALLLIST.size(); j++) {
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
        }*/
    //获取异常配置参数
        /*if (ALLLIST.size() > 0) {
            ALLLIST.forEach(data -> {
                *//**
     * 查询上次24小时内的数据表中是否包含这次测站的这个异常
     * *//*
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateTransform.String2Date(data.getDate(), "yyyy-MM-dd HH:mm:ss"));
                cal.add(cal.HOUR, -24);
                String last24HourTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                List<AbnormalDetailEntity> latestData = abnormalDetailMapper.getLatestData(last24HourTime, data.getSensorCode(), data.getBrokenAccordingId());
                *//**
     * 查询数据表四,是否有数据的最后一次出现时间 = 异常表上个24小时的时间,
     * 若有，更新最后一次生成时间
     * *//*
                if (latestData.size() > 0) {
                    try {
                        Integer stationId = (latestData.get(0).getSensorCode() / 100);
                        String accordingId = latestData.get(0).getDataError();
                        List<ReportManageApplicationBroken> lastData = reportManageApplicationBrokenMapper.getLastestData(stationId, accordingId, last24HourTime);
                        if (lastData.size() > 0) {
                            lastData.forEach(lastdata -> {
                                Date dateOld = DateTransform.String2Date(lastdata.getErrorLastestAppearTime(), "yyyy-MM-dd HH:mm:ss");
                                Date dateNew = DateTransform.String2Date(data.getDate(), "yyyy-MM-dd HH:mm:ss");
                                if (dateOld.after(dateNew)) {
                                    //修改最后出现时间
                                    data.setErrorLastestAppearTime(lastdata.getErrorLastestAppearTime());
                                } else {
                                    data.setErrorLastestAppearTime(data.getDate());
                                }
                                reportManageApplicationBrokenMapper.updateTime(lastdata, data.getErrorLastestAppearTime());
                            });
                        } else {
                            Calendar calendar = Calendar.getInstance();
                            ReportManageApplicationBroken applicationBroken = new ReportManageApplicationBroken();
                            //异常包含设备异常或数据异常的一种
                            if (data.getDataError() != null) {
                                applicationBroken.setBrokenAccordingId(data.getDataError());
                                applicationBroken.setDescription(data.getDescription());
                            }
                            if (applicationBroken.getBrokenAccordingId() != null) {
                                //结合module表添加测站参数
                                moduleList.forEach(module -> {
                                    if (module.getSectionCode() == data.getSectionCode()) {
                                        applicationBroken.setStationId(module.getStationCode());
                                        applicationBroken.setStationName(module.getStationName());
                                    }
                                });
                                //给表四数据赋值各类自动时间
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
                                    });
                                } catch (Exception e) {
                                    logger.error(e.getMessage());
                                }

                                list.forEach(e -> {
                                    //根据字典赋值故障判断依据和故障名称
                                    if (e.getBrokenAccordingId().equals(applicationBroken.getBrokenAccordingId())) {
                                        applicationBroken.setBrokenAccording(e.getBrokenAccording());
                                        applicationBroken.setBrokenName(e.getErrorName());
                                    }
                                });
                                applicationBroken.setCreateTime(data.getDate());
                                applicationBroken.setErrorLastestAppearTime(data.getDate());
                                brokenList.add(applicationBroken);
                            }
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
                *//**
     * 修改已被展示的异常表数据 状态
     * *//*r
                idList.add(data.getId());
            });
            abnomalDetailMapper.updateTable4Status(idList);
*/


     /*public void updateUnpackStatus() {
        //获取5分钟内的开关门动态
        List<RealDeviceStatus> devList  = reportManageApplicationBrokenMapper.getRealDeviceStatus();

        if(devList.size()>0){
            List<ReportManageApplicationBroken> notResolveList = reportManageApplicationBrokenMapper.getNotResolve();
            devList.forEach(data->{
                for (ReportManageApplicationBroken broken : notResolveList) {
                    if(broken.getStationId()+"" == data.getStationId()
                            || broken.getBrokenOnResolveTime() == null ){
                        broken.setBrokenOnResolveTime(data.getLastUploadTime());
                        broken.setRequestDesignatingStatus(3);
                        if (broken.getRequestDesignatingTime() == null) {
                            broken.setRequestDesignatingTime(data.getLastUploadTime());
                        }
                        //无维护时间修改
                        reportManageApplicationBrokenMapper.updateStatus(broken);
                    }
                }});}
    }*/

}

