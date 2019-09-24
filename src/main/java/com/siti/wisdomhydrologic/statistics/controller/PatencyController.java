package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.statistics.entity.Patency;
import com.siti.wisdomhydrologic.statistics.mapper.PatencyMapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.siti.wisdomhydrologic.util.DateDistance;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

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

    @RequestMapping("/getAll")
    public List<Patency> getPatency(String startTime, String endTime,String stationName) {
        List<ConfigRiverStation> stationList = configRiverStationMapper.getAllByStationName(stationName);
        Map<Integer,String> stationMap = new HashMap<>();
        List<String> stationIdList  = new ArrayList();
        stationList.forEach(data->{
            stationIdList.add(data.getStationId()+"89");
            stationMap.put(data.getStationId(),data.getStationName());
        });
        //获取起始时间和结束时间的5分钟应有几个
        long[] times = DateDistance.getDistanceTimes(startTime,endTime);
        long timeDiff =  times[0] * 288
                +times[1]*12
                +times[2]/5;

        List<Patency> patency = patencyMapper.getPatency(stationIdList, startTime, endTime);
        try {
            patency.forEach(data -> {
                data.setStationId(data.getStationId() / 100);
                data.setPatencyRate(Double.parseDouble(data.getNumber() / timeDiff + "") * 100);
                BigDecimal b = new BigDecimal(data.getPatencyRate());
                data.setPatencyRate(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                data.setStationName(stationMap.get(data.getStationId()));
            });
        }catch (Exception e){
            System.out.println("查询出错");
        }
        return patency;
    }

    public static void main(String[] args) {

        String startTime = "2019-09-10 13:00:00";
        String endTime ="2019-09-13 18:30:00";

        //获取起始时间和结束时间的5分钟应有几个
        long[] times = DateDistance.getDistanceTimes(startTime,endTime);
        long timeDiff =  times[0] * 288
                +times[1]*12
                +times[2]/5;

        System.out.println(timeDiff);


    }

}
