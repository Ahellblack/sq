package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.DayData;
import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.mapper.StationRainConstrastMapper;
import com.siti.wisdomhydrologic.operation.service.StationRainConstrastService;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.StationIdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by dell on 2019/8/1.
 */
@Service
public class StationRainConstrastServiceImpl implements StationRainConstrastService {
    @Resource
    private StationRainConstrastMapper stationRainConstrastMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;


    @Override
    public List<ReportStationRainConstrastVo> getAutoByMonth(Date date) {
        return stationRainConstrastMapper.getAutoByMonth(date);
    }

    @Override
    public List<ReportStationRainConstrastVo> getBaseByMonth(Date date) {
        return stationRainConstrastMapper.getBaseByMonth(date);
    }

    @Override
    public List<ReportStationRainConstrastVo> getDiffByMonth(Date date) {
        return stationRainConstrastMapper.getDiffByMonth(date);
    }

    @Override
    public List<ReportStationRainConstrast> getAll(Date date) {
        return null;
    }

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
            map.put("自动测报", autoList);
            map.put("基本站", baseList);
            map.put("差值", diffList);
            map.put("情况说明", data.getRemark());

            returnList.add(map);
        });

        return returnList;
    }

    public int update(ReportStationRainConstrastVo vo) {

        ReportStationRainConstrast entity = new ReportStationRainConstrast();

        ReportStationRainConstrastVo station = stationRainConstrastMapper.getStation(vo.getStationName(), vo.getDataYearMonth());
       // station.setDay1Diff((Double.parseDouble(station.getDay1Auto()) - Double.parseDouble(vo.getDay1Base())) + "");

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
                    method.invoke(entity,
                            (method1.invoke(station) + "," +
                                    (method2.invoke(vo)) + "," +
                                    ((Double.parseDouble(method1.invoke(station).toString())
                                            - (Double.parseDouble(method2.invoke(vo).toString()))))));
                }else {
                    /**
                     * 若没有base数据 entity赋值原来的数据
                     * */
                    method.invoke(entity,method3.invoke(stationdata));
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
                methodauto = vo.getClass().getMethod("getDay" + i + "Auto");
                methodbase = vo.getClass().getMethod("getDay" + i + "Base");

                if (methodauto.invoke(vo) != null) {
                    autototal += Double.parseDouble(methodauto.invoke(vo).toString());
                }
                if (methodbase.invoke(vo) != null) {
                    basetotal += Double.parseDouble(methodbase.invoke(vo).toString());
                }
                difftotal = autototal - basetotal;
            } catch (Exception e) {
                System.out.println("");
            }
        }
        entity.setTotal(autototal + "," + basetotal + "," + difftotal);

        try {
            return stationRainConstrastMapper.updateData(entity);
        } catch (Exception e) {
            return 0;
        }
    }

    public void insertOrUpdateData() throws Exception {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        List<Integer> idList = new ArrayList<>();
        StationIdUtils.getTable7StationList().forEach(data -> {
            int stationId = 0;
            if (configRiverStationMapper.getByName(data) != null) {
                stationId = configRiverStationMapper.getByName(data).getStationId();
                idList.add(stationId);
            }
        });
        idList.forEach(data -> {
            Calendar calendar = Calendar.getInstance();
            String databaseName = null;
            if (calendar.get(Calendar.YEAR) <= 2020) {
                databaseName = "history_day_sensor_data_2016_2020";
            } else {
                databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
            }
            //System.out.println(databaseName);
            DayData dayVo = stationRainConstrastMapper.getDayData(data + "84", databaseName);
            ReportStationRainConstrast entity = new ReportStationRainConstrast();

            ConfigRiverStation station = configRiverStationMapper.getAllByCode(data);
            entity.setStationName(station.getStationName());
            entity.setStationCode(station.getStationId());
            entity.setManageOrgName(station.getOrgName());
            entity.setManageOrgId(station.getOrgId());

            if (dayVo != null) {
                entity.setCreateTime(dayVo.getSensorDataUploadTime());
                entity.setUpdateTime(dayVo.getSensorDataUploadTime());
                entity.setDataYearMonth(dayVo.getSensorDataUploadTime().substring(0, 7));
                int nowaday = cal.get(Calendar.DAY_OF_MONTH);
                Method marray = null;
                try {
                    marray = entity.getClass().getMethod("setDay" + nowaday, String.class);
                    marray.invoke(entity, dayVo.getSensorDataValue() + ",0,0");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //查询目前数据的total
                String total = stationRainConstrastMapper.getTotal(entity.getStationCode(), entity.getDataYearMonth());
                String daynumber = "day" + nowaday;
                if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                    //月初赋值
                    entity.setTotal(dayVo.getSensorDataValue() + ",0,0");
                    stationRainConstrastMapper.insert(entity);
                } else {
                    //update时赋值
                    entity.setTotal(((Double.parseDouble(total.split(",")[0]) + dayVo.getSensorDataValue()) + "," + total.split(",")[1]) + "," + Double.parseDouble(total.split(",")[2]));
                    try {
                        stationRainConstrastMapper.update(daynumber, dayVo.getSensorDataValue() + ",0,0", entity.getStationCode(), entity.getDataYearMonth(), entity.getTotal());
                    } catch (Exception e) {
                    }
                }
            } else {
                if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
                    //月初赋值
                    entity.setTotal("0,0,0");
                    for (int i = 1; i <= 31; i++) {
                        try {
                            Method method = entity.getClass().getMethod("setDay"+i,String.class);
                            method.invoke(entity,"0,0,0");
                        } catch (Exception e) {
                            System.out.println("月初表7数据自动添加出错");
                        }
                    }
                    try {
                        stationRainConstrastMapper.insert(entity);
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

/*
    public static void main(String[] args) {
        StationRainConstrastServiceImpl s = new StationRainConstrastServiceImpl();
        s.update();
        System.out.println(s.update());

    }
*/

}
