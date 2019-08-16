package com.siti.wisdomhydrologic.maintainconfig.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/8/16.
 */
@RequestMapping("/module")
@RestController
public class ConfigSensorSectionModuleController {

    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;
    @RequestMapping("/getAll")
    private List<ConfigSensorSectionModule> getList(){
        return configSensorSectionModuleMapper.getStation();
    }
    @RequestMapping("/insert")
    private int insert(ConfigSensorSectionModule configSensorSectionModule){
        return configSensorSectionModuleMapper.insert(configSensorSectionModule);
    }
    @RequestMapping("/update")
    private int update(ConfigSensorSectionModule configSensorSectionModule){
        return configSensorSectionModuleMapper.updateByPrimaryKey(configSensorSectionModule);
    }
}
