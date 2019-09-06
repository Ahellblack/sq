package com.siti.wisdomhydrologic.fundconfig.controller;

import com.siti.wisdomhydrologic.fundconfig.entity.ConfigStreet;
import com.siti.wisdomhydrologic.fundconfig.mapper.ConfigStreetMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/street")
@Api(value="街镇配置表controller",tags={"街镇配置表"})
public class ConfigStreetController {

    @Resource
    private ConfigStreetMapper configStreetMapper;

    @ApiOperation(value = "街道添加", httpMethod = "GET", notes = "街道添加")
    @PostMapping(value="/insert")
    private int insert(@RequestBody ConfigStreet configStreet) {
        try {
            System.out.println(configStreet.getSysOrgName());
            return configStreetMapper.insert(configStreet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation(value = "街道修改", httpMethod = "POST", notes = "街道修改")
    @PostMapping(value="/update")
    private int update(@RequestBody ConfigStreet configStreet) {
        try {
            return configStreetMapper.update(configStreet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation(value = "街道删除", httpMethod = "GET", notes = "街道删除")
    @GetMapping(value="/delete")
    private int delete(@RequestParam(value = "streetId")  Integer streetID) {
        try {
            return configStreetMapper.deleteByStreetID(streetID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation(value = "街道查询", httpMethod = "GET", notes = "街道查询")
    @GetMapping("/getAll")
    private List<ConfigStreet> getAll() {
        try {
            return configStreetMapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @ApiOperation(value = "街道查询BySysOrgID", httpMethod = "GET", notes = "街道查询BySysOrgID")
    @GetMapping("/getBySysOrgID")
    private List<ConfigStreet> getBySysOrgID(@RequestParam(value = "sysOrgId") Integer sysOrgId) {
        try {
            return configStreetMapper.getBySysOrg(sysOrgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "街道查询ByStreetID", httpMethod = "GET", notes = "街道查询ByStreetID")
    @GetMapping("/getByStreetID")
    private List<ConfigStreet> getByStreetID(@RequestParam(value = "streetId")  Integer streetID) {
        try {
            return configStreetMapper.getByStreetID(streetID);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @ApiOperation(value = "街道查询ByStreetName", httpMethod = "GET", notes = "街道查询ByStreetName")
    @GetMapping("/getByStreetName")
    private List<ConfigStreet> getByStreetName(@RequestParam(value = "streetName") String streetName) {
        try {
            return configStreetMapper.getByStreetName(streetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
