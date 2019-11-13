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
import com.siti.wisdomhydrologic.operation.utils.WorkBookUtils;
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
 * Created by zyw on 2019/7/30.
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
    public int update(@RequestBody ReportManageDataMantain reportManageDataMantain) {

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
    public int insert(@RequestBody ReportManageDataMantain reportManageDataMantain) {

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
        return reportManageDataMantainService.insertAbnormalData();
    }

    @ApiOperation(value = "表二模板导出", httpMethod = "GET", notes = "表二模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String stationId, String alterType, String createTime,HttpSession session, @RequestParam List<Integer> reportIdList) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = reportManageDataMantainService.exportSheetByTemplate(stationId, alterType, createTime,session,reportIdList);
        // 判断数据
        if (workbook == null) {return "fail";}
        // 设置excel的文件名称
        String excelName = "系统数据修正登记表";
        return WorkBookUtils.download(response, workbook, excelName);
    }



    @ApiOperation(value = "表二模板导出", httpMethod = "GET", notes = "表二模板导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String stationId, String alterType, String createTime,HttpSession session) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = reportManageDataMantainService.exportSheetByTemplate(stationId, alterType, createTime,session);
        // 判断数据
        if (workbook == null) {return "fail";}
        // 设置excel的文件名称
        String excelName = "系统数据修正登记表";
        return WorkBookUtils.download(response, workbook, excelName);
    }





}
