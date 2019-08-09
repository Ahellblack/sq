package com.siti.wisdomhydrologic.maintainconfig.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorDatabase;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorDatabaseMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
@RequestMapping("/sensorDatabase")
@RestController
public class ConfigSensorDatabaseController {

    @Resource
    private ConfigSensorDatabaseMapper configSensorDatabaseMapper;

    @RequestMapping("/getAll")
    public List<ConfigSensorDatabase> getAll(){
        return configSensorDatabaseMapper.getAll();
    }
}
