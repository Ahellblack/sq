package com.siti.wisdomhydrologic.configfund.controller;

import com.siti.wisdomhydrologic.configfund.entity.ConfigRegion;
import com.siti.wisdomhydrologic.configfund.mapper.ConfigRegionMapper;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/10/14.
 */
@RestController
@RequestMapping("region")
public class ConfigRegionController {

    @Resource
    private ConfigRegionMapper configRegionMapper;

    @GetMapping("getAll")
    public List<ConfigRegion> getALL(){
       return configRegionMapper.getAll();
    }

}
