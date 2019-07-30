package com.siti.wisdomhydrologic.datepull.service.impl;

import com.siti.wisdomhydrologic.datepull.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.datepull.mapper.TSDBMapper;
import com.siti.wisdomhydrologic.datepull.service.TSDBService;
import com.siti.wisdomhydrologic.datepull.vo.TSDBVo;
import com.siti.wisdomhydrologic.datepull.vo.StationVo;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/23.
 */
@Component
public class TSDBServiceImpl implements TSDBService {

    @Resource
    private DayDataMapper dayDataMapper;
    @Resource
    private TSDBMapper tsdbMapper;

    @Override
    public synchronized int insertTSDB(List<TSDBVo> list) {
        List<ConfigSensorSectionModule> stationList = dayDataMapper.getStation();
        System.out.println("测站信息获取...");
        for(TSDBVo tsdb :list){
            for(ConfigSensorSectionModule station : stationList){
                if(tsdb.getSENID() == station.getSectionCode()){
                    tsdb.setSensorTypeName(station.getSensorName());
                    tsdb.setSensorDataUnit(station.getSectionDataUnit());
                    tsdb.setSensorTypeId(station.getSensorCode());
                    tsdb.setStationId(station.getStationCode());
                    tsdb.setStationName(station.getStationName());
                }
            }
        }
        return tsdbMapper.insertTSDB(list);
    }
}
