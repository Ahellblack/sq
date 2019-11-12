package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorDatabase;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorDatabaseMapper;
import com.siti.wisdomhydrologic.operation.entity.RecordDeviceReplace;
import com.siti.wisdomhydrologic.operation.mapper.RecordDeviceReplaceMapper;
import com.siti.wisdomhydrologic.operation.utils.WorkBookUtils;
import com.siti.wisdomhydrologic.operation.vo.RecordDeviceReplaceVo;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zyw on 2019/8/23.
 * 表8
 */
@RequestMapping("/recordDevice")
@RestController
@Api(value = "测站设备变更记录表controller", tags = {"测站设备变更记录表"})
public class RecordDeviceReplaceController {
    @Resource
    private ConfigSensorDatabaseMapper configSensorDatabaseMapper;
    @Resource
    private RecordDeviceReplaceMapper mapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysLogMapper sysLogMapper;

    @ApiOperation(value = "", httpMethod = "GET", notes = "测站设备变更记录表")
    @GetMapping("/getAll")
    public List<RecordDeviceReplaceVo> getAll(String stationId, String createDate) {
        return mapper.getAll(stationId, createDate);
    }

    @GetMapping("/delete")
    public int delete(Integer reportId, HttpSession session) {
        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表8删除")
                .setFreshVal(reportId+"")
                .setAction("删除")
                .setPreviousVal("")
                .build());
        return mapper.deleteData(reportId);
    }

    @PostMapping("/update")
    public int update(@RequestBody RecordDeviceReplace entity,HttpSession session) {
        try {
            User user = (User) userInfoService.get();
            sysLogMapper.insertUserOprLog( new SysLog.builder()
                    .setUsername(user.getUserName())
                    .setOperateDes("数据表8修改")
                    .setFreshVal(entity.toString())
                    .setAction("修改")
                    .setPreviousVal("")
                    .build());
            return mapper.updateData(entity);
        } catch (Exception e) {
            return 0;
        }
    }
    @ApiOperation(value = "表八原设备资产选择下拉框", httpMethod = "GET", notes = "表八原设备资产选择下拉框")
    @GetMapping("/getOriginDatabase")
    public List<ConfigSensorDatabase> getOriginList(String originDeviceId,String manageOrgId) {
        return configSensorDatabaseMapper.getData(originDeviceId, manageOrgId);
    }
    @ApiOperation(value = "表八新备用设备资产选择下拉框", httpMethod = "GET", notes = "表八新备用设备资产选择下拉框")
    @GetMapping("/getNewDatabase")
    public List<ConfigSensorDatabase> getNewList(String newDeviceId) {
        return configSensorDatabaseMapper.getNewData(newDeviceId);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody RecordDeviceReplaceVo vo,HttpSession session) {

        //ConfigSensorDatabase database = configSensorDatabaseMapper.getData(vo.getOriginDeviceName(),vo.getManageOrgName());
        //旧设备状态更新
        ConfigSensorDatabase oldDatabase = configSensorDatabaseMapper.findAllByPropertyCode(vo.getOriginDatabaseId());
        //设置状态为 0备用,1已使用,2维修中,3已报废
        if (oldDatabase != null) {
            oldDatabase.setSensorUseStatus(vo.getOriginDatabaseStatus());
            configSensorDatabaseMapper.update(oldDatabase);
        } else {
            return 0;
        }
        //新设备备用状态选择测站,状态更新为1:已安装
        ConfigSensorDatabase newDatabase = configSensorDatabaseMapper.findAllByPropertyCode(vo.getNewDatabaseId());
        if (newDatabase != null) {
            newDatabase.setSensorUseStatus("1");
            newDatabase.setManageOrgId(oldDatabase.getManageOrgId());
            newDatabase.setManageOrgName(oldDatabase.getManageOrgName());
        }else {
            return 0;
        }
        configSensorDatabaseMapper.update(newDatabase);
        //为设备更替记录赋值
        RecordDeviceReplace entity = new RecordDeviceReplace.Builder()
                .replaceDate(vo.getReplaceDate())
                .createBy(vo.getCreateBy())
                .replaceReason(vo.getReplaceReason())
                //根据旧资产表赋值
                .stationCode(oldDatabase.getManageOrgId()+"")
                .stationName(oldDatabase.getManageOrgName())
                .originDeviceCode(oldDatabase.getPropertyCode())
                .originDeviceName(oldDatabase.getSensorTypeName())
                .originDeviceTypeCode(oldDatabase.getSensorModelType())
                .originOrgName(oldDatabase.getSubordinateCompany())
                //根据替换资产赋值
                .newDeviceCode(newDatabase.getPropertyCode())
                .newDeviceName(newDatabase.getSensorTypeName())
                .newDeviceTypeCode(newDatabase.getSensorModelType())
                .newOrgName(newDatabase.getSubordinateCompany())
                .createTime(DateTransform.Date2String(new Date(),"yyyy-MM-dd HH:mm:ss")).build();

        //修改设备记录在资产表
        try {
            User user = (User) userInfoService.get();
            sysLogMapper.insertUserOprLog( new SysLog.builder()
                    .setUsername(user.getUserName())
                    .setOperateDes("替换旧设备"+oldDatabase.toString()+"为新设备"+newDatabase.toString())
                    .setFreshVal(entity.toString())
                    .setAction("修改")
                    .setPreviousVal("")
                    .build());

            return mapper.insert(entity);

        } catch (Exception e) {
            return 0;
        }
    }

    @ApiOperation(value = "表八测站设备变更记录表模板导出", httpMethod = "GET", notes = "表八测站设备变更记录表模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String stationId, String createDate, @RequestParam List<Integer> reportIdList) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(stationId, createDate,reportIdList);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "测站设备变更记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }


    @ApiOperation(value = "表八测站设备变更记录表模板导出", httpMethod = "GET", notes = "表八测站设备变更记录表模板导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String stationId, String createDate) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(stationId, createDate);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "测站设备变更记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }


    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(String stationId, String createDate, @RequestParam List<Integer> reportIdList) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        // 查询数据,此处省略
        List<RecordDeviceReplaceVo> list = mapper.getAll(stationId, createDate);

        /**
         * 选择导出reportList替换全部list
         * */
        if (reportIdList.size() > 0) {
            List<RecordDeviceReplaceVo> reportlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (reportIdList.contains(list.get(i).getReportId())) {
                    reportlist.add(list.get(i));
                }
            }
            list = reportlist;
        }

        for (int i = 0; i < list.size(); i++) {
            RecordDeviceReplaceVo data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getReplaceDate() != null) data.setReplaceDate(data.getReplaceDate().substring(8, 10) + "日");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model8.xls");

        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表八");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createDate);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(String stationId, String createDate) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        // 查询数据,此处省略
        List<RecordDeviceReplaceVo> list = mapper.getAll(stationId, createDate);


        for (int i = 0; i < list.size(); i++) {
            RecordDeviceReplaceVo data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getReplaceDate() != null) data.setReplaceDate(data.getReplaceDate().substring(8, 10) + "日");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model8.xls");

        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表八");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createDate);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

}
