package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.operation.entity.RecordDeviceReplace;
import com.siti.wisdomhydrologic.operation.mapper.RecordDeviceReplaceMapper;
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
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/8/23.
 * 表8
 */
@RequestMapping("/recordDevice")
@RestController
@Api(value="测站设备变更记录表controller",tags={"测站设备变更记录表"})
public class RecordDeviceReplaceController {

    @Resource
    private RecordDeviceReplaceMapper mapper;

    @ApiOperation(value = "表八测站设备变更记录表查询", httpMethod = "GET", notes = "测站设备变更记录表")
    @GetMapping("/getAll")
    public List<RecordDeviceReplace> getAll(String stationName,String createDate) {
        return mapper.getAll(stationName,createDate);
    }

    @GetMapping("/delete")
    public int delete(Integer reportId) {
        return mapper.deleteData(reportId);
    }

    @PostMapping("/update")
    public int update(RecordDeviceReplace recordDeviceReplace) {
        try{
            return mapper.updateByPrimaryKey(recordDeviceReplace);
        }catch (Exception e){
            return 0;
        }
    }

    @PostMapping("/insert")
    public int insert(RecordDeviceReplace recordDeviceReplace) {
        try{
            return mapper.insert(recordDeviceReplace);

        }catch (Exception e){
            return 0;
        }
    }

    @ApiOperation(value = "表八测站设备变更记录表模板导出", httpMethod = "GET", notes = "表八测站设备变更记录表模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String stationName,String createDate) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(stationName,createDate);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "测站设备变更记录表";
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
    public Workbook exportSheetByTemplate(String stationName,String createDate) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        // 查询数据,此处省略
        List<RecordDeviceReplace> list = mapper.getAll(stationName,createDate);
        for (int i = 0; i < list.size(); i++) {
            RecordDeviceReplace data = list.get(i);
            data.setReportId(i + 1);
            if (data.getCreateTime() != null)
                data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            if (data.getReplaceDate() != null) data.setReplaceDate(data.getReplaceDate().substring(8, 10) + "日");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model8.xls");

        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表八");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createDate);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }


}
