package com.siti.wisdomhydrologic.operation.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.mapper.StationCheckMantainMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.StationCheckMantainServiceImpl;
import com.siti.wisdomhydrologic.operation.utils.WorkBookUtils;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
//    @GetMapping("/getAll")
//    public ReportStationCheckMantain getAll(String mantainDate, String stationId) {
//
//        ReportStationCheckMantain byStationId = stationCheckMantainMapper.getByStationId(mantainDate, stationId);
//        if (byStationId!=null){
//            return byStationId;
//        }else {
//            ConfigRiverStation allByCode = configRiverStationMapper.getAllByCode(stationId);
//            ReportStationCheckMantain newData = new ReportStationCheckMantain(mantainDate, stationId, allByCode.getStationName());
//            stationCheckMantainMapper.insert(newData);
//            return newData;
//        }
//    }

    @GetMapping("/getList")
    public PageInfo<ReportStationCheckMantain> getList(@RequestParam(value = "maintainDate") String maintainDate,
                                                   @RequestParam(value = "stationId", required = false)String stationId,
                                                   @RequestParam(value = "page") Integer page,
                                                   @RequestParam(value = "pageSize") Integer pageSize) {
        return stationCheckMantainService.getList(maintainDate,stationId,page,pageSize);
    }

    @PostMapping("/insert")
    public JSONObject insert(@RequestBody ReportStationCheckMantain reportStationCheckMantain) {
        JSONObject jsonObject = new JSONObject();
        int status = 0;
        String message = "";


        try {
            if(null == reportStationCheckMantain.getStationId() || null ==reportStationCheckMantain.getStationName()){
                status = 0;
                message = "测站信息缺失";
            } else if (null == reportStationCheckMantain.getMantainDate()){
                status = 0;
                message = "维护时间缺失！";
            } else{
                if(stationCheckMantainMapper.insert(reportStationCheckMantain) == 1){
                    User user = (User) userInfoService.get();
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("数据表5添加")
                            .setFreshVal(reportStationCheckMantain.toString())
                            .setAction("添加")
                            .setPreviousVal("")
                            .build());
                    status = 1;
                    message = "添加成功！";
                }
                else{
                    status = 0;
                    message = "添加失败！";
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            status = -1;
            message = "异常错误！";
        }

        jsonObject.put("status", status);
        jsonObject.put("message", message);
        return jsonObject;
    }

    @GetMapping("/delete")
    public JSONObject delete(Integer reportId) {
        JSONObject jsonObject = new JSONObject();
        int status = 0;
        String message = "";
        try {
            if(null == reportId){
                status = 0;
                message = "记录编号缺失！";
            } else{
                if(stationCheckMantainMapper.deleteById(reportId) == 1){
                    User user = (User) userInfoService.get();
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("数据表5删除")
                            .setFreshVal(reportId+"")
                            .setAction("删除")
                            .setPreviousVal("")
                            .build());

                    status = 1;
                    message = "删除成功！";
                }
                else{
                    status = 0;
                    message = "删除失败！";
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            status = -1;
            message = "异常错误！";
        }

        jsonObject.put("status", status);
        jsonObject.put("message", message);
        return jsonObject;
    }

    @PostMapping("/update")
    public JSONObject update(@RequestBody ReportStationCheckMantain reportStationCheckMantain) {
        JSONObject jsonObject = new JSONObject();
        int status = 0;
        String message = "";

        try {
            if(0 == reportStationCheckMantain.getReportId()){
                status = 0;
                message = "测站信息缺失";
            } else{
                if(stationCheckMantainMapper.update(reportStationCheckMantain) == 1){
                    User user = (User) userInfoService.get();
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("数据表5修改")
                            .setFreshVal(reportStationCheckMantain.toString())
                            .setAction("修改")
                            .setPreviousVal("")
                            .build());

                    status = 1;
                    message = "修改成功！";
                }
                else{
                    status = 0;
                    message = "修改失败！";
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            status = -1;
            message = "异常错误！";
        }

        jsonObject.put("status", status);
        jsonObject.put("message", message);
        return jsonObject;
    }

    @ApiOperation(value = "表五测站检查维护记录表，根据日期及测站id进行筛选，每次导出一条数据,模板导出", httpMethod = "GET", notes = "表五测站检查维护记录表模板导出")
    @GetMapping("/exportOne")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response,Integer reportId) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = stationCheckMantainService.exportSheetByTemplate(reportId);
        // 设置excel的文件名称
        String excelName = "测站检查维护记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }

    @ApiOperation(value = "表五测站检查维护记录表，模板导出,多条分多sheet", httpMethod = "GET", notes = "表五测站检查维护记录表模板导出")
    @GetMapping("/exportAll")
    @ResponseBody
    public String exportExcel(HttpServletResponse response, String maintainDate, String stationId) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = stationCheckMantainService.exportAllRecord(maintainDate,stationId);
        // 设置excel的文件名称
        String excelName = "测站检查维护记录表";
        return WorkBookUtils.download(response, workbook, excelName);
    }
}
