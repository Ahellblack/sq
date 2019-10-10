package com.siti.wisdomhydrologic.maintainconfig.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/8/16.
 */
@RestController
@RequestMapping("/module")
@Api(value = "传感器配置表controller", tags = {"传感器配置表"})
public class ConfigSensorSectionModuleController {

    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;

    @ApiOperation(value = "传感器", httpMethod = "GET", notes = "传感器根")
    @GetMapping("/getAll")
    private List<ConfigSensorSectionModule> getList() {
        try {
            return configSensorSectionModuleMapper.getStation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/getAllNid")
    private String getNidList() {
        try {
            List<String> list = configSensorSectionModuleMapper.getNidStation();
            String str = "";
            for (String nid : list) {
                str = str + nid + ",";
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 插入
    @PostMapping("/insert")
    public int insert(@RequestBody ConfigSensorSectionModule configSensorSectionModule) {
        try {
            System.out.println(configSensorSectionModule.toString());
            return configSensorSectionModuleMapper.insert(configSensorSectionModule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 修改数据，部分字段不修改
    @PostMapping("/update")
    public int update(@RequestBody ConfigSensorSectionModule configSensorSectionModule) {
        try {
            return configSensorSectionModuleMapper.update(configSensorSectionModule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 后续不建议开放删除接口，仅供内部使用
    @GetMapping("/delete")
    public int delete(@RequestParam(value = "sectionCode") Integer sectionCode) {
        try {
            return configSensorSectionModuleMapper.deleteBySectionCode(sectionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 根据测站ID，查询某测站下所有元素数据
    @ApiOperation(value = "传感器根据测站ID，查询某测站下所有元素数据", httpMethod = "GET", notes = "传感器根根据测站ID")
    @GetMapping("/findModulesByStationCode")
    public List<ConfigSensorSectionModule> findModulesByStationCode(@RequestParam(value = "stationCode") Integer stationCode) {
        return configSensorSectionModuleMapper.findModuleByStationCode(stationCode);
    }

    // 根据测站名，查询某测站下所有元素数据
    @GetMapping("/findModulesByStationName")
    @ApiOperation(value = "传感器根据测站名，查询某测站下所有元素数据", httpMethod = "GET", notes = "传感器根据测站名")
    public List<ConfigSensorSectionModule> findModulesByStationName(@RequestParam(value = "stationName") String stationName) {
        return configSensorSectionModuleMapper.findModuleByStationName(stationName);
    }

    // 根据分中心站ID查找下属所有传感器数据
    @ApiOperation(value = "传感器根据分中心站ID查找下属所有传感器数据", httpMethod = "GET", notes = "传感器根据分中心站ID")
    @GetMapping("/findModuleByOrgID")
    public List<ConfigSensorSectionModule> findModuleByOrgID(@RequestParam(value = "orgId") Integer orgId) {
        return configSensorSectionModuleMapper.findModuleBySysOrg(orgId);
    }
}
