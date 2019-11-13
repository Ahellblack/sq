package com.siti.wisdomhydrologic.operation.service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
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
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by zyw on 2019/7/31.
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
    ManageApplicationBrokenMapper manageApplicationBrokenMapper;

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
        try {
            entity.setBuilderCode(1);//设为手动添加
            entity.setRequestDesignatingStatus(4);//数据状态为已完成
            entity.setErrorLastestAppearTime(entity.getCreateTime());
            reportManageApplicationBrokenMapper.insert(entity);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int update(ReportManageApplicationBroken entity) {

        try {
            entity.setErrorLastestAppearTime(entity.getCreateTime());
            reportManageApplicationBrokenMapper.update(entity);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }


    public int updateMalStatus(Integer reportId) {
        try {
            ReportManageApplicationBroken entity = reportManageApplicationBrokenMapper.getOne(reportId);
            ConfigRiverStation malStation = configRiverStationMapper.getAllByCode(entity.getStationId());
            List<String> phoneNumber = reportManageApplicationBrokenMapper.getNumberByRegionId(malStation.getRegionId());
            /**
             *派单后状态直接更改为3，派单时间=处理中时间
             * */
            entity.setMalStatus(1);
            entity.setRequestDesignatingStatus(3);
            entity.setRequestDesignatingTime(DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            entity.setBrokenOnResolveTime(DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            String numberStr = "";
            for (int i = 0; i < phoneNumber.size(); i++) {
                numberStr = numberStr + "," + phoneNumber.get(i);
            }
            if (numberStr.length() > 1) {
                numberStr = numberStr.substring(1, numberStr.length());
            }
            if (numberStr == "") {
                System.out.println("配置电话信息错误");
                return 0;
            }
            ConfigAbnormalDictionary oneByAccordingId = new ConfigAbnormalDictionary();
            if (entity.getStationName() != null && entity.getCreateTime() != null && entity.getBrokenAccordingId() != null) {
                //发送短信
                oneByAccordingId = configAbnormalDictionaryMapper.getOneByAccordingId(entity.getBrokenAccordingId());
            }
            if(oneByAccordingId.getDescription() == null){
                oneByAccordingId.setDescription("无");
            }if(oneByAccordingId.getBrokenAccording() == null || oneByAccordingId.getBrokenAccording() ==""){
                entity.setBrokenAccording(entity.getBrokenName());
            }
            PushMsg.pushMsgToClient(
                    numberStr,
                    entity.getStationName(),
                    entity.getCreateTime(),
                    entity.getBrokenAccording() + "," + oneByAccordingId.getDescription(),
                    reportId + "");
            return reportManageApplicationBrokenMapper.updateStatus(entity);

        } catch (Exception e) {
            System.out.println("派单异常");
            return 0;
        }
    }

    public int updateBrokenStatus() {
        List<ReportManageApplicationBroken> notResolveList = reportManageApplicationBrokenMapper.getNotResolve();
        String today = DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            notResolveList.forEach(data -> {
                int unCoverData = abnormalDetailCurrentMapper.getUnCoverData(data.getStationId());
                if (unCoverData == 0) {
                    data.setRequestDesignatingStatus(4);
                    data.setBrokenResolveTime(today);

                    if (data.getRequestDesignatingTime() == null) {
                        data.setRequestDesignatingTime(today);
                    }
                    if (data.getBrokenOnResolveTime() == null) {
                        data.setBrokenOnResolveTime(today);
                    }
                    reportManageApplicationBrokenMapper.updateStatus(data);
                }
            });
        } catch (Exception e) {
            System.out.println("系统自动恢复异常");
        }
        return 0;
    }


    public Map<String, Object> updateModuleStatus(Integer reportId) {
        Map<String, Object> map = new HashMap<>();
        try {
            /**
             * 对审核的设备故障，替换成已完成状态
             * */
            Optional<ReportManageApplicationBroken> optional = Optional.ofNullable(reportManageApplicationBrokenMapper.getOne(reportId));

            ReportManageApplicationBroken data = optional.get();
            String today = DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
            data.setRequestDesignatingStatus(4);
            data.setBrokenResolveTime(today);
            if (data.getRequestDesignatingTime() == null) {
                data.setRequestDesignatingTime(today);
            }
            if (data.getBrokenOnResolveTime() == null) {
                data.setBrokenOnResolveTime(today);
            }
            reportManageApplicationBrokenMapper.updateStatus(data);
            /**
             * 关联异常表找到故障模组,设置该模组为灰名单。
             * */
            Integer sensorCode = reportManageApplicationBrokenMapper.findModule(reportId);
            if (configSensorSectionModuleMapper.updateStatus(sensorCode) == 0) {
                map.put("status", 0);
                map.put("msg", "灰名单已被添加");
            } else {
                map.put("status", 1);
                map.put("msg", "模组添加至灰名单");
            }
            return map;

        } catch (Exception e) {
            map.put("status", -1);
            map.put("msg", "添加出现异常");
            return map;
        }
    }


    @Override
    public int delete(Integer reportId) {
        return reportManageApplicationBrokenMapper.updateDisplayStatus(reportId);
    }


    /**
     * 根据日期获取异常
     */
    @Override
    public int insertDataMantain() {
        try {
            List<ModuleAndStation> moduleList = configSensorSectionModuleMapper.getStationAndModule();
            //根据异常表表四展示状态字段 获取数据
            //获取到的数据状态更新为1
            List<AbnormalDetailCurrent> lastest = abnormalDetailCurrentMapper.get4Lastest();
            List<ReportManageApplicationBroken> brokenList = new ArrayList();
            List<Integer> idList = lastest.stream().map(AbnormalDetailCurrent::getId).collect(Collectors.toList());
            if (lastest.size() > 0) {
                //新建拷贝筛选队列
                List<ReportManageApplicationBroken> histroyList = reportManageApplicationBrokenMapper.getById(idList);
                if (histroyList.size() > 0) {
                    histroyList.forEach(data -> lastest.forEach(lasttime -> {
                        if (data.getReportId() == lasttime.getId()) {
                            reportManageApplicationBrokenMapper.updateTime(data.getReportId(), lasttime.getLastDate());
                            abnormalDetailCurrentMapper.update4Status(lasttime.getId());
                        }
                    }));
                }
                List<AbnormalDetailCurrent> newlastest = abnormalDetailCurrentMapper.get4Lastest();
                if (newlastest.size() > 0) {
                    List<Integer> idList2 = newlastest.stream().map(AbnormalDetailCurrent::getId).collect(Collectors.toList());
                    newlastest.forEach(data -> {
                        ReportManageApplicationBroken entity = new ReportManageApplicationBroken.Builder().reportId(data.getId()).brokenAccordingId(data.getBrokenAccordingId()).brokenAccording(data.getBrokenAccording()).description(data.getDescription()).createTime(data.getDate()).brokenName(data.getErrorName()).errorLastestAppearTime(data.getLastDate()).build();

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
        }catch (Exception e){
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


    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(HttpSession session, String createTime, String stationId, Integer status) {
        if (createTime == null) {
            createTime = DateTransform.Date2String(new Date(), "yyyy-MM-dd");
        }
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());

        // 查询数据,此处省略
        List<ReportManageApplicationBroken> list = manageApplicationBrokenMapper.getAll(createTime, stationId, orgList.get(0).getId(), status, 1);

        for (int i = 0; i < list.size(); i++) {
            ReportManageApplicationBroken data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResponseTime() != null)
                data.setBrokenResponseTime(data.getBrokenResponseTime().substring(8, 10) + "日" + data.getBrokenResponseTime().substring(11, 13) + "时");
            if (data.getBrokenAskToResolveTime() != null)
                data.setBrokenAskToResolveTime(data.getBrokenAskToResolveTime().substring(8, 10) + "日" + data.getBrokenAskToResolveTime().substring(11, 13) + "时");
            if (data.getBrokenrRequestReportTime() != null)
                data.setBrokenrRequestReportTime(data.getBrokenrRequestReportTime().substring(8, 10) + "日" + data.getBrokenrRequestReportTime().substring(11, 13) + "时");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String rootPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model4.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表四");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(HttpSession session, String createTime, String stationId, @RequestParam List<Integer> reportIdList, Integer status) {
        if (createTime == null) {
            createTime = DateTransform.Date2String(new Date(), "yyyy-MM-dd");
        }
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());

        // 查询数据,此处省略
        List<ReportManageApplicationBroken> list = manageApplicationBrokenMapper.getAll(createTime, stationId, orgList.get(0).getId(), status, 1);
        /**
         * 选择导出reportList替换全部list
         * */
        if (reportIdList.size() > 0) {
            List<ReportManageApplicationBroken> reportlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (reportIdList.contains(list.get(i).getReportId())) {
                    reportlist.add(list.get(i));
                }
            }
            list = reportlist;
        }
        for (int i = 0; i < list.size(); i++) {
            ReportManageApplicationBroken data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResponseTime() != null)
                data.setBrokenResponseTime(data.getBrokenResponseTime().substring(8, 10) + "日" + data.getBrokenResponseTime().substring(11, 13) + "时");
            if (data.getBrokenAskToResolveTime() != null)
                data.setBrokenAskToResolveTime(data.getBrokenAskToResolveTime().substring(8, 10) + "日" + data.getBrokenAskToResolveTime().substring(11, 13) + "时");
            if (data.getBrokenrRequestReportTime() != null)
                data.setBrokenrRequestReportTime(data.getBrokenrRequestReportTime().substring(8, 10) + "日" + data.getBrokenrRequestReportTime().substring(11, 13) + "时");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String rootPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model4.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表四");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

}

