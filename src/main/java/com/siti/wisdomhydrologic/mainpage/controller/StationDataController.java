package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zyw on 2019/8/15.
 */
@RestController
@RequestMapping("/station")
@Api(value = "首页测站实时数据controller", tags = {"首页测站实时数据"})
public class StationDataController {

    @Resource
    private RealStationDataMapper realStationDataMapper;
    @Resource
    private StationDataServiceImpl stationDataService;


    @ApiOperation(value = "首页地图方大级别后显示全部数据接口", httpMethod = "GET", notes = "查询station_data表获取最近各站点的传感器数据,返回各类传感器数据值")
    @ApiParam(name = "stationCode", value = "测站id(5位)")
    @GetMapping("/getRealDataAll")
    public RealStationData getRealListAll() throws Exception {
        return realStationDataMapper.getAllData();
    }

    @ApiOperation(value = "测站实时状况表更新接口", httpMethod = "GET", notes = "测站实时状况表更新接口")
    @GetMapping("/updateData")
    public void InsertRealData() throws Exception {
        stationDataService.updateData();
    }
    /**
     * @Param level 站点级别
     * @Param status 站点状态
     */
    @ApiOperation(value = "首页地图站点地址经纬度展示接口", httpMethod = "GET", notes = "返回各类站点的地图坐标,根据不同测站状态及测站等级进行筛选" + "测站状态 0位离线；1为正常；2为故障" + "站点类型：0 基本站；1国家站；2一般站 " + "南北片：42 北片 ； 43 南片")
    @ApiParam(name = "level", value = "测站级别")
    @GetMapping("/getLocation")
    public List<ConfigRiverStationVo> getList(Integer level, Integer status, Integer snId,Integer stationId) throws Exception {
        return  stationDataService.getLocationStation(level,status,snId,stationId);

    }



}
