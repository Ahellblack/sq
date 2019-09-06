package com.siti.wisdomhydrologic.maintainconfig.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
@RestController
@RequestMapping("/RiverStation")
public class ConfigRiverStationController {

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @ApiOperation(value = "测站查询", httpMethod = "GET", notes = "测站添加")
    @GetMapping("/getAll")
    public List<ConfigRiverStation> getAll() {
        return configRiverStationMapper.getAll();
    }

    // 后续登录做好之后，需要根据登录用户ID，获取到其org_id,再根据org_id 查询对应该看到的测站信息
    @ApiOperation(value = "测站查询BySysOrg", httpMethod = "GET", notes = "测站添加BySysOrg")
    @GetMapping("/getAllStationBySysOrg")
    public List<ConfigRiverStation> getAllStationBySysOrg(@RequestParam(value = "orgId") Integer orgId) {
        try {
            return configRiverStationMapper.getAllStationBySysOrg(orgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据测站级别进行查询
    @ApiOperation(value = "测站查询ByStationLevel", httpMethod = "GET", notes = "测站添加ByStationLevel")
    @GetMapping("/getAllByStationLevel")
    public List<ConfigRiverStation> getAllByStationLevel(@RequestParam(value = "stationLevel") Integer stationLevel) {
        try {
            return configRiverStationMapper.getAllByStationLevel(stationLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据测站名称模糊进行查询
    @ApiOperation(value = "测站查询ByStationName", httpMethod = "GET", notes = "测站添加ByStationName")
    @GetMapping("/getAllByStationName")
    public List<ConfigRiverStation> getAllByStationName(@RequestParam(value = "stationName") String stationName) {
        try {
            return configRiverStationMapper.getAllByStationName(stationName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ConfigRiverStation configRiverStation) {
        try {
            return configRiverStationMapper.insert(configRiverStation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 并不是所有内容都更新，注意查看sql语句
    @PostMapping("/update")
    public int update(@RequestBody ConfigRiverStation configRiverStation) {
        try {
            return configRiverStationMapper.update(configRiverStation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 后续不建议开放删除接口，仅供内部使用
    @GetMapping(value = "/delete")
    public int delete(@RequestParam(value = "stationCode") String stationCode) {
        try {
            return configRiverStationMapper.deleteByStationCode(stationCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
