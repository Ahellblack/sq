package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageApplicationBrokenServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/7/31.
 */
@RestController
@RequestMapping("/applicationBroken")
@Api(value="应用程序及设备异常表controller",tags={"表四应用程序及设备异常表"})
public class ManageApplicationBrokenController {

    @Resource
    ManageApplicationBrokenMapper manageApplicationBrokenMapper;
    @Resource
    private ManageApplicationBrokenServiceImpl manageApplicationBrokenService;

    @ApiOperation(value = "表四应用程序及设备异常表查询", httpMethod = "GET", notes = "表四应用程序及设备异常表查询")
    @GetMapping("/getAll")
    public PageInfo<ReportManageApplicationBroken> selectAll(int page, int pageSize, String createDate,String stationName) {
        return manageApplicationBrokenService.getAll(page, pageSize, createDate,stationName);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportManageApplicationBroken reportManageApplicationBroken) {
        return manageApplicationBrokenService.insert(reportManageApplicationBroken);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageApplicationBroken reportManageApplicationBroken) {
        return manageApplicationBrokenService.update(reportManageApplicationBroken);
    }
    @ApiOperation(value = "表四应用程序及设备异常表删除", httpMethod = "GET", notes = "表四应用程序及设备异常表删除")
    @GetMapping("/delete")
    public int delete(Integer reportId) {
        return manageApplicationBrokenService.delete(reportId);
    }

    @ApiOperation(value = "表四应用程序及设备异常表自动添加入库接口,后台人员使用", httpMethod = "GET", notes = "自动添加入库接口")
    @GetMapping("/insertDataMantain")
    public int insertDataMantain(String date) {
        return manageApplicationBrokenService.insertDataMantain(date);
    }

    @ApiOperation(value = "表四应用程序及设备异常表EXCEL模板导出", httpMethod = "GET", notes = "表四应用程序及设备异常表EXCEL模板导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String createTime,String stationName) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(createTime,stationName);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "故障情况记录表";
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
    public Workbook exportSheetByTemplate(String createTime,String stationName) {
        // 查询数据,此处省略
        List<ReportManageApplicationBroken> list = manageApplicationBrokenMapper.getAll(createTime,stationName);
        for (int i = 0; i < list.size(); i++) {
            ReportManageApplicationBroken data = list.get(i);
            data.setReportId(i+1);
            if (data.getCreateTime()!= null)data.setCreateTime(data.getCreateTime().substring(8,10)+"日"+data.getCreateTime().substring(11,13)+"时");
            if (data.getBrokenResponseTime()!= null)data.setBrokenResponseTime(data.getBrokenResponseTime().substring(8,10)+"日"+data.getBrokenResponseTime().substring(11,13)+"时");
            if (data.getBrokenAskToResolveTime()!= null)data.setBrokenAskToResolveTime(data.getBrokenAskToResolveTime().substring(8,10)+"日"+data.getBrokenAskToResolveTime().substring(11,13)+"时");
            if (data.getBrokenrRequestReportTime()!= null)data.setBrokenrRequestReportTime(data.getBrokenrRequestReportTime().substring(8,10)+"日"+data.getBrokenrRequestReportTime().substring(11,13)+"时");
        }
        int count1 = 0;
        // 设置导出配置
        // 获取导出excel指定模版
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath() ;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String rootPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
        TemplateExportParams params = new TemplateExportParams(logFilePath+"sqexcelmodel/model4.xls");
        File f = new File(this.getClass().getResource("/").getPath());
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表四");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }


}
