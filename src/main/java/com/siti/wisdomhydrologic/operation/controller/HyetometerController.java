package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.mapper.HyetometerMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.HyetometerServiceImpl;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/7/26.
 */
@RequestMapping("/hyetometer")
@RestController
@Api(value="雨滴表controller",tags={"雨量计滴水实验记录表"})
public class HyetometerController {

    @Resource
    private HyetometerServiceImpl reportHyetometerService;

    @Resource
    private HyetometerMapper reportHyetometerMapper;

    @ApiOperation(value = "雨滴表查询", httpMethod = "GET", notes = "雨滴表查询")
    @GetMapping("/getAll")
    public List<ReportHyetometerTest> getAll(String createTime,String stationName){
        return reportHyetometerService.getAll(createTime,stationName);
    }

    @GetMapping("/deleteBy")
    public int deleteBy(Integer reportId){
        return reportHyetometerService.delByReportId(reportId);
    }

    @GetMapping("/deleteChosen")
    public int deleteByList(@RequestBody List<Integer> reportIdList){
        return reportHyetometerService.delByReportIdList(reportIdList);
    }
    @PostMapping("/insert")
    public int insert(@RequestBody ReportHyetometerTest reportHyetometer){

        return reportHyetometerService.insert(reportHyetometer);
    }
    @PostMapping("/update")
    public int update(@RequestBody ReportHyetometerTest reportHyetometer){
        return reportHyetometerService.update(reportHyetometer);
    }

    @ApiOperation(value = "雨滴表excel导出接口", httpMethod = "GET", notes = "雨滴表excel导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response,String createTime,String stationName) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(createTime,stationName);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "雨量计滴水实验记录表";
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
        List<ReportHyetometerTest> list = reportHyetometerMapper.getAll(createTime,stationName);
        for (int i = 0; i < list.size(); i++) {
            ReportHyetometerTest data = list.get(i);
            data.setReportId(i+1);
           // if (data.getCreateTime()!= null)data.setCreateTime(data.getCreateTime().substring(8,10)+"日"+data.getCreateTime().substring(11,13)+"时");
       }
        int count1 = 0;
        // 设置导出配置


        //获取动态路径
        File f = new File(this.getClass().getResource("/").getPath());
        /*String programpath = f.getPath().split("target")[0] ;
        System.out.println(programpath);
        String filepath = "resources/sqexcelmodel/model4.xls";
*/
        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath() ;
        // 获取导出excel指定模版
         TemplateExportParams params = new TemplateExportParams(logFilePath+"sqexcelmodel/model6.xls");
        // 标题开始行
        // params.setHeadingStartRow(0);
        // 标题行数
        // params.setHeadingRows(2);
        // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
        params.setSheetName("表六");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("date", createTime);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        // 导出excel
        return workbook;
    }

}
