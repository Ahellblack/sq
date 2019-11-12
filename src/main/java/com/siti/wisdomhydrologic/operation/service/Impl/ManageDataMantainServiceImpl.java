package com.siti.wisdomhydrologic.operation.service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
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
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by zyw on 2019/7/30.
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
        if (lastest.size() > 0) {
            try {
                List<Integer> idList = lastest.stream()
                        .map(AbnormalDetailCurrent::getId).collect(Collectors.toList());
                List<ReportManageDataMantainVo> histroyList =
                        reportManageDataMantainMapper.getById(idList);
                if (histroyList.size() > 0) {
                    histroyList.forEach(data -> lastest.forEach(lasttime -> {
                        if (data.getReportId() == lasttime.getId()) {
                            String errorSpace = data.getCreateTime() + "," + lasttime.getLastDate();
                            data.setErrorTimeSpace(errorSpace);
                            data.setErrorLastestAppearTime(lasttime.getLastDate());
                            reportManageDataMantainMapper.updateTime(data);
                            abnormalDetailCurrentMapper.update2Status(lasttime.getId());
                            //System.out.println("表二数据错误时间更替" + lasttime.getLastDate());
                        }
                    }));
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
                            ReportManageDataMantainVo entity = new ReportManageDataMantainVo.Builder()
                                        .reportId(data.getId())
                                        .brokenAccordingId(data.getBrokenAccordingId())
                                        .errorDataReason(data.getErrorName())
                                        .errorDataType(data.getErrorDataId())
                                        .createTime(data.getDate())
                                        .errorDataReRun(0)
                                        .missDataReRun(0)
                                        .errorLastestAppearTime(data.getLastDate())
                                        .alterDate(data.getDate().substring(0,10)).build();

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
    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(String stationId, String alterType, String createTime,HttpSession session) {
        //默认查询本月
        if (createTime == null) {
            createTime = DateOrTimeTrans.Date2TimeString3(new Date());
        }

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        // 查询数据,此处省略
        List<ReportManageDataMantainVo> list = reportManageDataMantainMapper.getVoByCreateDate(stationId, alterType, createTime,orgList.get(0).getId());

        for (int i = 0; i < list.size(); i++) {
            try {
                ReportManageDataMantainVo data = list.get(i);
                data.setReportId(i + 1);
                if (data.getCreateTime() != null)
                    data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
                if (data.getAlterSensorTypeName() != null)
                    data.setAlterSensorTypeName(data.getAlterSensorTypeName().substring(data.getAlterSensorTypeName().length() - 2, data.getAlterSensorTypeName().length()));
                if (data.getAlterDate() != null) data.setAlterDate(data.getAlterDate().substring(8, 10) + "日");
                int type = data.getErrorDataType();
                data.setErrorDataTypeName(type == 1 ? "实时" : type == 2 ? "5分钟" : type == 3 ? "小时" : type == 4 ? "一天" : "空值");
                if (data.getMissDataReRun() != null) {
                    int miss = data.getMissDataReRun();
                    data.setMissDataReRunName(miss == 0 ? "×" : "√");
                }
                if (data.getErrorDataReRun() != null) {
                    int error = data.getErrorDataReRun();
                    data.setErrorDataReRunName(error == 0 ? "×" : "√");
                }
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model2.xls");
        System.out.println(logFilePath + "sqexcelmodel/model2.xls");

        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表二");
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
    public Workbook exportSheetByTemplate(String stationId, String alterType, String createTime,HttpSession session,@RequestParam List<Integer> reportIdList) {
        //默认查询本月
        if (createTime == null) {
            createTime = DateOrTimeTrans.Date2TimeString3(new Date());
        }

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        // 查询数据,此处省略
        List<ReportManageDataMantainVo> list = reportManageDataMantainMapper.getVoByCreateDate(stationId, alterType, createTime,orgList.get(0).getId());

        /**
         * 选择导出reportList替换全部list
         * */
        if (reportIdList.size() > 0) {
            List<ReportManageDataMantainVo> reportlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (reportIdList.contains(list.get(i).getReportId())) {
                    reportlist.add(list.get(i));
                }
            }
            list = reportlist;
        }

        for (int i = 0; i < list.size(); i++) {
            try {
                ReportManageDataMantainVo data = list.get(i);
                data.setReportId(i + 1);
                if (data.getCreateTime() != null)
                    data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
                if (data.getAlterSensorTypeName() != null)
                    data.setAlterSensorTypeName(data.getAlterSensorTypeName().substring(data.getAlterSensorTypeName().length() - 2, data.getAlterSensorTypeName().length()));
                if (data.getAlterDate() != null) data.setAlterDate(data.getAlterDate().substring(8, 10) + "日");
                int type = data.getErrorDataType();
                data.setErrorDataTypeName(type == 1 ? "实时" : type == 2 ? "5分钟" : type == 3 ? "小时" : type == 4 ? "一天" : "空值");
                if (data.getMissDataReRun() != null) {
                    int miss = data.getMissDataReRun();
                    data.setMissDataReRunName(miss == 0 ? "×" : "√");
                }
                if (data.getErrorDataReRun() != null) {
                    int error = data.getErrorDataReRun();
                    data.setErrorDataReRunName(error == 0 ? "×" : "√");
                }
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model2.xls");
        System.out.println(logFilePath + "sqexcelmodel/model2.xls");

        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表二");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

}

