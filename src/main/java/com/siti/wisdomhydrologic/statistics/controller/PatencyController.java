package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.statistics.entity.Patency;
import com.siti.wisdomhydrologic.statistics.entity.UploadData;
import com.siti.wisdomhydrologic.statistics.mapper.PatencyMapper;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.siti.wisdomhydrologic.statistics.vo.UploadVo;
import com.siti.wisdomhydrologic.util.DateDistance;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zyw on 2019/9/20.
 */
@RequestMapping("/patency")
@RestController
public class PatencyController {

    @Resource
    private PatencyMapper patencyMapper;

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;

    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;

    @RequestMapping("/getPatency")
    public Map<String, Object> getPatency(Integer stationId, String date) {
        String endTime,startTime,year;
        String yearmonth = "";
        Map<String, Object> map = new HashMap<>();
        List<ConfigRiverStation> stationList = configRiverStationMapper.getStationByStationID(stationId);
        Map<Integer, String> stationMap = new HashMap<>();
        List<String> stationIdList = new ArrayList();
        stationList.forEach(data -> {
            stationIdList.add(data.getStationId() + "89");
            stationMap.put(data.getStationId(), data.getStationName());
        });
        List<Patency> patency = new ArrayList<>();
        try {
            if (date.length() > 10) {
                year = date.substring(0, 4);
                yearmonth = year + date.substring(5, 7);
            }
            for (int i = 0; i < 7; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateTransform.String2Date(date, "yyyy-MM-dd HH:mm:ss"));
                cal.add(Calendar.DAY_OF_MONTH, -i + 1);

                endTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

                cal.add(Calendar.DAY_OF_MONTH, -1);
                startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

                //获取起始时间和结束时间的5分钟应有几个
                long[] times = DateDistance.getDistanceTimes(startTime, endTime);
                long timeDiff = times[0] * 288 + times[1] * 12 + times[2] / 5;

                List<String> exist = patencyMapper.isExist("history_real_sensor_data_" + yearmonth);
                if (exist.size() == 0) {
                    map.put("status", 0);
                    map.put("msg", "暂无数据");
                    return map;
                }

                Patency entity = patencyMapper.getPatency("history_real_sensor_data_" + yearmonth, stationIdList, startTime, endTime);
                if (entity != null) {
                    entity.setStationId(stationId);
                    entity.setPatencyRate(Double.parseDouble(entity.getNumber() / timeDiff + "") * 100);
                    BigDecimal b = new BigDecimal(entity.getPatencyRate());
                    entity.setPatencyRate(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    entity.setStationName(stationMap.get(entity.getStationId()));
                    entity.setTime(startTime);
                } else {
                    entity = new Patency(stationMap.get(stationId), stationId, 0.0, 0.0, startTime);
                }
                patency.add(entity);
            }
            map.put("status", 1);
            map.put("msg", "查询成功");
            map.put("list", patency);
        } catch (Exception e) {
            map.put("status", -1);
            map.put("msg", "查询出错");
            System.out.println("查询出错");
        }
        return map;
    }

    @GetMapping("uploadData")
    public Map<String, Object> getUploadData(String year) {
        Map<String, Object> map = new HashMap<>();
        try {
            UploadVo rtsqvo = new UploadVo(); rtsqvo.setSum(0);
            UploadVo tsdbvo = new UploadVo(); tsdbvo.setSum(0);
            UploadVo hourvo = new UploadVo();hourvo.setSum(0);
            UploadVo dayvo = new UploadVo();dayvo.setSum(0);
            List<UploadData> list = patencyMapper.uploadList(year);
            list.forEach(uploadData -> {
                switch (uploadData.getDataType()) {
                    case 1://rtsq
                        solveUploadData(rtsqvo,uploadData);
                        rtsqvo.setName("rtsq");
                        break;
                    case 2://tsdb
                        solveUploadData(tsdbvo,uploadData);
                        tsdbvo.setName("tsdb");
                        break;
                    case 3://hour
                        solveUploadData(hourvo,uploadData);
                        hourvo.setName("hour");
                        break;
                    case 4://day
                        solveUploadData(dayvo,uploadData);
                        dayvo.setName("day");
                        break;
                }
            });
            map.put("rtsq", rtsqvo);
            map.put("tsdb", tsdbvo);
            map.put("hour", hourvo);
            map.put("day", dayvo);
            map.put("status", 1);
            return map;
        } catch (Exception e) {
            map.put("status", -1);
            map.put("msg", "查询出错");
        }
        return map;
    }

    public static UploadVo solveUploadData(UploadVo vo,UploadData entity){

        for (int i = 1; i <= 12; i++) {
            try {
                Integer month = Integer.parseInt(entity.getYearMonth().split("-")[1]);
                Method setMethod = vo.getClass().getMethod("setMonth" + i, Integer.class);
                Method getMethod = vo.getClass().getMethod("getMonth" + i);
                if (month == i) {
                    setMethod.invoke(vo, entity.getValue());
                    int value = (Integer) getMethod.invoke(vo);
                    vo.setSum(vo.getSum()+value);
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
        return vo;
    }


    @GetMapping("/getDataUploading")
    public Map<String, Object> getDataUploading(String date, Integer stationId) {
        Map<String, Object> map = new HashMap<>();
        String day = "";
        String year = "";
        String yearmonth = "";
        try {
            if (date.length() > 10) {
                day = date.substring(0, 10);
                year = date.substring(0, 4);
                yearmonth = year + date.substring(5, 7);
            }
            //查询需发送数据的传感器个数
            Integer moduleNum = configSensorSectionModuleMapper.getStation(stationId).size();

            //统计tsdb当天数据
            Integer time1 = patencyMapper.getRealTSDBData("history_5min_sensor_data_" + year, day, stationId);
            long[] times = DateDistance.getDistanceTimes(day + " 00:00:00", day + " 24:00:00");
            long timeDiff = times[0] * 24;
            map.put("expectTSDB", timeDiff * moduleNum);
            map.put("realTSDB", time1);
            Integer time2 = patencyMapper.getRealHourData("history_hour_sensor_data_" + year, day, stationId);
            map.put("expectHour", timeDiff * moduleNum);
            map.put("realHour", time2);

            Integer time3 = patencyMapper.getRealRTSQData("history_real_sensor_data_" + yearmonth, day, stationId);
            long timeRTSQ = times[0] * 288 + times[1] * 12 + times[2] / 5;
            map.put("expectRTSQ", timeRTSQ * moduleNum);
            map.put("realRTSQ", time3);

            map.put("status", 1);
            map.put("msg", "查询成功");
        } catch (Exception e) {

            map.put("status", -1);
            map.put("msg", "查询出错");
        }
        return map;
    }

    /**
     * 取当前日期的年月日
     *
     * @return
     * @throws ParseException
     */
    public static Date getMinDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = sdf.parse(sdf.format(date));
        return newDate;
    }

    /**
     * 获取最近的整5分时间点real表数据
     *
     * @Param dateFormat dateFormat的格式 如 YYYY-MM-dd
     * @Param date 当前时间
     * @Param min 相隔时间
     */
    public static String getCloseDate(String dateFormat, Date date, long min) throws Exception {
        long dateTime = date.getTime();
        long needTime = 0;
        if (min >= 8 * 60) {
            return new SimpleDateFormat(dateFormat).format(getMinDate(date));
        } else {
            needTime = dateTime - dateTime % (min * 60L * 1000L);
        }
        return new SimpleDateFormat(dateFormat).format(new Date(needTime));
    }

}
