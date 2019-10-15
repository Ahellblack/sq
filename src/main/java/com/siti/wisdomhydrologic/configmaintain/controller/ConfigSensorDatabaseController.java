package com.siti.wisdomhydrologic.configmaintain.controller;

import com.siti.wisdomhydrologic.configfund.mapper.ConfigSensorTypeMapper;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorDatabase;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorDatabaseMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
@RestController
@RequestMapping("/sensorDatabase")
public class ConfigSensorDatabaseController {

    @Resource
    private ConfigSensorDatabaseMapper configSensorDatabaseMapper;

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;

    @Resource
    private ConfigSensorTypeMapper configSensorTypeMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SysLogMapper sysLogMapper;
    @RequestMapping("/getAll")
    public List<ConfigSensorDatabase> getAll(){
        try {
            return configSensorDatabaseMapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping("/getAllAndName")
    public List<ConfigSensorDatabase> getAllAndName(){
        try {
            return configSensorDatabaseMapper.getAllAndName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/insert")
    public int insert(@RequestBody ConfigSensorDatabase configSensorDatabase, HttpSession session){
        try {
            // 设置创建人ID以及创建时间
            configSensorDatabase.setCreateBy(3);
            configSensorDatabase.setCreateTime(new Timestamp(System.currentTimeMillis()));

            // 根据测站ID查询测站名称
            Integer stationID = configSensorDatabase.getManageOrgId();
            configSensorDatabase.setManageOrgName(configRiverStationMapper.getStationNameByStationID(stationID));

            // 根据资产类型ID查询资产类型名称
            String sensorTypeId = configSensorDatabase.getSensorTypeId();
            configSensorDatabase.setSensorTypeName(configSensorTypeMapper.getSensorTypeNameBySensorTypeID(sensorTypeId));


            // -1为“添加失败，资产ID重复！”；1为“添加成功”；其它均为“添加失败！”
            if (null != configSensorDatabaseMapper.findByPropertyCode(configSensorDatabase.getPropertyCode())) {
                return -1;
            } else if (1 == configSensorDatabaseMapper.insert(configSensorDatabase)){

                User user = (User) userInfoService.get();
                sysLogMapper.insertUserOprLog( new SysLog.builder()
                        .setUsername(user.getUserName())
                        .setOperateDes("仓库表添加")
                        .setFreshVal(configSensorDatabase.toString())
                        .setAction("添加")
                        .setPreviousVal("")
                        .build());
                return 1;
            } else{
                return -2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 并不是所有内容都更新，注意查看sql语句
    @RequestMapping("/update")
    public int update(@RequestBody ConfigSensorDatabase configSensorDatabase,HttpSession session){
        // 1为“更新成功”；其它均为“更新失败！”
        try {
            // 设置修改人ID以及修改时间
            configSensorDatabase.setUpdateBy(3);
            configSensorDatabase.setUpdateTime(new Timestamp(System.currentTimeMillis()));

            // 根据测站ID查询测站名称
            Integer stationID = configSensorDatabase.getManageOrgId();
            configSensorDatabase.setManageOrgName(configRiverStationMapper.getStationNameByStationID(stationID));

            // 根据资产类型ID查询资产类型名称
            String sensorTypeId = configSensorDatabase.getSensorTypeId();
            configSensorDatabase.setSensorTypeName(configSensorTypeMapper.getSensorTypeNameBySensorTypeID(sensorTypeId));

            if (1 == configSensorDatabaseMapper.update(configSensorDatabase)){
                User user = (User) userInfoService.get();
                sysLogMapper.insertUserOprLog( new SysLog.builder()
                        .setUsername(user.getUserName())
                        .setOperateDes("仓库表修改")
                        .setFreshVal(configSensorDatabase.toString())
                        .setAction("修改")
                        .setPreviousVal("")
                        .build());
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 后续不建议开放删除接口，仅供内部使用
    @RequestMapping("/delete")
    public int delete(@RequestParam(value = "propertyCode")  String propertyCode) {
        try {
            return configSensorDatabaseMapper.deleteByPropertyCode(propertyCode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    // 根据资产ID查询
    @RequestMapping("/findByPropertyCode")
    public ConfigSensorDatabase findByPropertyCode(@RequestParam(value = "propertyCode") String propertyCode){
        try {
            return configSensorDatabaseMapper.findByPropertyCode(propertyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据传感器编号查询
    @RequestMapping("/findAllBySensorCode")
    public List<ConfigSensorDatabase> findAllBySensorCode(@RequestParam(value = "sensorCode") String sensorCode){
        try {
            return configSensorDatabaseMapper.findAllBySensorCode(sensorCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据资产类型ID查询
    @RequestMapping("/findAllByTypeID")
    public List<ConfigSensorDatabase> findAllByTypeID(@RequestParam(value = "sensorTypeId") String sensorTypeId){
        try {
            return configSensorDatabaseMapper.findAllByTypeID(sensorTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据资产类型名模糊查询
    @RequestMapping("/findAllByTypeName")
    public List<ConfigSensorDatabase> findAllByTypeName(@RequestParam(value = "sensorTypeName") String sensorTypeName){
        try {
            return configSensorDatabaseMapper.findAllByTypeName(sensorTypeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据所属测站ID查询
    @RequestMapping("/findAllByOrgID")
    public List<ConfigSensorDatabase> findAllByOrgID(@RequestParam(value = "manageOrgId") Integer manageOrgId){
        try {
            return configSensorDatabaseMapper.findAllByOrgID(manageOrgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据所属测站名称模糊查询
    @RequestMapping("/findAllByOrgName")
    public List<ConfigSensorDatabase> findAllByOrgName(@RequestParam(value = "manageOrgName") String manageOrgName){
        try {
            return configSensorDatabaseMapper.findAllByOrgName(manageOrgName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据资产使用状态进行查询
    @RequestMapping("/findAllByUseStatus")
    public List<ConfigSensorDatabase> findAllByUseStatus(@RequestParam(value = "sensorUseStatus") String sensorUseStatus){
        try {
            return configSensorDatabaseMapper.findAllByUseStatus(sensorUseStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
