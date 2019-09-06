package com.siti.wisdomhydrologic.maintainconfig.controller;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorDatabase;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorDatabaseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
@RestController
@RequestMapping("/sensorDatabase")
@Api(value="资产配置表controller",tags={"资产配置表"})
public class ConfigSensorDatabaseController {

    @Resource
    private ConfigSensorDatabaseMapper configSensorDatabaseMapper;

    @ApiOperation(value = "资产查询", httpMethod = "GET", notes = "资产查询")
    @GetMapping("/getAll")
    public List<ConfigSensorDatabase> getAll(){
        return configSensorDatabaseMapper.getAll();
    }

    @PostMapping("/insert")
    public int getAllStationBySysOrg(@RequestBody ConfigSensorDatabase configSensorDatabase){
        try {
            return configSensorDatabaseMapper.insert(configSensorDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 并不是所有内容都更新，注意查看sql语句
    @PostMapping("/update")
    public int update(@RequestBody ConfigSensorDatabase configSensorDatabase){
        try {
            return configSensorDatabaseMapper.update(configSensorDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 后续不建议开放删除接口，仅供内部使用
    @GetMapping("/delete")
    public int delete(@RequestParam(value = "propertyCode")  Long propertyCode) {
        try {
            return configSensorDatabaseMapper.deleteByPropertyCode(propertyCode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    // 根据资产ID查询
    @ApiOperation(value = "资产根据资产ID查询", httpMethod = "GET", notes = "资产根据资产ID查询")
    @GetMapping("/findByPropertyCode")
    public ConfigSensorDatabase findByPropertyCode(@RequestParam(value = "propertyCode") Long propertyCode){
        try {
            return configSensorDatabaseMapper.findAllByPropertyCode(propertyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据传感器编号查询
    @ApiOperation(value = "资产根据传感器编号查询", httpMethod = "GET", notes = "资产根据传感器编号查询")
    @GetMapping("/findAllBySensorCode")
    public List<ConfigSensorDatabase> findAllBySensorCode(@RequestParam(value = "sensorCode") String sensorCode){
        try {
            return configSensorDatabaseMapper.findAllBySensorCode(sensorCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据资产类型ID查询
    @ApiOperation(value = "资产根据资产类型ID查询", httpMethod = "GET", notes = "资产根根据资产类型ID查询")
    @GetMapping("/findAllByTypeID")
    public List<ConfigSensorDatabase> findAllByTypeID(@RequestParam(value = "sensorTypeId") String sensorTypeId){
        try {
            return configSensorDatabaseMapper.findAllByTypeID(sensorTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据资产类型名模糊查询
    @ApiOperation(value = "资产根据资产类型名模糊查询", httpMethod = "GET", notes = "资产根据资产类型名模糊查询")
    @GetMapping("/findAllByTypeName")
    public List<ConfigSensorDatabase> findAllByTypeName(@RequestParam(value = "sensorTypeName") String sensorTypeName){
        try {
            return configSensorDatabaseMapper.findAllByTypeName(sensorTypeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据所属测站ID查询
    @ApiOperation(value = "资产根据所属测站ID查询", httpMethod = "GET", notes = "资产根据所属测站ID查询")
    @GetMapping("/findAllByOrgID")
    public List<ConfigSensorDatabase> findAllByOrgID(@RequestParam(value = "manageOrgId") Integer manageOrgId){
        try {
            return configSensorDatabaseMapper.findAllByOrgID(manageOrgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据所属测站名称模糊查询
    @ApiOperation(value = "资产根据所属测站名称模糊查询", httpMethod = "GET", notes = "资产根据所属测站名称模糊查询")
    @GetMapping("/findAllByOrgName")
    public List<ConfigSensorDatabase> findAllByOrgName(@RequestParam(value = "manageOrgName") String manageOrgName){
        try {
            return configSensorDatabaseMapper.findAllByOrgName(manageOrgName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据资产使用状态进行查询
    @ApiOperation(value = "资产根据资产使用状态进行查询", httpMethod = "GET", notes = "资产根据资产使用状态进行查询")
    @GetMapping("/findAllByUseStatus")
    public List<ConfigSensorDatabase> findAllByUseStatus(@RequestParam(value = "sensorUseStatus") String sensorUseStatus){
        try {
            return configSensorDatabaseMapper.findAllByUseStatus(sensorUseStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
