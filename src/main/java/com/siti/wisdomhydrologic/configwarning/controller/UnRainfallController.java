package com.siti.wisdomhydrologic.configwarning.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.configwarning.entity.*;
import com.siti.wisdomhydrologic.configwarning.mapper.WarningAbnormalUnRainMapper;
import com.sun.tools.javac.util.List;
import org.apache.ibatis.annotations.Update;
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
    public List<UnrainAbnormal> getAllAbnormalByType(String SensorTypeId) {
        //获取数据库名
        String databaseName = getDatabase(SensorTypeId);
        return warningAbnormalUnRainMapper.getAllAbnormalByType(databaseName);
    }
    @PostMapping("/updateAbnormalByType")
    public Map<String, Object> updateAbnormal(String SensorTypeId, @RequestBody UnrainAbnormal entity) {
        String databaseName = getDatabase(SensorTypeId);
        int status = warningAbnormalUnRainMapper.update(databaseName,entity);
        return null;
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
