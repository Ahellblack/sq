package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.statistics.entity.Patency;
import com.siti.wisdomhydrologic.statistics.mapper.PatencyMapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.siti.wisdomhydrologic.util.DateDistance;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/9/20.
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
        String endTime = "";
        String startTime = "";
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
            for (int i = 0; i < 7; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateTransform.String2Date(date, "yyyy-MM-dd HH:mm:ss"));
                cal.add(Calendar.DAY_OF_MONTH, -i);

                endTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

                cal.add(Calendar.DAY_OF_MONTH, -1);
                startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

                //获取起始时间和结束时间的5分钟应有几个
                long[] times = DateDistance.getDistanceTimes(startTime, endTime);
                long timeDiff = times[0] * 288 + times[1] * 12 + times[2] / 5;
                Patency entity = patencyMapper.getPatency(stationIdList, startTime, endTime);

                if (entity != null) {
                    entity.setStationId(stationId);
                    entity.setPatencyRate(Double.parseDouble(entity.getNumber() / timeDiff + "") * 100);
                    BigDecimal b = new BigDecimal(entity.getPatencyRate());
                    entity.setPatencyRate(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    entity.setStationName(stationMap.get(entity.getStationId()));
                    entity.setTime(endTime);
                } else {
                    entity = new Patency(stationMap.get(stationId), stationId, 0.0, 0.0, endTime);
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

    @GetMapping("/getDataUploading")
    public Map<String, Object> getDataUploading(String date) {
        Map<String, Object> map = new HashMap<>();
        String day = "";
        String year = "";
        try {
            if (date.length() > 10) {
                day = date.substring(0, 10);
                year = date.substring(0, 4);
            }
            //查询需发送数据的传感器个数
            Integer moduleNum = configSensorSectionModuleMapper.getStation().size();

            //统计tsdb当天数据
            Integer time1 = patencyMapper.getRealTSDBData("history_5min_sensor_data_" + year);
            long[] times = DateDistance.getDistanceTimes(day + " 00:00:00", day + " 24:00:00");
            long timeDiff = times[0]*24;
            map.put("expectTSDB", timeDiff * moduleNum);
            map.put("realTSDB", time1);
            Integer time2 = patencyMapper.getRealHourData("history_hour_sensor_data_" + year);
            map.put("expectHour", timeDiff * moduleNum);
            map.put("realHour", time2);
            Integer time3 = patencyMapper.getRealRTSQData("real");
            long timeRTSQ = times[0] * 288 + times[1] * 12 + times[2] / 5;
            map.put("expectRTSQ", timeRTSQ * moduleNum);
            map.put("realRTSQ", time3);

            map.put("status", 1);
            map.put("msg", "查询成功");
        }catch (Exception e){

            map.put("status", -1);
            map.put("msg", "查询出错");
        }
        return map;
    }

    public static void main(String[] args) {

        String startTime = "2019-09-10 13:00:00";
        String endTime = "2019-09-13 18:30:00";

        //获取起始时间和结束时间的5分钟应有几个
        long[] times = DateDistance.getDistanceTimes("2019-09-10" + " 00:00:00", "2019-09-10" + " 23:59:59");
        long timeDiff = times[0] * 288 + times[1] * 12 + times[2] / 5;


        System.out.println(startTime.substring(0, 4));
        System.out.println(timeDiff);


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
