package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageApplicationBrokenServiceImpl;
import com.siti.wisdomhydrologic.operation.utils.WorkBookUtils;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * Created by zyw on 2019/7/31.
 * 表三
 */
@RequestMapping("/stationBroken")
@RestController
@Api(value = "应用程序及设备故障登记表controller", tags = {"表三应用程序及设备故障登记表"})
public class ManageApplicationBrokenController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ManageApplicationBrokenServiceImpl manageApplicationBrokenService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SysLogMapper sysLogMapper;
    @ApiOperation(value = "表三应用程序及设备故障登记表查询", httpMethod = "GET", notes = "表三应用程序及设备故障登记表查询")
    @RequestMapping("/getAll")
    public List<ReportManageApplicationBroken> getAll(String createDate, String applicationEquipName) {
        return manageApplicationBrokenService.getAll(createDate, applicationEquipName);
    }

    @GetMapping("/delete")
    public int delete(Integer reportId, HttpSession session) {
        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表3删除")
                .setFreshVal(reportId+"")
                .setAction("删除")
                .setPreviousVal("")
                .build());
        return manageApplicationBrokenService.delete(reportId);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageApplicationBroken reportManageApplicationBroken, HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表3修改")
                .setFreshVal(reportManageApplicationBroken.toString())
                .setAction("修改")
                .setPreviousVal("")
                .build());
        return manageApplicationBrokenService.update(reportManageApplicationBroken);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportManageApplicationBroken reportManageApplicationBroken, HttpSession session) {
        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表3添加")
                .setFreshVal(reportManageApplicationBroken.toString())
                .setAction("添加")
                .setPreviousVal("")
                .build());
        return manageApplicationBrokenService.insert(reportManageApplicationBroken);
    }

    @ApiOperation(value = "表三应用程序及设备故障登记表模板导出", httpMethod = "GET", notes = "表三应用程序及设备故障登记表模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String createTime, String applicationEquipName, @RequestParam List<Integer> reportIdList) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = manageApplicationBrokenService.exportSheetByTemplate(createTime, applicationEquipName, reportIdList);
        // 设置excel的文件名称
        String excelName = "应用程序及设备故障登记表";
        return WorkBookUtils.download(response, workbook, excelName);
    }

    @ApiOperation(value = "表三应用程序及设备故障登记表模板导出", httpMethod = "GET", notes = "表三应用程序及设备故障登记表模板导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String createTime, String applicationEquipName) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = manageApplicationBrokenService.exportSheetByTemplate(createTime, applicationEquipName);
        // 设置excel的文件名称
        String excelName = "应用程序及设备故障登记表";
        return WorkBookUtils.download(response, workbook, excelName);
    }


}
