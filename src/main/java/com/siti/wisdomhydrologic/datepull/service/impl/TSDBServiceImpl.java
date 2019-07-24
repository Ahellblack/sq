package com.siti.wisdomhydrologic.datepull.service.impl;

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
    private TSDBMapper tsdbMapper;

    @Override
    public synchronized int insertTSDB(List<TSDBVo> list) {
        /*List<StationVo> stationList = tsdbMapper.getStation();
        System.out.println("测站信息获取...");
        for(TSDBVo tsdb :list){
            String time=DateOrTimeTrans.Date2TimeString2(tsdb.getTime());
            tsdb.setSensorDataTime(DateOrTimeTrans.String2Timestamp(time));
            for(StationVo station : stationList){
                if(tsdb.getSENID() == station.getSenId()){
                    tsdb.setSensorTypeName(station.getSensorName());
                    tsdb.setSensorDataUnit("");
                    tsdb.setSensorTypeId(station.getSensorTypeId());
                    tsdb.setStationId(station.getStationId());
                    tsdb.setStationName(station.getStationName());
                    System.out.println(tsdb);
                }
            }
        }*/
        System.out.println(list);
        return tsdbMapper.insertTSDB(list);
    }
}
