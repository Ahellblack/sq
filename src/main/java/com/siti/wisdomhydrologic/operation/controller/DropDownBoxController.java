package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import java.util.List;

import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/8/21.
 */
@RequestMapping("/dropdown")
@RestController
public class DropDownBoxController {

    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @RequestMapping("/getDictionary")
    public List<ConfigAbnormalDictionary> getAbnormalDictionaryList(){
     return configAbnormalDictionaryMapper.getList();
    }
    @RequestMapping("/getStation")
    public List<ConfigRiverStation> getStationList(){
        return configRiverStationMapper.getAll();
    }

    @RequestMapping("/getErrorName")
    public List<String> getErrorNameList(){
        return configAbnormalDictionaryMapper.getErrorName();
    }

}
