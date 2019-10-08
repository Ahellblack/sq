package com.siti.wisdomhydrologic.operation.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.DayData;
import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.mapper.StationRainConstrastMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.StationRainConstrastServiceImpl;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.StationIdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;


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
    @PostMapping("/update")
    public int update(@RequestBody ReportStationRainConstrastVo vo) {
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
    public void insertData(String day) throws Exception {
        Date today = DateTransform.String2Date(day, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        List<Integer> idList = StationIdUtils.getTable7StationList();

        //每个测站id对应的day数据获取
        idList.forEach(data -> {
            Calendar calendar = Calendar.getInstance();
            String databaseName = null;
            if (calendar.get(Calendar.YEAR) <= 2020) {
                databaseName = "history_day_sensor_data_2016_2020";
            } else {
                databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
            }
            //System.out.println(databaseName);
            //获取每个测站的日雨量数据
            String sensorCode = data + "84";
            //System.out.println(sensorCode);
            List<DayData> dayVo = stationRainConstrastMapper.getChosenDayData(day, sensorCode, databaseName);
            //新建雨量对比对象
            ReportStationRainConstrast entity = new ReportStationRainConstrast();
            //赋值测站信息
            ConfigRiverStation station = configRiverStationMapper.getAllByCode(data);
            entity.setStationName(station.getStationName());
            entity.setStationCode(station.getStationId());
            entity.setManageOrgName(station.getOrgName());
            entity.setManageOrgId(station.getOrgId());
            entity.setDataYearMonth(DateTransform.Date2String(cal.getTime(), "yyyy-MM"));
            entity.setCreateTime(DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
            entity.setUpdateTime(DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
            if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                //月初赋值
                entity.setTotal("0,0,0");
                for (int i = 1; i <= 31; i++) {
                    try {
                        Method method = entity.getClass().getMethod("setDay" + i, String.class);
                        method.invoke(entity, "0,0,0");
                    } catch (Exception e) {
                        //System.out.println("月初表7数据自动添加出错");
                        //logger.error("错误信息{}", e);
                    }
                }
                stationRainConstrastMapper.insert(entity);
            }
            //当获取日雨量成功时
            if (dayVo.size() > 0) {
                entity.setCreateTime(dayVo.get(0).getSensorDataUploadTime());
                entity.setUpdateTime(dayVo.get(0).getSensorDataUploadTime());
                entity.setDataYearMonth(dayVo.get(0).getSensorDataUploadTime().substring(0, 7));
                //nowaday每月的第几天
                int nowaday = cal.get(Calendar.DAY_OF_MONTH);

                //查询目前数据的total
                String total = stationRainConstrastMapper.getTotal(entity.getStationCode(), entity.getDataYearMonth());
                String daynumber = "day" + nowaday;

                //update时赋值 total的值为原数据+dayVo数据
                entity.setTotal(
                        (Double.parseDouble(total.split(",")[0]) + dayVo.get(0).getSensorDataValue())
                        + ","
                        + total.split(",")[1]
                        + ","
                        + (Double.parseDouble(total.split(",")[2])+ dayVo.get(0).getSensorDataValue())
                );
                //修改当前月 当天的日数据
                stationRainConstrastMapper.update(daynumber, dayVo.get(0).getSensorDataValue() + ",0," + dayVo.get(0).getSensorDataValue(), entity.getStationCode(), entity.getDataYearMonth(), entity.getTotal());
            }
        });

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
                ReportStationRainConstrastVo vo = list.get(i);
                for (int j = 1; j <= 31; j++) {
                    try {
                        Method methodautoget = vo.getClass().getMethod("getDay" + j + "Auto");
                        Method methodbaseget = vo.getClass().getMethod("getDay" + j + "Base");
                        Method methoddiffget = vo.getClass().getMethod("getDay" + j + "Diff");
                        Method methodautoset = vo.getClass().getMethod("setDay" + j + "Auto", String.class);
                        Method methodbaseset = vo.getClass().getMethod("setDay" + j + "Base", String.class);
                        Method methoddiffset = vo.getClass().getMethod("setDay" + j + "Diff", String.class);


                        /**
                         * 导出时设置 值为0的部分 为 空字符串
                         * */
                        try {
                            if (Double.parseDouble(methodautoget.invoke(vo).toString()) == 0) {
                                methodautoset.invoke(vo, "");
                            }
                            if (Double.parseDouble(methodbaseget.invoke(vo).toString()) == 0) {
                                methodbaseset.invoke(vo, "");
                            }
                            if (Double.parseDouble(methoddiffget.invoke(vo).toString()) == 0) {
                                methoddiffset.invoke(vo, "");
                            }
                            if (Double.parseDouble(vo.getAutoTotal()) == 0) {
                                vo.setAutoTotal("");
                            }
                            if (Double.parseDouble(vo.getBaseTotal()) == 0) {
                                vo.setBaseTotal("");
                            }
                            if (Double.parseDouble(vo.getDiffTotal()) == 0) {
                                vo.setDiffTotal("");
                            }

                        } catch (Exception e) {
                            //System.out.println("导出异常");
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                }
                map.put(dataname, vo);

            }
            map.put("date", createTime);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            // 导出excel
            return workbook;
        }
    }
}
