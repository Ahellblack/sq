package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.statistics.entity.Patency;
import com.siti.wisdomhydrologic.statistics.mapper.PatencyMapper;

import java.math.BigDecimal;
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

    @RequestMapping("/getPatency")
    public Map<String, Object> getPatency(String startTime, String endTime, Integer stationId) {
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
            //获取起始时间和结束时间的5分钟应有几个
            long[] times = DateDistance.getDistanceTimes(startTime, endTime);
            long timeDiff = times[0] * 288 + times[1] * 12 + times[2] / 5;

            patency = patencyMapper.getPatency(stationIdList, startTime, endTime);
            patency.forEach(data -> {
                data.setStationId(data.getStationId() / 100);
                data.setPatencyRate(Double.parseDouble(data.getNumber() / timeDiff + "") * 100);
                BigDecimal b = new BigDecimal(data.getPatencyRate());
                data.setPatencyRate(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                data.setStationName(stationMap.get(data.getStationId()));
            });
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
    public Map<String, Object> getDataUploading() {
        Map<String, Object> map = new HashMap<>();
        String today = DateTransform.Date2String(new Date(), "yyyy-MM-dd");
        String time = DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
        String year = DateTransform.Date2String(new Date(), "yyyy");
        String databaseName = "";

        //统计tsdb当天数据
        Integer time1 = patencyMapper.getRealTSDBData(
                "history_5min_sensor_data_"+ year);
        long[] times = DateDistance.getDistanceTimes(today+" 00:00:00", time);
        long timeDiff = times[1];
        map.put("expectTSDB",timeDiff);
        map.put("realTSDB",time1);
        Integer time2 = patencyMapper.getRealHourData(
                "history_hour_sensor_data_"+ year);
        map.put("expectHour",timeDiff);
        map.put("realHour",time2);
        Integer time3 = patencyMapper.getRealRTSQData("real");
        long timeRTSQ = times[0] * 288 + times[1] * 12 + times[2] / 5;
        map.put("expectRTSQ",timeRTSQ);
        map.put("realRTSQ",time3);


        return map;
    }

    public static void main(String[] args) {

        String startTime = "2019-09-10 13:00:00";
        String endTime = "2019-09-13 18:30:00";

        //获取起始时间和结束时间的5分钟应有几个
        long[] times = DateDistance.getDistanceTimes(startTime, endTime);
        long timeDiff = times[0] * 288 + times[1] * 12 + times[2] / 5;

        System.out.println(timeDiff);


    }

}
