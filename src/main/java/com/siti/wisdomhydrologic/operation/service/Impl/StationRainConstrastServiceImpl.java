package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.mapper.StationRainConstrastMapper;
import com.siti.wisdomhydrologic.operation.service.StationRainConstrastService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/1.
 */
public class StationRainConstrastServiceImpl implements StationRainConstrastService {
    @Resource
    private StationRainConstrastMapper stationRainConstrastMapper;

    @Override
    public List<ReportStationRainConstrast> getByMonth(Date date) {
        return stationRainConstrastMapper.getByMonth(date);
    }
}
