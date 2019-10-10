package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageMantainServiceImpl;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
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
 * Created by dell on 2019/7/31.
 **/


@RequestMapping("/manageMantain")
@RestController
@Api(value="日常维护记录表controller",tags={"日常维护记录表"})
public class ManageMantainController {
    @Resource
    private ManageMantainServiceImpl reportManageMantainService;
    @Resource
    private RedisBiz redisBiz;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysLogMapper sysLogMapper;
    /**
     * @Param date xxxx年xx月 格式YYYY-MM
     */
    @ApiOperation(value = "表一日常维护记录表查询，根据登录后的sysorg和月份参数查询", httpMethod = "GET", notes = "表一日常维护记录表查询")
    @GetMapping("/selectByDate")
    public List<ReportManageMantain> getAll(String date) {

        return reportManageMantainService.getAll(date);
    }

    /**
     * 每月初获取上个月的运维表数据并生成
     *
     * @param yearMonth 需要添加或判断的月份 yyyy-MM
     */
    @ApiOperation(value = "表一数据查询时若无数据自动查询，表一不可进行添加", httpMethod = "GET", notes = "表一数据查询时若无数据自动查询")
    @PostMapping("/insertOrUpdate")
    public void insertOrUpdate(/*@RequestBody ReportManageMantain reportManageMantain*/String yearMonth) {
        reportManageMantainService.insertOrUpdate(yearMonth);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageMantain reportManageMantain, HttpSession session) {

        User user = (User) redisBiz.get(session.getId());
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表1修改")
                .setFreshVal(reportManageMantain.toString())
                .setAction("修改")
                .setPreviousVal("")
                .build());
        return reportManageMantainService.update(reportManageMantain);
    }

    @GetMapping("/delete")
    public int delete(Integer ReportId) {
        return reportManageMantainService.delete(ReportId);
    }

    @ApiOperation(value = "表一日常维护记录表模板导出", httpMethod = "GET", notes = "表一日常维护记录表模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String date) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(date);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "日常维护记录表";
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
    public Workbook exportSheetByTemplate(String date) {
        //默认查询本月
        if (date == null) {
            date = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        // 查询数据,此处省略
        List<ReportManageMantain> list = reportManageMantainService.getAll(date);
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ReportManageMantain data = list.get(i);
            data.setReportId(i + 1);
            if (i % 2 == 0) {
                numberList.add(i / 2);
            }
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model1.xls");

        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表一");
        Map<String, Object> map = new HashMap<String, Object>();
        numberList.forEach(data -> {
            String number = "number" + data;
            map.put(number, data);
        });
        map.put("list", list);
        map.put("date", date);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

}
