package com.siti.wisdomhydrologic.configfund.controller;

import com.siti.wisdomhydrologic.configfund.entity.ConfigRiver;
import com.siti.wisdomhydrologic.configfund.mapper.ConfigRiverMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/river")
@Api(value="测站配置表controller",tags={"测站配置表"})
public class ConfigRiverController {

    @Resource
    private ConfigRiverMapper configRiverMapper;

    @ApiOperation(value = "测站添加", httpMethod = "POST", notes = "测站添加")
    @PostMapping(value = "/insert")
    private int insert(@RequestBody ConfigRiver configRiver) {
        try {
            return configRiverMapper.insert(configRiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //     考虑修改为多个街镇合并形式
//    @GetMapping(value="/update", method=RequestMethod.POST)
//    private int update(@RequestBody ConfigRiver configRiver) {
//        try {
//            return configRiverMapper.update(configRiver);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
    @ApiOperation(value = "测站删除", httpMethod = "GET", notes = "测站删除")
    @GetMapping(value = "/delete")
    private int delete(@RequestParam(value = "riverId") Integer riverId) {
        try {
            return configRiverMapper.deleteByStreetID(riverId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation(value = "测站查询", httpMethod = "GET", notes = "测站查询")
    @GetMapping("/getAll")
    private List<ConfigRiver> getAll() {
        try {
            return configRiverMapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @ApiOperation(value = "测站查询ByRiverID", httpMethod = "GET", notes = "测站查询ByRiverID")
    @GetMapping("/getByRiverID")
    private List<ConfigRiver> getByStreetID(@RequestParam(value = "riverId") Integer riverId) {
        try {
            return configRiverMapper.getByRiverID(riverId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "测站查询ByRiverName", httpMethod = "GET", notes = "测站查询ByRiverName")
    @GetMapping("/getByRiverName")
    private List<ConfigRiver> getByStreetName(@RequestParam(value = "riverName") String riverName) {
        try {
            return configRiverMapper.getByRiverName(riverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
