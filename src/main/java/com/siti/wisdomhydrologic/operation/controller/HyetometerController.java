package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.HyetometerMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.HyetometerServiceImpl;
import com.siti.wisdomhydrologic.operation.vo.ReportListVo;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zyw on 2019/7/26.
 */
@RequestMapping("/hyetometer")
@RestController
@Api(value = "雨滴表controller", tags = {"雨量计滴水实验记录表"})
public class HyetometerController {

    @Resource
    private HyetometerServiceImpl reportHyetometerService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private HyetometerMapper reportHyetometerMapper;
    @Resource
    private SysLogMapper sysLogMapper;

    @ApiOperation(value = "雨滴表查询", httpMethod = "GET", notes = "雨滴表查询")
    @GetMapping("/getAll")
    public List<ReportHyetometerTest> getAll(HttpSession session, String createTime, String stationId) {
        return reportHyetometerService.getAll(session, createTime, stationId);
    }

    @GetMapping("/deleteBy")
    public int deleteBy(Integer reportId,HttpSession session) {
        int i = reportHyetometerService.delByReportId(reportId);

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("从数据表6删除"+reportId)
                .setFreshVal("")
                .setAction("删除")
                .setPreviousVal("")
                .build());
        return i;
    }

    @GetMapping("/deleteChosen")
    public int deleteByList(@RequestBody List<Integer> reportIdList) {
        return reportHyetometerService.delByReportIdList(reportIdList);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportHyetometerTest reportHyetometer,HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("添加数据")
                .setFreshVal(reportHyetometer.toString())
                .setAction("添加")
                .setPreviousVal("")
                .build());
        return reportHyetometerService.insert(reportHyetometer);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportHyetometerTest reportHyetometer,HttpSession session) {

        User user = (User) userInfoService.get();
        sysLogMapper.insertUserOprLog( new SysLog.builder()
                .setUsername(user.getUserName())
                .setOperateDes("修改数据")
                .setFreshVal(reportHyetometer.toString())
                .setAction("添加")
                .setPreviousVal("")
                .build());
        return reportHyetometerService.update(reportHyetometer);
    }

    @ApiOperation(value = "雨滴表excel导出接口", httpMethod = "GET", notes = "雨滴表excel导出")
    @GetMapping("/getExcel")
    @ResponseBody
    public String exportExcelTest(HttpSession session, HttpServletResponse response, String createTime, String stationId, @RequestParam  List<Integer> reportIdList) throws UnsupportedEncodingException {

        // 获取workbook对象   easypoi   easyexcell
        Workbook workbook = exportSheetByTemplate(session, createTime, stationId, reportIdList);
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
    public Workbook exportSheetByTemplate(HttpSession session, String createTime, String stationId,  List<Integer> reportIdList) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());        // 查询数据,此处省略

        List<ReportHyetometerTest> list = reportHyetometerMapper.getAll(createTime, stationId, orgList.get(0).getId());
        /**
         * 选择导出reportList替换全部list
         * */
        if (reportIdList.size() > 0) {
            List<ReportHyetometerTest> reportlist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (reportIdList.contains(list.get(i).getReportId())) {
                    reportlist.add(list.get(i));
                }
            }
            list = reportlist;
        }
        for (int i = 0; i < list.size(); i++) {
            ReportHyetometerTest data = list.get(i);
            data.setReportId(i + 1);
            data.getTimeDuration();
            data.getStartTime();
            data.getEndTime();
            if (data.getTimeDuration() != null)
                data.setTimeDuration(data.getTimeDuration().substring(11, 13) + "时" + data.getTimeDuration().substring(14, 16) + "分" + data.getTimeDuration().substring(17, 19) + "秒");
            if (data.getStartTime() != null)
                data.setStartTime(data.getStartTime().substring(11, 13) + "时" + data.getStartTime().substring(14, 16) + "分" + data.getStartTime().substring(17, 19) + "秒");
            if (data.getEndTime() != null)
                data.setEndTime(data.getEndTime().substring(11, 13) + "时" + data.getEndTime().substring(14, 16) + "分" + data.getEndTime().substring(17, 19) + "秒");
            if (data.getCreateTime() != null)
                data.setCreateTime(data.getCreateTime().substring(0, 4) + "年" + data.getCreateTime().substring(5, 7) + "月" + data.getCreateTime().substring(8, 10) + "日");

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
        String logFilePath = url.getPath();
        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model6.xls");
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

    /**
     * 导出全部
     * */
    @ApiOperation(value = "雨滴表excel导出接口", httpMethod = "GET", notes = "雨滴表excel导出")
    @GetMapping("/getExcelAll")
    @ResponseBody
    public String exportExcelTest(HttpSession session, HttpServletResponse response, String createTime, String stationId) throws UnsupportedEncodingException {

        // 获取workbook对象   easypoi   easyexcell
        Workbook workbook = exportSheetByTemplate(session, createTime, stationId);
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
    public Workbook exportSheetByTemplate(HttpSession session, String createTime, String stationId) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());        // 查询数据,此处省略

        List<ReportHyetometerTest> list = reportHyetometerMapper.getAll(createTime, stationId, orgList.get(0).getId());

        for (int i = 0; i < list.size(); i++) {
            ReportHyetometerTest data = list.get(i);
            data.setReportId(i + 1);
            data.getTimeDuration();
            data.getStartTime();
            data.getEndTime();
            if (data.getTimeDuration() != null)
                data.setTimeDuration(data.getTimeDuration().substring(11, 13) + "时" + data.getTimeDuration().substring(14, 16) + "分" + data.getTimeDuration().substring(17, 19) + "秒");
            if (data.getStartTime() != null)
                data.setStartTime(data.getStartTime().substring(11, 13) + "时" + data.getStartTime().substring(14, 16) + "分" + data.getStartTime().substring(17, 19) + "秒");
            if (data.getEndTime() != null)
                data.setEndTime(data.getEndTime().substring(11, 13) + "时" + data.getEndTime().substring(14, 16) + "分" + data.getEndTime().substring(17, 19) + "秒");
            if (data.getCreateTime() != null)
                data.setCreateTime(data.getCreateTime().substring(0, 4) + "年" + data.getCreateTime().substring(5, 7) + "月" + data.getCreateTime().substring(8, 10) + "日");
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
        String logFilePath = url.getPath();
        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams(logFilePath + "sqexcelmodel/model6.xls");
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
