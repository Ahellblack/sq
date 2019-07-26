package com.siti.wisdomhydrologic.datepull.service.impl;

import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.datepull.service.DayDataService;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.datepull.vo.StationVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/18.
 */
@Component
public class DayDataServiceImpl implements DayDataService{

    @Resource
    private DayDataMapper dayDataMapper;

    @Override
    public synchronized int addDayData(List<DayVo> dayVo) {
        List<StationVo> stationList = dayDataMapper.getStation();
        System.out.println("测站信息获取...");
        for(DayVo d :dayVo){
            for(StationVo station : stationList){
                if(d.getSenId() == station.getSenId()){
                    d.setSensorTypeName(station.getSensorName());
                    d.setSensorDataUnit("");
                    d.setSensorTypeId(station.getSensorTypeId());
                    d.setStationId(station.getStationId());
                    d.setStationName(station.getStationName());
                }
            }
        }
        return dayDataMapper.addDayData(dayVo);
    }

    @Override
    public synchronized int addHourData(List<DayVo> HourVo) {
        List<StationVo> stationList = dayDataMapper.getStation();
        System.out.println("测站信息获取...");
        for(DayVo d :HourVo){
            for(StationVo station : stationList){
                if(d.getSenId() == station.getSenId()){
                    d.setSensorTypeName(station.getSensorName());
                    d.setSensorDataUnit("");
                    d.setSensorTypeId(station.getSensorTypeId());
                    d.setStationId(station.getStationId());
                    d.setStationName(station.getStationName());
                }
            }
        }
        return dayDataMapper.addHourData(HourVo);
    }
}
