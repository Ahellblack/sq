package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import com.siti.wisdomhydrologic.mainpage.vo.AbnormalDetailVo;
import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.vo.RealDeviceStatus;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.DatesUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
