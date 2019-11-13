package com.siti.wisdomhydrologic.mainpage.service.serviceImpl;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.vo.StationMalFunction;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/11/13.
 */
@Service
public class MalFunctionServiceImpl {

    @Resource
    private ManageApplicationBrokenMapper manageApplicationBrokenMapper;
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
        regAndStatusList = manageApplicationBrokenMapper.getRegAndStatusListDay();
    }
        if (DATE == 2) {
        regAndStatusList = manageApplicationBrokenMapper.getRegAndStatusListMonth();
    }
        if (DATE == 3) {
        regAndStatusList = manageApplicationBrokenMapper.getRegAndStatusListYear();
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

    @ApiOperation(value = "首页南片的派单状况", httpMethod = "GET", notes = "南片数据派单状况,默认展示年派单数据," + " NstationNumber北测站数" + " NmalNumber南派单数" + " NonResolveNumber南维护中数" + " NendResolveNumber南已解决数" + " NnormalStationNumber南正常测站数" + " NabnormalStationNumber南异常测站数" + " NdownStationNumber南离线测站数")
    @ApiParam(name = "Date", value = "date值若不传则默认查询年派单数据" + "date = 1时 查询日数据 date = 2时 查询月数据 date = 3 查询年数据")
    @GetMapping("/getS")
    public StationMalFunction getSStationMal(Integer date) {
        int DATE = 0;
        if (date == null) {
            DATE = 1;
        } else {
            DATE = date.intValue();
        }
        List<StationMalFunction> regAndStatusList = new ArrayList<>();
        if (DATE == 1) {
            regAndStatusList = manageApplicationBrokenMapper.getRegAndStatusListDay();
        }
        if (DATE == 2) {
            regAndStatusList = manageApplicationBrokenMapper.getRegAndStatusListMonth();
        }
        if (DATE == 3) {
            regAndStatusList = manageApplicationBrokenMapper.getRegAndStatusListYear();
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
                if (status >= 1) stationMalFunction.setNfindNumber(stationMalFunction.getNfindNumber() + 1);
                if (malStatus == 1) stationMalFunction.setNmalNumber(stationMalFunction.getNmalNumber() + 1);
                if (status >= 3) stationMalFunction.setNonResolveNumber(stationMalFunction.getNonResolveNumber() + 1);
                if (status == 4) stationMalFunction.setNendResolveNumber(stationMalFunction.getNendResolveNumber() + 1);
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
