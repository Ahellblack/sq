package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.mapper.StationRainConstrastMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.StationRainConstrastServiceImpl;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
 * Created by dell on 2019/8/1.
 */
@RequestMapping("/rainConstrast")
@RestController
@Api(value = "测站降水量数据比对表controller", tags = {"测站降水量数据比对表"})
public class StationRainConstrastController {
    @Resource
    private StationRainConstrastServiceImpl stationRainConstrastService;
    @Resource
    private StationRainConstrastMapper stationRainConstrastMapper;

    /**
     * 获取某个月的全部站点数据对表
     * 当没有选择具体某个月时,默认返回上个月的数据
     *
     * @PARAM date 选择查询的月份
     */
    @ApiOperation(value = "表七测站降水量数据比对表，默认查询上月数据", httpMethod = "GET", notes = "表七测站降水量数据比对表查询")
    @GetMapping("/getAll")
    public List<Map<String, Object>> getByMonth(String date) {
        List<Map<String, Object>> list = stationRainConstrastService.getByMonth(date);
        return list;
    }

    /**
     * 客户可修改基本站数值
     */
    @GetMapping("/update")
    public int update(ReportStationRainConstrastVo vo) {

        return stationRainConstrastService.update(vo);
    }


    @GetMapping("/getAllAuto")
    public List<ReportStationRainConstrastVo> getByAutoMonth(Date date) {
        return stationRainConstrastService.getAutoByMonth(date);
    }

    @GetMapping("/getAllBase")
    public List<ReportStationRainConstrastVo> getByBaseMonth(Date date) {
        return stationRainConstrastService.getBaseByMonth(date);
    }

    @GetMapping("/getAllDiff")
    public List<ReportStationRainConstrastVo> getByDiffMonth(Date date) {
        return stationRainConstrastService.getDiffByMonth(date);
    }

    @GetMapping("/insertOrUpdate")
    public void insertData() throws Exception {
        stationRainConstrastService.insertOrUpdateData();
    }

    @ApiOperation(value = "表七测站降水量数据比对表，默认查询上月数据导出", httpMethod = "GET", notes = "表七测站降水量数据比对表查询导出excel")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response, String createTime) throws UnsupportedEncodingException {
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate(createTime);
        // 判断数据
        if (workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "测站降水量数据比对表";
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
    public Workbook exportSheetByTemplate(String createTime) {
        // 查询数据,此处省略
        List<ReportStationRainConstrastVo> list = stationRainConstrastService.getExcel(createTime);

        if (list.size() != 11) {
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                ReportStationRainConstrastVo data = list.get(i);
                data.setReportId(i + 1);
                if (data.getCreateTime() != null && data.getCreateTime().length() > 13)
                    data.setCreateTime(data.getCreateTime().substring(8, 10) + "日" + data.getCreateTime().substring(11, 13) + "时");
            }
            int count1 = 0;
            // 设置导出配置
            // 获取导出excel指定模版
            URL url = this.getClass().getClassLoader().getResource("");
            String logFilePath = url.getPath();
            TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model7.xls");
            // 标题开始行
            // params.setHeadingStartRow(0);
            // 标题行数
            // params.setHeadingRows(2);
            // 设置sheetName,若不设置该参数,则使用得原本得sheet名称
            params.setSheetName("表七");
            Map<String, Object> map = new HashMap<String, Object>();
            //对查询的List进行遍历 把对象存于map
            for (int i = 0; i < list.size(); i++) {
                String dataname = "data" + (i + 1);
                map.put(dataname, list.get(i));
            }
            map.put("date", createTime);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            // 导出excel
            return workbook;
        }
    }
}
