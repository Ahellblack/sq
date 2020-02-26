package com.siti.wisdomhydrologic.operation.service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.DayData;
import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.mapper.StationRainConstrastMapper;
import com.siti.wisdomhydrologic.operation.service.StationRainConstrastService;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.operation.utils.StationIdUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by zyw on 2019/8/1.
 */
@Service
public class StationRainConstrastServiceImpl implements StationRainConstrastService {
    @Resource
    private StationRainConstrastMapper stationRainConstrastMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private StationRainConstrastServiceImpl stationRainConstrastService;

    Logger logger = LoggerFactory.getLogger(ReportStationRainConstrast.class);

    public List<ReportStationRainConstrastVo> getExcel(String date) {
        if (date == null) {
            //获取当前日期的上个月时间
            date = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        System.out.println(date);
        List<ReportStationRainConstrastVo> list = stationRainConstrastMapper.getByMonth(date);
        return list;
    }

    @Override
    public List<Map<String, Object>> getByMonth(String date) {
        if (date == null) {
            //获取当前日期的上个月时间
            date = DateOrTimeTrans.Date2TimeString3(new Date());
        }

        System.out.println(date);
        List<ReportStationRainConstrastVo> list = stationRainConstrastMapper.getByMonth(date);
        List<Map<String, Object>> returnList = new ArrayList<>();
        list.forEach(data -> {
            Map<String, Object> map = new HashMap<>();
            List<String> autoList = new ArrayList<>();
            List<String> baseList = new ArrayList<>();
            List<String> diffList = new ArrayList<>();
            map.put("stationName", data.getStationName());
            for (int i = 1; i <= 31; i++) {
                try {
                    Method method1 = data.getClass().getMethod("getDay" + i + "Auto");
                    Method method2 = data.getClass().getMethod("getDay" + i + "Base");
                    Method method3 = data.getClass().getMethod("getDay" + i + "Diff");

                    autoList.add((String) method1.invoke(data));
                    baseList.add((String) method2.invoke(data));
                    diffList.add((String) method3.invoke(data));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            map.put("id", data.getReportId());
            map.put("自动测报", autoList);
            map.put("基本站", baseList);
            map.put("差值", diffList);
            map.put("情况说明", data.getRemark());

            returnList.add(map);
        });

        return returnList;
    }

    public int update(ReportStationRainConstrastVo vo) {
        ConfigRiverStation RiverStation = configRiverStationMapper.getByAllName(vo.getStationName());
        DecimalFormat df = new DecimalFormat("0.0");
        ReportStationRainConstrast entity = new ReportStationRainConstrast();
        ReportStationRainConstrastVo station = stationRainConstrastMapper.getStationRainConstrast(RiverStation.getStationId(), vo.getDataYearMonth());
        ReportStationRainConstrast stationdata = stationRainConstrastMapper.getData(vo.getStationName(), vo.getDataYearMonth());

        /**
         *为DAY1至DAY31进行数据修改
         * */
        Method method = null;
        Method method1 = null;
        Method method2 = null;
        Method method3 = null;
        for (int i = 1; i <= 31; i++) {
            try {
                /**
                 * entity的数据 若base数据不为空，进行数据值差
                 * */
                method = entity.getClass().getMethod("setDay" + i, String.class);
                method1 = station.getClass().getMethod("getDay" + i + "Auto");
                method2 = vo.getClass().getMethod("getDay" + i + "Base");
                method3 = entity.getClass().getMethod("getDay" + i);
                if (method2.invoke(vo) != null && (!"".equals(method2.invoke(vo)))) {
                    Double auto = Double.parseDouble(method1.invoke(station).toString());
                    Double base = Double.parseDouble((method2.invoke(vo).toString()));
                    Double diff = ((Double.parseDouble(method1.invoke(station).toString()) - (Double.parseDouble(method2.invoke(vo).toString()))));
                    method.invoke(entity, df.format(auto) + "," + df.format(base) + "," + df.format((diff)));
                } else {
                    /**
                     * 若没有base数据 entity赋值原来的数据
                     * */
                    method.invoke(entity, method3.invoke(stationdata));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * 为修改值赋值
         * */
        entity.setCreateBy(vo.getCreateBy());
        entity.setRemark(vo.getRemark());
        entity.setUpdateBy(vo.getUpdateBy());
        entity.setUpdateTime(vo.getUpdateTime());
        entity.setCreateTime(vo.getCreateTime());
        entity.setStationName(vo.getStationName());
        entity.setDataYearMonth(vo.getDataYearMonth());

        List<ConfigRiverStation> allByStationName = configRiverStationMapper.getAllByStationName(entity.getStationName());
        entity.setStationCode(allByStationName.get(0).getStationId());
        /**
         * 设置total的值
         * */
        double autototal = 0;
        double basetotal = 0;
        double difftotal = 0;

        Method methodauto;
        Method methodbase;
        for (int i = 1; i <= 31; i++) {
            try {
                methodauto = station.getClass().getMethod("getDay" + i + "Auto");
                methodbase = vo.getClass().getMethod("getDay" + i + "Base");

                if (methodauto.invoke(station) != null) {
                    autototal += Double.parseDouble(methodauto.invoke(station).toString());
                    df.format(autototal);
                }
                if (methodbase.invoke(vo) != null) {
                    basetotal += Double.parseDouble(methodbase.invoke(vo).toString());
                    df.format(basetotal);
                }
                difftotal = autototal - basetotal;
            } catch (Exception e) {
                logger.error("异常信息{}", e);
                System.out.println("修改异常");
            }
        }
        entity.setTotal(df.format(autototal) + "," + df.format(basetotal) + "," + df.format(difftotal));

        try {
            return stationRainConstrastMapper.updateData(entity);
        } catch (Exception e) {
            return 0;
        }
    }

    public void insertOrUpdateMonthData(String month) throws Exception {
        if (month == null) {
            month = DateTransform.Date2String(new Date(), "yyyy-MM");
        }
        String startDay = month + "-01";
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateTransform.String2Date(startDay, "yyyy-MM-dd"));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) - 1; i++) {
            String date = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd");
            insertOrUpdateData(date);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

    }


    public void insertOrUpdateData(String today) throws Exception {

        if (today == null) {
            today = DateTransform.Date2String(new Date(), "yyyy-MM-dd");
        }
        DecimalFormat df = new DecimalFormat("0.0");
        //Calendar cal = Calendar.getInstance();
        //cal.setTime(DateTransform.String2Date(today, "yyyy-MM-dd"));
        List<Integer> idList = StationIdUtils.getTable7StationList();
        //每个测站id对应的day数据获取
        for (Integer data : idList) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateTransform.String2Date(today, "yyyy-MM-dd"));

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
            List<DayData> dayVo = stationRainConstrastMapper.getDayData(sensorCode, databaseName, DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd"));
            //新建雨量对比对象
            ReportStationRainConstrast entity = new ReportStationRainConstrast();
            //赋值测站信息
            ConfigRiverStation station = configRiverStationMapper.getAllByCode(data);
            entity.setStationName(station.getStationName());
            entity.setStationCode(station.getStationId());
            entity.setManageOrgName(station.getOrgName());
            entity.setManageOrgId(station.getOrgId());
            entity.setDataYearMonth(DateTransform.Date2String(cal.getTime(), "yyyy-MM"));
            cal.add(Calendar.MONTH, -1);
            entity.setCreateTime(DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
            entity.setUpdateTime(DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
            if (stationRainConstrastMapper.getAll(entity.getDataYearMonth(), data).size() == 0) {
                //  if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                entity.setTotal("0,0,0");
                for (int i = 1; i <= 31; i++) {
                    try {
                        Method method = entity.getClass().getMethod("setDay" + i, String.class);
                        method.invoke(entity, "0,0,0");
                    } catch (Exception e) {
                        System.out.println("月初表7数据自动添加出错");
                        logger.error("错误信息{}", e);
                    }
                }
                // 月初数据添加 day1 至 day31,total修改为 0,0,0
                try {
                    stationRainConstrastMapper.insert(entity);
                } catch (Exception e) {
                }
            }
            if (stationRainConstrastMapper.getAll(DateOrTimeTrans.Date2TimeString3(cal.getTime()), data).size() == 0) {
                entity.setTotal("0,0,0");
                entity.setDataYearMonth(DateOrTimeTrans.Date2TimeString3(cal.getTime()));
                for (int i = 1; i <= 31; i++) {
                    try {
                        Method method = entity.getClass().getMethod("setDay" + i, String.class);
                        method.invoke(entity, "0,0,0");
                    } catch (Exception e) {
                        System.out.println("月初表7数据自动添加出错");
                        logger.error("错误信息{}", e);
                    }
                }
                // 月初数据添加 day1 至 day31,total修改为 0,0,0
                try {
                    stationRainConstrastMapper.insert(entity);
                } catch (Exception e) {
                }
            }
            //当获取日雨量成功时a
            if (dayVo.size() > 0) {
                entity.setCreateTime(dayVo.get(0).getSensorDataUploadTime());
                entity.setUpdateTime(dayVo.get(0).getSensorDataUploadTime());
                entity.setDataYearMonth(dayVo.get(0).getSensorDataUploadTime().substring(0, 7));
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(DateOrTimeTrans.String2Date(dayVo.get(0).getSensorDataUploadTime()));
                cal1.add(Calendar.DAY_OF_MONTH, -1);
                //nowaday每月的第几天
                int nowaday = cal1.get(Calendar.DAY_OF_MONTH);
                entity.setDataYearMonth(DateOrTimeTrans.Date2TimeString3(cal1.getTime()));

                Method marray = null;


                ReportStationRainConstrastVo RainConstrast = stationRainConstrastMapper.getStationRainConstrast(entity.getStationCode(), entity.getDataYearMonth());
                double autototal = 0;
                double basetotal = 0;
                double difftotal = 0;
                Method methodauto;
                Method methodbase;

                try {
                    //反射到entity对象，对当前天的数据进行更改
                    marray = entity.getClass().getMethod("setDay" + nowaday, String.class);
                    //生成数据时，差值与自动生成值相同
                    Double autoDate = dayVo.get(0).getSensorDataValue();
                    Double diffDate = dayVo.get(0).getSensorDataValue();
                    marray.invoke(entity, autoDate + ",0," + diffDate);
                } catch (Exception e) {
                    logger.error("赋值每日降雨量数据异常");
                }

                //查询目前数据的total
                //String total = stationRainConstrastMapper.getTotal(entity.getStationCode(), entity.getDataYearMonth());
                String daynumber = "day" + nowaday;

                stationRainConstrastMapper.update(daynumber, dayVo.get(0).getSensorDataValue() + ",0," + dayVo.get(0).getSensorDataValue(), entity.getStationCode(), entity.getDataYearMonth());
                //update时赋值 total的值为原数据+dayVo数据
                for (int i = 1; i <= 31; i++) {
                    try {
                        Method method = entity.getClass().getMethod("getDay" + i);
                        method.invoke(entity);
                    } catch (Exception e) {
                        logger.error("月初表7数据自动添加出错,错误信息{}", e);
                    }
                }
                for (int i = 1; i <= 31; i++) {
                    try {
                        if (RainConstrast != null) {
                            methodauto = RainConstrast.getClass().getMethod("getDay" + i + "Auto");
                            methodbase = RainConstrast.getClass().getMethod("getDay" + i + "Base");

                            if (methodauto.invoke(RainConstrast) != null) {
                                autototal += Double.parseDouble(methodauto.invoke(RainConstrast).toString());
                                df.format(autototal);
                            }
                            if (methodbase.invoke(RainConstrast) != null) {
                                basetotal += Double.parseDouble(methodbase.invoke(RainConstrast).toString());
                                df.format(basetotal);
                            }
                            difftotal = autototal - basetotal;
                            Double thisDayAuto = dayVo.get(0).getSensorDataValue();
                            Double thisDayBase = Double.parseDouble(methodbase.invoke(RainConstrast).toString());
                            Double thisDayDiff = (dayVo.get(0).getSensorDataValue()-Double.parseDouble(methodbase.invoke(RainConstrast).toString()));
                            stationRainConstrastMapper.update(daynumber,  thisDayAuto+ ","+thisDayBase+"," +thisDayDiff, entity.getStationCode(), entity.getDataYearMonth());
                        }
                    } catch (Exception e) {
                        logger.error("异常信息", e);
                        System.out.println("修改异常");
                    }
                }
                entity.setTotal(df.format(autototal) + "," + df.format(basetotal) + "," + df.format(difftotal));

                try {
                    stationRainConstrastMapper.updateTotal(entity.getStationCode(), entity.getDataYearMonth(), entity.getTotal());

                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                //entity.setTotal((Double.parseDouble(total.split(",")[0]) + dayVo.get(0).getSensorDataValue()) + "," + total.split(",")[1] + "," + (Double.parseDouble(total.split(",")[2]) + dayVo.get(0).getSensorDataValue())); //修改当前月 当天的日数据
            }
        }
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
            TemplateExportParams params = new TemplateExportParams("sqexcelmodel/model7.xls");
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


