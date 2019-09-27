package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalError;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorDatabase;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorDatabaseMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dell on 2019/8/21.
 */
@RequestMapping("/dropdown")
@RestController
@Api(value="下拉框controller",tags={"下拉框"})
public class DropDownBoxController {

    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private ConfigSensorDatabaseMapper configSensorDatabaseMapper;
    @Resource
    private RedisBiz redisBiz;

    @ApiOperation(value = "字典表数据下拉框", httpMethod = "GET", notes = "字典表数据下拉框")
    @GetMapping("/getDictionary")
    public List<ConfigAbnormalDictionary> getAbnormalDictionaryList(){
     return configAbnormalDictionaryMapper.getList();
    }
    @ApiOperation(value = "测站信息下拉框", httpMethod = "GET", notes = "测站信息下拉框获取")
    @GetMapping("/getStation")
    public List<ConfigRiverStation> getStationList(HttpSession session)
    {

        User user = (User) redisBiz.get(session.getId());
        Integer uid = user.getId();

        return configRiverStationMapper.getAll(uid);
    }

    @ApiOperation(value = "错误名称", httpMethod = "GET", notes = "数据异常下拉框获取,运维表2下拉框")
    @GetMapping("/getErrorName")
    public List<ConfigAbnormalError> getErrorNameList(){
        return configAbnormalDictionaryMapper.getErrorName();
    }

    @ApiOperation(value = "数据异常下拉框", httpMethod = "GET", notes = "设备异常下拉框获取,运维表4下拉框")
    @GetMapping("/getDataError")
    public List<ConfigAbnormalDictionary> getDataErrorNameList(){
        return configAbnormalDictionaryMapper.getDataErrorNameList();
    }
    @ApiOperation(value = "设备异常下拉框", httpMethod = "GET", notes = "服务器异常下拉框获取,运维表3下拉框")
    @GetMapping("/getEqError")
    public List<ConfigAbnormalDictionary> getEqErrorNameList(){
        return configAbnormalDictionaryMapper.getEqErrorNameList();
    }

    @ApiOperation(value = "服务器异常下拉框", httpMethod = "GET", notes = "服务器异常下拉框获取,运维表3下拉框")
    @GetMapping("/getSeError")
    public List<ConfigAbnormalError> getSeList(){
        return configAbnormalDictionaryMapper.getSeErrorNameList();
    }


    public List<ConfigSensorDatabase> getDataBaseList(){
        return configSensorDatabaseMapper.getAll();
    }


    @ApiOperation(value = "设备名称下拉框", httpMethod = "GET", notes = "设备名称下拉框,运维表8下拉框")
    @GetMapping("/getSensorTypeName")
    public List<String> getSensorTypeNameList(String stationName){
        return configSensorDatabaseMapper.getSensorTypeNameList(stationName);
    }

    @ApiOperation(value = "资产表设备下拉框", httpMethod = "GET", notes = "资产表设备下拉框,运维表8下拉框")
    @GetMapping("/getDatabaseStationName")
    public List<String> getSensorTypeId(HttpSession session){
        User user = (User) redisBiz.get(session.getId());
        Integer uid = user.getId();
        return configSensorDatabaseMapper.getStationName(uid);
    }



}
