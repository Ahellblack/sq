package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.mapper.HyetometerMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.HyetometerServiceImpl;
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
 * Created by zyw on 2019/7/26.
 */
@RequestMapping("/hyetometer")
@RestController
@Api(value = "雨滴表controller", tags = {"雨量计滴水实验记录表"})
public class HyetometerController {

    @Resource
    private HyetometerServiceImpl reportHyetometerService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private HyetometerMapper reportHyetometerMapper;
    @Resource
    private SysLogMapper sysLogMapper;

    @ApiOperation(value = "雨滴表查询", httpMethod = "GET", notes = "雨滴表查询")
    @GetMapping("/getAll")
    public List<ReportHyetometerTest> getAll(HttpSession session, String createTime, String stationId) {
        return reportHyetometerService.getAll(session, createTime, stationId);
    }

    @GetMapping("/deleteBy")
    public int deleteBy(Integer reportId,HttpSession session) {
        int i = reportHyetometerService.delByReportId(reportId);

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("从数据表6删除"+reportId)
                .setFreshVal("")
                .setAction("删除")
                .setPreviousVal("")
                .build());
        return i;
    }

    @GetMapping("/deleteChosen")
    public int deleteByList(@RequestBody List<Integer> reportIdList) {
        return reportHyetometerService.delByReportIdList(reportIdList);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportHyetometerTest reportHyetometer,HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("添加数据")
                .setFreshVal(reportHyetometer.toString())
                .setAction("添加")
                .setPreviousVal("")
                .build());
        return reportHyetometerService.insert(reportHyetometer);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportHyetometerTest reportHyetometer,HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("修改数据")
                .setFreshVal(reportHyetometer.toString())
                .setAction("添加")
                .setPreviousVal("")
                .build());
        return reportHyetometerService.update(reportHyetometer);
    }

    @ApiOperation(value = "雨滴表excel导出接口", httpMethod = "GET", notes = "雨滴表excel导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpSession session, HttpServletResponse response, String createTime, String stationId, @RequestParam  List<Integer> reportIdList) throws UnsupportedEncodingException {
        // 获取workbook对象   easypoi   easyexcell
        Workbook workbook = reportHyetometerService.exportSheetByTemplate(session, createTime, stationId, reportIdList);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "雨量计滴水实验记录表";
        // 重置响应对象
        return WorkBookUtils.download(response,workbook,excelName);
    }
    /**
     * 导出全部
     * */
    @ApiOperation(value = "雨滴表excel导出接口", httpMethod = "GET", notes = "雨滴表excel导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpSession session, HttpServletResponse response, String createTime, String stationId) throws UnsupportedEncodingException {
        // 获取workbook对象   easypoi   easyexcell
        Workbook workbook = reportHyetometerService.exportSheetByTemplate(session, createTime, stationId);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "雨量计滴水实验记录表";
        return WorkBookUtils.download(response,workbook,excelName);
    }



}
