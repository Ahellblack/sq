package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.mapper.StationCheckMantainMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.StationCheckMantainServiceImpl;
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
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/7/31.
 */
@RequestMapping("/stationCheck")
@RestController
@Api(value = "测站检查维护记录表controller", tags = {"测站检查维护记录表"})
public class StationCheckMantainController {
    @Resource
    private StationCheckMantainServiceImpl stationCheckMantainService;
    @Resource
    private StationCheckMantainMapper stationCheckMantainMapper;

    @ApiOperation(value = "表五测站检查维护记录表查询，根据日期及测站id进行筛选，每次导出一条数据", httpMethod = "GET", notes = "表五测站检查维护记录表查询")
    @GetMapping("/getAll")
    public ReportStationCheckMantain getAll(String mantainDate, String stationId) {

        return stationCheckMantainMapper.getByStationId(mantainDate, stationId);
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
        return stationCheckMantainService.update(reportStationCheckMantain);
    }

    @ApiOperation(value = "表五测站检查维护记录表，根据日期及测站id进行筛选，每次导出一条数据,模板导出", httpMethod = "GET", notes = "表五测站检查维护记录表模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String mantainDate, String stationId) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(mantainDate, stationId);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "测站检查维护记录表";
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
    public Workbook exportSheetByTemplate(@Param("mantainDate") String mantainDate, @Param("stationId") String stationId) {
        // 查询数据,此处省略
        ReportStationCheckMantain entity = stationCheckMantainMapper.getByStationId(mantainDate, stationId);
        /*for (int i = 0; i < list.size(); i++) {
            ReportInspectionMaintenance data = list.get(i);
            data.setReportId(i+1);
         }*/
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String rootPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model5.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表五");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", entity);
        map.put("date", mantainDate);
        System.out.println(map.get("data").toString());
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

}
