package com.siti.wisdomhydrologic.operation.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalError;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorDatabase;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorDatabaseMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyw on 2019/8/21.
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
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;

    @ApiOperation(value = "字典表数据下拉框", httpMethod = "GET", notes = "字典表数据下拉框")
    @GetMapping("/getDictionary")
    public List<ConfigAbnormalDictionary> getAbnormalDictionaryList(){
     return configAbnormalDictionaryMapper.getList();
    }
    @ApiOperation(value = "测站信息下拉框", httpMethod = "GET", notes = "测站信息下拉框获取")
    @GetMapping("/getStation")
    public List<ConfigRiverStation> getStationList()
    {
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        return configRiverStationMapper.getAll(orgList.get(0).getId());
    }

    @ApiOperation(value = "测站信息下拉框", httpMethod = "GET", notes = "测站信息下拉框获取")
    @GetMapping("/getAllStationIDAndName")
    public JSONArray getAllStationIDAndName()
    {
        JSONArray jsonArray = null;
        try {
            User user = (User) userInfoService.get();
            List<Integer> orgList = userMapper.getOrgIdList(user.getId());
            List<ConfigRiverStation> list = configRiverStationMapper.getAllStationIDAndNameByOrgList(orgList);

            jsonArray = new JSONArray();
            for(ConfigRiverStation configRiverStation : list){
                JSONObject json = new JSONObject();
                json.put("stationId",String.valueOf(configRiverStation.getStationId()));
                json.put("stationName",configRiverStation.getStationName());
                jsonArray.add(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @ApiOperation(value = "错误名称", httpMethod = "GET", notes = "数据异常下拉框获取,运维表2下拉框")
    @GetMapping("/getErrorName")
    public List<ConfigAbnormalError> getErrorNameList(){
        return configAbnormalDictionaryMapper.getErrorName();
    }

    @ApiOperation(value = "数据异常下拉框", httpMethod = "GET", notes = "数据异常下拉框获取,运维表4下拉框")
    @GetMapping("/getDataError")
    public List<ConfigAbnormalError> getDataErrorNameList(){
        List<Integer> tableNumberList = new ArrayList<>();
        tableNumberList.add(2);
        return configAbnormalDictionaryMapper.getErrorNameList(tableNumberList);
    }
    @ApiOperation(value = "设备异常下拉框", httpMethod = "GET", notes = "设备异常下拉框获取,运维表3下拉框")
    @GetMapping("/getEqError")
    public List<ConfigAbnormalError> getEqErrorNameList(){
        List<Integer> tableNumberList = new ArrayList<>();
        tableNumberList.add(2);
        tableNumberList.add(4);
        return configAbnormalDictionaryMapper.getErrorNameList(tableNumberList);
    }

    @ApiOperation(value = "服务器异常下拉框", httpMethod = "GET", notes = "服务器异常下拉框获取,运维表3下拉框")
    @GetMapping("/getSeError")
    public List<ConfigAbnormalError> getSeList(){
        List<Integer> tableNumberList = new ArrayList<>();
        tableNumberList.add(3);
        return configAbnormalDictionaryMapper.getErrorNameList(tableNumberList);
    }

    public List<ConfigSensorDatabase> getDataBaseList(){
        return configSensorDatabaseMapper.getAll();
    }


    @ApiOperation(value = "设备名称下拉框", httpMethod = "GET", notes = "设备名称下拉框,运维表8下拉框")
    @GetMapping("/getSensorTypeName")
    public List<ConfigSensorDatabase> getSensorTypeNameList(String stationId){
        return configSensorDatabaseMapper.getSensorTypeNameList(stationId);
    }

    @ApiOperation(value = "资产表设备下拉框", httpMethod = "GET", notes = "资产表设备下拉框,运维表8下拉框")
    @GetMapping("/getDatabaseStationName")
    public List<String> getSensorTypeId(){
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        return configSensorDatabaseMapper.getStationName(orgList.get(0).getId());
    }

    @ApiOperation(value = "维护人员下拉框", httpMethod = "GET", notes = "维护人员下拉框")
    @GetMapping("/getMaintainer")
    public List<String> getMaintainer(){
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        return userMapper.findMaintainer(orgList.get(0).getId());

    }





}
