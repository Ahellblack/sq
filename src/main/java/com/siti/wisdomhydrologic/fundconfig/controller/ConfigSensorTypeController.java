package com.siti.wisdomhydrologic.fundconfig.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.fundconfig.entity.ConfigSensorType;
import com.siti.wisdomhydrologic.fundconfig.mapper.ConfigSensorTypeMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sensorType")
public class ConfigSensorTypeController {

    @Resource
    private ConfigSensorTypeMapper configSensorTypeMapper;

    @RequestMapping("/getAll")
    public List<ConfigSensorType> getAll() {
        try {
            return configSensorTypeMapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getAllIDAndName")
    public List<JSONObject> getAllIDAndName() {
        try {
            List<JSONObject> jsonList = new ArrayList<JSONObject>();
            // 从ConfigRiverStation取出stationID与stationName，构建json数组传送给前端
            for (ConfigSensorType rs:configSensorTypeMapper.getAll()) {
                JSONObject json = new JSONObject();
                json.put("sensorTypeID", rs.getSensorTypeId());
                json.put("sensorTypeName", rs.getSensorTypeName());
                jsonList.add(json);
            }
            return jsonList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
