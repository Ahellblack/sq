package com.siti.wisdomhydrologic.mainpage.service.serviceImpl;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.vo.StationMalFunction;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/11/13.
 */
@Service
public class MalFunctionServiceImpl {

    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private RealStationDataMapper realStationDataMapper;


    public StationMalFunction getNStationMal(Integer date) {
        int DATE;
        if (date == null) {
            DATE = 3;
        } else {
            DATE = date.intValue();
        }
        List<StationMalFunction> regAndStatusList = new ArrayList<>();
        if (DATE == 1) {
            regAndStatusList = reportStationBrokenMapper.getRegAndStatusListDay();
        }
        if (DATE == 2) {
            regAndStatusList = reportStationBrokenMapper.getRegAndStatusListMonth();
        }
        if (DATE == 3) {
            regAndStatusList = reportStationBrokenMapper.getRegAndStatusListYear();
        }
        StationMalFunction stationMalFunction = new StationMalFunction(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<ConfigRiverStation> list = configRiverStationMapper.getAllstation();
        list.forEach(data -> {
            if (42 == data.getRegionId())
                stationMalFunction.setNstationNumber(stationMalFunction.getNstationNumber() + 1);
        });
        regAndStatusList.forEach(data -> {
            int status = data.getRequestDesignatingStatus();
            int malStatus = data.getMalStatus();
            if ("42".equals(data.getRegionId())) {
                if (status >= 1) stationMalFunction.setNfindNumber(stationMalFunction.getNfindNumber() + 1);
                if (malStatus == 1) stationMalFunction.setNmalNumber(stationMalFunction.getNmalNumber() + 1);
                if (status >= 3) stationMalFunction.setNonResolveNumber(stationMalFunction.getNonResolveNumber() + 1);
                if (status == 4) stationMalFunction.setNendResolveNumber(stationMalFunction.getNendResolveNumber() + 1);
            }
        });
        List<RealStationData> realStationData = realStationDataMapper.getDataList("北片");
        realStationData.forEach(data -> {
            if (1 == data.getStatus()) {
                stationMalFunction.setNnormalStationNumber(stationMalFunction.getNnormalStationNumber() + 1);
            }
            if (2 == data.getStatus()) {
                stationMalFunction.setNabnormalStationNumber(stationMalFunction.getNabnormalStationNumber() + 1);
            }
            if (3 == data.getStatus()) {
                stationMalFunction.setNdownStationNumber(stationMalFunction.getNdownStationNumber() + 1);
            }
        });
        return stationMalFunction;
    }


    public StationMalFunction getSStationMal(Integer date) {
        int DATE = 0;
        if (date == null) {
            DATE = 1;
        } else {
            DATE = date.intValue();
        }
        List<StationMalFunction> regAndStatusList = new ArrayList<>();
        if (DATE == 1) {
            regAndStatusList = reportStationBrokenMapper.getRegAndStatusListDay();
        }
        if (DATE == 2) {
            regAndStatusList = reportStationBrokenMapper.getRegAndStatusListMonth();
        }
        if (DATE == 3) {
            regAndStatusList = reportStationBrokenMapper.getRegAndStatusListYear();
        }
        StationMalFunction stationMalFunction = new StationMalFunction(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<ConfigRiverStation> list = configRiverStationMapper.getAllstation();
        list.forEach(data -> {
            if (43 == data.getRegionId())
                stationMalFunction.setSstationNumber(stationMalFunction.getSstationNumber() + 1);
        });
        regAndStatusList.forEach(data -> {
            int status = data.getRequestDesignatingStatus();
            int malStatus = data.getMalStatus();
            if ("43".equals(data.getRegionId())) {
                if (status >= 1) stationMalFunction.setSfindNumber(stationMalFunction.getSfindNumber() + 1);
                if (malStatus == 1) stationMalFunction.setSmalNumber(stationMalFunction.getSmalNumber() + 1);
                if (status >= 3) stationMalFunction.setSonResolveNumber(stationMalFunction.getSonResolveNumber() + 1);
                if (status == 4) stationMalFunction.setSendResolveNumber(stationMalFunction.getSendResolveNumber() + 1);
            }
        });
        List<RealStationData> realStationData = realStationDataMapper.getDataList("南片");
        realStationData.forEach(data -> {
            if (1 == data.getStatus()) {
                stationMalFunction.setSnormalStationNumber(stationMalFunction.getSnormalStationNumber() + 1);
            }
            if (2 == data.getStatus()) {
                stationMalFunction.setSabnormalStationNumber(stationMalFunction.getSabnormalStationNumber() + 1);
            }
            if (3 == data.getStatus()) {
                stationMalFunction.setSdownStationNumber(stationMalFunction.getSdownStationNumber() + 1);
            }
        });
        return stationMalFunction;
    }
}
