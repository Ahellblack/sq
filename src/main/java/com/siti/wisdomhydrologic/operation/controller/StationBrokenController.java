package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.service.Impl.StationBrokenServiceImpl;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dell on 2019/7/31.
 * 表三
 */
@RequestMapping("/stationBroken")
@RestController
@Api(value = "应用程序及设备故障登记表controller", tags = {"表三应用程序及设备故障登记表"})
public class StationBrokenController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private StationBrokenServiceImpl stationBrokenService;

    @ApiOperation(value = "表三应用程序及设备故障登记表查询", httpMethod = "GET", notes = "表三应用程序及设备故障登记表查询")
    @RequestMapping("/getAll")
    public List<ReportStationBroken> getAll(String createDate, String applicationEquipName) {
        return stationBrokenService.getAll(createDate, applicationEquipName);
    }

    @GetMapping("/delete")
    public int delete(Integer reportId) {
        return stationBrokenService.delete(reportId);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportStationBroken reportStationBroken) {
        return stationBrokenService.update(reportStationBroken);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportStationBroken reportStationBroken) {
        System.out.println(reportStationBroken);
        return stationBrokenService.insert(reportStationBroken);
    }

    @ApiOperation(value = "表三应用程序及设备故障登记表模板导出", httpMethod = "GET", notes = "表三应用程序及设备故障登记表模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String createDate, String applicationEquipName, List<Integer> reportIdList) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(createDate, applicationEquipName, reportIdList);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "应用程序及设备故障登记表";
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
    public Workbook exportSheetByTemplate(String createDate, String applicationEquipName,@RequestBody List<Integer> reportIdList) {
        // 查询数据,此处省略
        List<ReportStationBroken> list = stationBrokenService.getAll(createDate, applicationEquipName);

        /**
         * 选择导出reportList替换全部list
         * */
        if (reportIdList.size() > 0) {
            List<ReportStationBroken> reportlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (reportIdList.contains(list.get(i).getReportId())) {
                    reportlist.add(list.get(i));
                }
            }
            list = reportlist;
        }
        for (int i = 0; i < list.size(); i++) {
            ReportStationBroken data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null && data.getCreateTime().length() > 13)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResponseTime() != null && data.getBrokenResponseTime().length() > 13)
                data.setBrokenResponseTime(data.getBrokenResponseTime().substring(8, 10) + "日" + data.getBrokenResponseTime().substring(11, 13) + "时");
            if (data.getBrokenHappenTime() != null && data.getBrokenHappenTime().length() > 13)
                data.setBrokenHappenTime(data.getBrokenHappenTime().substring(8, 10) + "日" + data.getBrokenHappenTime().substring(11, 13) + "时");
            if (data.getBrokenResolveCreateTime() != null && data.getBrokenResolveCreateTime().length() > 13)
                data.setBrokenResolveCreateTime(data.getBrokenResolveCreateTime().substring(8, 10) + "日" + data.getBrokenResolveCreateTime().substring(11, 13) + "时");
            if (data.getBrokenResolveTime() != null && data.getBrokenResolveTime().length() > 13)
                data.setBrokenResolveTime(data.getBrokenResolveTime().substring(8, 10) + "日" + data.getBrokenResolveTime().substring(11, 13) + "时");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model3.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表三");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createDate);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }


}
