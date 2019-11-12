package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.DayData;
import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.mapper.StationRainConstrastMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.StationRainConstrastServiceImpl;
import com.siti.wisdomhydrologic.operation.utils.WorkBookUtils;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.operation.utils.StationIdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zyw on 2019/8/1.
 */
@RequestMapping("/rainConstrast")
@RestController
@Api(value = "测站降水量数据比对表controller", tags = {"测站降水量数据比对表"})
public class StationRainConstrastController {
    @Resource
    private StationRainConstrastServiceImpl stationRainConstrastService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 获取某个月的全部站点数据对表
     * 当没有选择具体某个月时,默认返回上个月的数据
     *
     * @PARAM date 选择查询的月份
     */
    @GetMapping("/getAll")
    public List<Map<String, Object>> getByMonth(String date) {
        List<Map<String, Object>> list = stationRainConstrastService.getByMonth(date);
        return list;
    }

    /**
     * 客户可修改基本站数值
     */
    @PostMapping("/update")
    public int update(@RequestBody ReportStationRainConstrastVo vo, HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog(new SysLog.builder().setUsername(user.getUserName()).setOperateDes("数据表7修改").setFreshVal(vo.toString()).setAction("修改").setPreviousVal("").build());
        return stationRainConstrastService.update(vo);
    }

    /**
     * 手动补充某日期的雨量数据
     * @param day 补充日期
     */
    @GetMapping("/insertOrUpdate")
    public void insertData(String day) throws Exception {
        stationRainConstrastService.insertOrUpdateData(day);
    }

    @ApiOperation(value = "表七测站降水量数据比对表，默认查询上月数据导出", httpMethod = "GET", notes = "表七测站降水量数据比对表查询导出excel")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String createTime) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = stationRainConstrastService.exportSheetByTemplate(createTime);
        // 设置excel的文件名称
        String excelName = "测站降水量数据比对表";
        return WorkBookUtils.download(response, workbook, excelName);
    }


}
