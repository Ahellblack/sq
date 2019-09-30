package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.vo.ReportManageApplicationBrokenVo;
import com.siti.wisdomhydrologic.mainpage.vo.StationMalFunction;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/8/21.
 */
@RestController
@RequestMapping("/malfunction")
@Api(value = "首页派单状况controller", tags = {"首页派单状况"})
public class MalFunctionController {

    @Resource
    private ManageApplicationBrokenMapper manageApplicationBrokenMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private RealStationDataMapper realStationDataMapper;

    @GetMapping("/getLatest10")
    public List<ReportManageApplicationBrokenVo> getList() {

        return manageApplicationBrokenMapper.getLatest10(null);
    }

    @GetMapping("/getLatest10S")
    public List<ReportManageApplicationBrokenVo> getListS() {

        return manageApplicationBrokenMapper.getLatest10(43);
    }

    @GetMapping("/getLatest10N")
    public List<ReportManageApplicationBrokenVo> getListN() {

        return manageApplicationBrokenMapper.getLatest10(42);
    }


    @ApiOperation(value = "首页北片的派单状况", httpMethod = "GET", notes = "北片数据派单状况,默认展示年派单数据," + " NstationNumber北测站数" + " NmalNumber北派单数" + " NonResolveNumber北维护中数" + " NendResolveNumber北已解决数" + " NnormalStationNumber北正常测站数" + " NabnormalStationNumber北异常测站数" + " NdownStationNumber北离线测站数")
    @ApiParam(name = "Date", value = "date值若不传则默认查询年派单数据" + "date = 1时 查询日数据 date = 2时 查询月数据 date = 3 查询年数据")
    @GetMapping("/getN")
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
        StationMalFunction stationMalFunction = new StationMalFunction(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0);
        List<ConfigRiverStation> list = configRiverStationMapper.getAllstation();
        list.forEach(data -> {
            if ("北片".equals(data.getRegionName()))
                stationMalFunction.setNstationNumber(stationMalFunction.getNstationNumber() + 1);
        });
        regAndStatusList.forEach(data -> {
            int status = data.getRequestDesignatingStatus();
            if ("北片".equals(data.getRegionName())) {
                if (status >= 1) stationMalFunction.setNfindNumber(stationMalFunction.getNfindNumber() + 1);
                if (status >= 2) stationMalFunction.setNmalNumber(stationMalFunction.getNmalNumber() + 1);
                if (status >= 3) stationMalFunction.setNonResolveNumber(stationMalFunction.getNonResolveNumber() + 1);
                if (status >= 4) stationMalFunction.setNendResolveNumber(stationMalFunction.getNendResolveNumber() + 1);
            }
        });
        List<RealStationData> realStationData = realStationDataMapper.getDataList("北片");
        realStationData.forEach(data -> {
            if (1 == data.getStatus()) {
                stationMalFunction.setNnormalStationNumber(stationMalFunction.getNnormalStationNumber() + 1);
            }
            if (2 ==data.getStatus()) {
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
        StationMalFunction stationMalFunction = new StationMalFunction(0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<ConfigRiverStation> list = configRiverStationMapper.getAllstation();
        list.forEach(data -> {
            if ("南片".equals(data.getRegionName()))
                stationMalFunction.setSstationNumber(stationMalFunction.getSstationNumber() + 1);
        });
        regAndStatusList.forEach(data -> {
            int status = data.getRequestDesignatingStatus();
            if ("南片".equals(data.getRegionName())) {
                if (status >= 1) stationMalFunction.setSfindNumber(stationMalFunction.getSfindNumber() + 1);
                if (status >= 2) stationMalFunction.setSmalNumber(stationMalFunction.getSmalNumber() + 1);
                if (status >= 3) stationMalFunction.setSonResolveNumber(stationMalFunction.getSonResolveNumber() + 1);
                if (status >= 4) stationMalFunction.setSendResolveNumber(stationMalFunction.getSendResolveNumber() + 1);
            }
        });
        List<RealStationData> realStationData = realStationDataMapper.getDataList("南片");
        realStationData.forEach(data -> {
            if (1 == data.getStatus()) {
                stationMalFunction.setSnormalStationNumber(stationMalFunction.getSnormalStationNumber() + 1);
            }
            if (2 ==data.getStatus()) {
                stationMalFunction.setSabnormalStationNumber(stationMalFunction.getSabnormalStationNumber() + 1);
            }
            if (3 ==data.getStatus()) {
                stationMalFunction.setSdownStationNumber(stationMalFunction.getSdownStationNumber() + 1);
            }
        });
        return stationMalFunction;
    }

    @ApiOperation(value = "首页南片的畅通率状况", httpMethod = "GET", notes = "南片数据派单状况,默认展示年派单数据，每个测站畅通率及畅通率不合格数," + "返回值为map key 'UnPatencyNumber' 为不畅通数量" + "key 'List' 为不畅通测站信息及畅通率")
    @GetMapping("/Spatency")
    public Map<String, Object> getSPatencyList() {
        Integer UnPatencyNumber = 0;
        List<RealStationData> PatencyDataList = realStationDataMapper.getPatencyDataList("南片");
        for (RealStationData data : PatencyDataList) {
            if (data.getFlowRate() > data.getPatencyRate()) {
                UnPatencyNumber++;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("UnPatencyNumber", UnPatencyNumber);
        map.put("List", PatencyDataList);
        return map;
    }

    @ApiOperation(value = "首页北片的畅通率状况", httpMethod = "GET", notes = "北片数据派单状况,默认展示年派单数据，每个测站畅通率及畅通率不合格数," + "返回值为map key 'UnPatencyNumber' 为不畅通数量" + "key 'List' 为不畅通测站信息及畅通率")
    @GetMapping("/Npatency")
    public Map<String, Object> getNPatencyList() {
        Integer UnPatencyNumber = 0;
        List<RealStationData> PatencyDataList = realStationDataMapper.getPatencyDataList("北片");
        for (RealStationData data : PatencyDataList) {
            if (data.getFlowRate() > data.getPatencyRate()) {
                UnPatencyNumber++;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("UnPatencyNumber", UnPatencyNumber);
        map.put("List", PatencyDataList);
        return map;
    }

}
