package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.mapper.StationCheckMantainMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.StationCheckMantainServiceImpl;
import com.siti.wisdomhydrologic.operation.utils.WorkBookUtils;
import com.siti.wisdomhydrologic.operation.vo.RainVo;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyw on 2019/7/31.
 */
@RequestMapping("/stationCheck")
@RestController
@Api(value = "测站检查维护记录表controller", tags = {"测站检查维护记录表"})
public class StationCheckMantainController {
    @Resource
    private StationCheckMantainServiceImpl stationCheckMantainService;
    @Resource
    private StationCheckMantainMapper stationCheckMantainMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SysLogMapper sysLogMapper;
    @ApiOperation(value = "表五测站检查维护记录表查询，根据日期及测站id进行筛选，每次导出一条数据", httpMethod = "GET", notes = "表五测站检查维护记录表查询")
    @GetMapping("/getAll")
    public ReportStationCheckMantain getAll(String mantainDate, Integer stationId) {

        ReportStationCheckMantain byStationId = stationCheckMantainMapper.getByStationId(mantainDate, stationId);
        if (byStationId!=null){
            return byStationId;
        }else {
            ConfigRiverStation allByCode = configRiverStationMapper.getAllByCode(stationId);
            ReportStationCheckMantain newData = new ReportStationCheckMantain(mantainDate, stationId, allByCode.getStationName());
            stationCheckMantainMapper.insert(newData);
            return newData;
        }
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportStationCheckMantain reportStationCheckMantain) {
        return stationCheckMantainService.insert(reportStationCheckMantain);
    }

    @GetMapping("/delete")
    public int delete(Integer reportId) {
        return stationCheckMantainService.delete(reportId);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportStationCheckMantain reportStationCheckMantain) {
        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("数据表5修改")
                .setFreshVal(reportStationCheckMantain.toString())
                .setAction("修改")
                .setPreviousVal("")
                .build());
        return stationCheckMantainService.update(reportStationCheckMantain);
    }

    @ApiOperation(value = "表五测站检查维护记录表，根据日期及测站id进行筛选，每次导出一条数据,模板导出", httpMethod = "GET", notes = "表五测站检查维护记录表模板导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String mantainDate, Integer stationId) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = stationCheckMantainService.exportSheetByTemplate(mantainDate, stationId);
        // 设置excel的文件名称
        String excelName = "测站检查维护记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }



}
