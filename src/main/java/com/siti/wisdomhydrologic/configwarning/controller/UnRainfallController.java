package com.siti.wisdomhydrologic.configwarning.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.configwarning.entity.*;
import com.siti.wisdomhydrologic.configwarning.mapper.WarningAbnormalUnRainMapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by dell on 2019/10/12.
 */

@RestController
@RequestMapping("/unRainfall")
public class UnRainfallController {

    @Resource
    WarningAbnormalUnRainMapper warningAbnormalUnRainMapper;

    @GetMapping("/getAllAbnormalByType")
    public Map<String, Object> getAllAbnormalByType(String SensorTypeId) {
        Map<String, Object> map = new HashMap<>();
        //获取数据库名
        String databaseName = getDatabase(SensorTypeId);
        try {
            if (!"".equals(databaseName)) {
                List<UnrainAbnormal> list = warningAbnormalUnRainMapper.getAllAbnormalByType(databaseName);
                if (list.size() > 0) {
                    map.put("list", list);
                    map.put("status", 1);
                    map.put("msg", "查询成功");
                } else {
                    map.put("status", 0);
                    map.put("msg", "暂无数据");
                }
            } else {
                map.put("status", -1);
                map.put("msg", "参数异常");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -2);
            map.put("msg", "异常");
        }
        return map;
    }

    @PostMapping("/updateAbnormalByType")
    public Map<String, Object> updateAbnormal(String SensorTypeId, @RequestBody UnrainAbnormal entity) {
        Map<String, Object> map = new HashMap<>();
        String databaseName = getDatabase(SensorTypeId);
        try {
            if (!"".equals(databaseName)) {
                int status = warningAbnormalUnRainMapper.update(databaseName, entity);
                if (status != 0) {
                    map.put("status", 1);
                    map.put("msg", "修改成功");
                } else {
                    map.put("status", 0);
                    map.put("msg", "修改失败");
                }
            } else {
                map.put("status", -1);
                map.put("msg", "参数异常");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -2);
            map.put("msg", "异常");
        }
        return map;
    }


    @PostMapping("/insertAbnormalByType")
    public Map<String, Object> insertAbnormal(String SensorTypeId, @RequestBody UnrainAbnormal entity) {
        Map<String, Object> map = new HashMap<>();
        String databaseName = getDatabase(SensorTypeId);
        try {
            if (!"".equals(databaseName)) {
                UnrainAbnormal oneAbnormalByType = warningAbnormalUnRainMapper.getOneAbnormalByType(databaseName, entity.getSensorCode());
                if (oneAbnormalByType != null) {
                    map.put("status", 0);
                    map.put("msg", "sensorCode重复");
                }
                int status = warningAbnormalUnRainMapper.insert(databaseName, entity);
                if (status != 0) {
                    map.put("status", 1);
                    map.put("msg", "添加成功");
                } else {
                    map.put("status", 0);
                    map.put("msg", "添加失败");
                }
            } else {
                map.put("status", -1);
                map.put("msg", "参数异常");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -2);
            map.put("msg", "异常");
        }
        return map;
    }


    /**
     * 获取对应typeID的数据库名称
     */
    public static String getDatabase(String SensorTypeId) {
        String databaseName = "";
        //根据参数判断修改数据库
        if (SensorTypeId != null && !("".equals(SensorTypeId))) {

            if (ConstantConfig.WAP.equals(SensorTypeId)) {
                databaseName = "abnormal_air_pressure";
            }
            if (ConstantConfig.WAT.equals(SensorTypeId)) {
                databaseName = "abnormal_air_temperature";
            }
            if (ConstantConfig.ES.equals(SensorTypeId)) {
                databaseName = "abnormal_electric";
            }
            if (ConstantConfig.WFV.equals(SensorTypeId)) {
                databaseName = "abnormal_flow_velocity";
            }
            if (ConstantConfig.TS.equals(SensorTypeId)) {
                databaseName = "abnormal_tide_level";
            }
            if (ConstantConfig.WS.equals(SensorTypeId)) {
                databaseName = "abnormal_water_level";
            }
            if (ConstantConfig.WDS.equals(SensorTypeId)) {
                databaseName = "abnormal_wind_direction";
            }
            if (ConstantConfig.WSS.equals(SensorTypeId)) {
                databaseName = "abnormal_wind_speed";
            }
        }
        return databaseName;
    }


}
