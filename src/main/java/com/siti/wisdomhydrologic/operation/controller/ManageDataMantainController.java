package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageDataMantainServiceImpl;
import com.siti.wisdomhydrologic.operation.vo.RecordDeviceReplaceVo;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
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
 * Created by dell on 2019/7/30.
 */
@RequestMapping("/manageDataMantain")
@RestController
@Api(value = "系统数据修正登记表controller", tags = {"系统数据修正登记表"})
public class ManageDataMantainController {
    @Resource
    private ManageDataMantainMapper manageDataMantainMapper;
    @Resource
    private ManageDataMantainServiceImpl reportManageDataMantainService;
    @Resource
    private DayDataMapper dayDataMapper;
    @Resource
    private ManageDataMantainMapper reportManageDataMantainMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysLogMapper sysLogMapper;
    /**
     * 根据修改日期查询
     *
     * @Param stationId 测站id
     * @Param alterType 监控类型
     * @Param createBy  修改人
     * @Param createDate 数据生成日期
     * <p>
     * 若createDate为空，默认获取当月的数据
     */
    @ApiOperation(value = "表二查询", httpMethod = "GET", notes = "表二查询表二查询根据修改日期查询" + "*stationId 测站id alterType 监控类型     *createBy  修改人\\n\" + \"     * createDate 数据生成日期")
    @GetMapping("/getByCreateDate")
    public PageInfo<ReportManageDataMantain> getByCreateDate(int page, int pageSize, String stationId, String alterType, String createDate,HttpSession session) {
        return reportManageDataMantainService.getByCreateDate(page, pageSize, stationId, alterType, createDate,session);
    }

    /**
     * 根据修改日期查询
     *
     * @Param stationId 测站id
     * @Param alterType 监控类型
     * @Param createBy  修改人
     * @Param createDate 数据生成日期
     * <p>
     * 若createDate为空，默认获取当月的数据
     */
    @ApiOperation(value = "表二查询", httpMethod = "GET", notes = "表二查询表二查询根据修改日期查询" + "*stationId 测站id alterType 监控类型     *createBy  修改人\\n\" + \"     * createDate 数据生成日期")
    @GetMapping("/getDisplayByCreateDate")
    public PageInfo<ReportManageDataMantain> getDisplayByCreateDate(int page, int pageSize, String stationId, String alterType, String createDate) {
        return reportManageDataMantainService.getDisplayByCreateDate(page, pageSize, stationId, alterType, createDate);
    }

    @ApiOperation(value = "表二删除", httpMethod = "GET", notes = "表二删除")
    @GetMapping("/delete")
    public int delete(Integer reportId,HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表2删除")
                .setFreshVal(reportId+"")
                .setAction("删除")
                .setPreviousVal("")
                .build());

        return reportManageDataMantainService.delete(reportId);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageDataMantain reportManageDataMantain,HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表2修改")
                .setFreshVal(reportManageDataMantain.toString())
                .setAction("修改")
                .setPreviousVal("")
                .build());
        return reportManageDataMantainService.update(reportManageDataMantain);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportManageDataMantain reportManageDataMantain,HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表2添加")
                .setFreshVal(reportManageDataMantain.toString())
                .setAction("添加")
                .setPreviousVal("")
                .build());
        return reportManageDataMantainService.insert(reportManageDataMantain);
    }

    @ApiOperation(value = "表二自动入库，后台人员使用", httpMethod = "GET", notes = "表二自动入库")
    @GetMapping("/insertAbnormal")
    public int insertAbnormalData(String date) {
        return reportManageDataMantainService.insertAbnormalData(date);
    }

    /*

        @ApiOperation(value = "表二删除", httpMethod = "GET", notes = "表二删除")
        @PostMapping("/getSelect")
        public Map<Integer, String> getSelect() {
            List<ConfigSensorSectionModule> station = dayDataMapper.getStation();
            Map<Integer, String> map = new HashMap<>();
            station.forEach(s -> {
                map.put(s.getStationCode(), s.getSectionName());
            });
            return map;
        }
    */
    @ApiOperation(value = "表二模板导出", httpMethod = "GET", notes = "表二模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String stationId, String alterType, String createTime,HttpSession session, @RequestParam List<Integer> reportIdList) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(stationId, alterType, createTime,session,reportIdList);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "系统数据修正登记表";
        // 重置响应对象
        response.reset();
        // 当前日期，用于导出文件名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = excelName + "_" + sdf.format(new Date());
        String DownName = URLEncoder.encode(dateStr, "UTF-8");
        // 指定下载的文件名--设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + DownName + ".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 模版单sheet导出示例
     *
     * @return
     */
    public Workbook exportSheetByTemplate(String stationId, String alterType, String createTime,HttpSession session,@RequestParam  List<Integer> reportIdList) {
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



    @ApiOperation(value = "表二模板导出", httpMethod = "GET", notes = "表二模板导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String stationId, String alterType, String createTime,HttpSession session) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(stationId, alterType, createTime,session);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "系统数据修正登记表";
        // 重置响应对象
        response.reset();
        // 当前日期，用于导出文件名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = excelName + "_" + sdf.format(new Date());
        String DownName = URLEncoder.encode(dateStr, "UTF-8");
        // 指定下载的文件名--设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + DownName + ".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
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


}
