package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageMantainServiceImpl;
import com.siti.wisdomhydrologic.operation.utils.WorkBookUtils;
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
 * Created by zyw on 2019/7/31.
 **/


@RequestMapping("/manageMantain")
@RestController
@Api(value="日常维护记录表controller",tags={"日常维护记录表"})
public class ManageMantainController {
    @Resource
    private ManageMantainServiceImpl reportManageMantainService;
    @Resource
    private UserInfoService userInfoService;
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
     * @param yearMonth 需要添加或判断的月份 yyyy-MM
     */
    @ApiOperation(value = "表一数据查询时若无数据自动查询，表一不可进行添加", httpMethod = "GET", notes = "表一数据查询时若无数据自动查询")
    @GetMapping("/insertOrUpdate")
    public void insertOrUpdate(/*@RequestBody ReportManageMantain reportManageMantain*/String yearMonth) {
        reportManageMantainService.insertOrUpdate(yearMonth);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageMantain reportManageMantain, HttpSession session) {

        User user = (User) userInfoService.get();
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
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String date) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = reportManageMantainService.exportSheetByTemplate(date);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "日常维护记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }



}
