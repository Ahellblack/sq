package com.siti.wisdomhydrologic.operation.controller;

import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.service.Impl.StationBrokenServiceImpl;
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
 */
@RestController
@RequestMapping("/applicationBroken")
@Api(value = "应用程序及设备异常表controller", tags = {"表四应用程序及设备异常表"})
public class StationBrokenController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private StationBrokenServiceImpl stationBrokenService;
    @Resource
    private SysLogMapper sysLogMapper;

    @ApiOperation(value = "测站故障情况查询", httpMethod = "GET", notes = "表四测站故障情况查询")
    @GetMapping("/getAll")
    public PageInfo<ReportStationBroken> selectAll(HttpSession session, int page, int pageSize, String createDate, String stationId, Integer status) {
        return stationBrokenService.getAll(session, page, pageSize, createDate, stationId, status);
    }

    @ApiOperation(value = "测站故障情况查询", httpMethod = "GET", notes = "表四测站故障情况查询")
    @GetMapping("/getAllDisplay")
    public PageInfo<ReportStationBroken> selectAllDisplay(int page, int pageSize, String createDate, String stationId, Integer status) {
        return stationBrokenService.selectAllDisplay(page, pageSize, createDate, stationId, status);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportStationBroken reportStationBroken, HttpSession session) {
        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog(new SysLog.builder().setUsername(user.getUserName()).setOperateDes("数据表4添加").setFreshVal(reportStationBroken.toString()).setAction("添加").setPreviousVal("").build());
        return stationBrokenService.insert(reportStationBroken);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportStationBroken reportStationBroken, HttpSession session) {
        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog(new SysLog.builder().setUsername(user.getUserName()).setOperateDes("数据表4修改").setFreshVal(reportStationBroken.toString()).setAction("修改").setPreviousVal("").build());
        return stationBrokenService.update(reportStationBroken);
    }

    @ApiOperation(value = "故障情况记录表查派单状态修改", notes = "表四故障情况记录表查派单状态修改,参数为2绑定派单，参数为4绑定已处理")
    @GetMapping("/updateStatus")
    public int updateMalStatus(Integer reportId) {
        return stationBrokenService.updateMalStatus(reportId);
    }

    @ApiOperation(value = "人员核验错误调整", notes = "表四故障情况记录表查派单状态修改,参数为2绑定派单，参数为4绑定已处理")
    @GetMapping("/updateModuleStatus")
    public Map<String, Object> updateModuleStatus(Integer reportId) {
        return stationBrokenService.updateModuleStatus(reportId);
    }


    @ApiOperation(value = "故障情况记录表删除", httpMethod = "GET", notes = "表四故障情况记录表删除")
    @GetMapping("/delete")
    public int delete(Integer reportId, HttpSession session) {
        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog(new SysLog.builder().setUsername(user.getUserName()).setOperateDes("数据表4删除" + reportId).setFreshVal(reportId + "").setAction("删除").setPreviousVal("").build());
        return stationBrokenService.delete(reportId);
    }

    @ApiOperation(value = "故障情况记录表自动添加入库接口,后台人员使用", httpMethod = "GET", notes = "表四故障情况记录表自动添加入库接口")
    @GetMapping("/insertDataMantain")
    public int insertDataMantain() {
        return stationBrokenService.insertDataMantain();
    }

    @ApiOperation(value = "表四故障情况记录表EXCEL模板导出", httpMethod = "GET", notes = "表四故障情况记录表EXCEL模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpSession session, HttpServletResponse response, String createTime, String stationId, @RequestParam List<Integer> reportIdList, Integer status) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = stationBrokenService.exportSheetByTemplate(session, createTime, stationId, reportIdList, status);
        String excelName = "故障情况记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }


    @ApiOperation(value = "故障情况记录表EXCEL模板导出", httpMethod = "GET", notes = "表四故障情况记录表EXCEL模板导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpSession session, HttpServletResponse response, String createTime, String stationId, Integer status) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = stationBrokenService.exportSheetByTemplate(session, createTime, stationId, status);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "故障情况记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }


}
