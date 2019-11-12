package com.siti.wisdomhydrologic.configmaintain.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by zyw on 2019/8/16.
 */
@RestController
@RequestMapping("/module")
@Api(value = "传感器配置表controller", tags = {"传感器配置表"})
public class ConfigSensorSectionModuleController {

    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SysLogMapper sysLogMapper;

    @ApiOperation(value = "传感器", httpMethod = "GET", notes = "传感器")
    @GetMapping("/getAll")
    private List<ConfigSensorSectionModule> getList(HttpSession session,Integer stationCode) {
        try {
            User user = (User) userInfoService.get();
            return configSensorSectionModuleMapper.getStationByCode(user.getOrgList().get(0).getId(),stationCode);
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
    public JSONObject insert(@RequestBody ConfigSensorSectionModule configSensorSectionModule, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            configSensorSectionModule.setCreateTime(new Date());
            configSensorSectionModule.setSensorCode(configSensorSectionModule.getStationCode()+configSensorSectionModule.getSensorCode());
            configSensorSectionModule.setSensorName(configSensorSectionModule.getStationName()+configSensorSectionModule.getSensorName());
            System.out.println(configSensorSectionModule.toString());
            User user = (User) userInfoService.get();
            sysLogMapper.insertUserOprLog( new SysLog.builder()
                    .setUsername(user.getUserName())
                    .setOperateDes("传感器表添加")
                    .setFreshVal(configSensorSectionModule.toString())
                    .setAction("添加")
                    .setPreviousVal("")
                    .build());
            /***
             * sensorTypeId
             * */
            ConfigSensorSectionModule moduleBySectionCode = configSensorSectionModuleMapper.findModuleBySectionCode(configSensorSectionModule.getSectionCode());
            ConfigSensorSectionModule moduleByStationName = configSensorSectionModuleMapper.findOneModuleBySectionName(configSensorSectionModule.getStationName());
            if(moduleBySectionCode!=null || moduleByStationName!=null){
                jsonObject.put("status", 2);
                jsonObject.put("message", "重复sectionCode！");
                return jsonObject;
            }else {
                int status = configSensorSectionModuleMapper.insert(configSensorSectionModule);
                if(status==0) {
                    jsonObject.put("status", status);
                    jsonObject.put("message", "其它原因，添加失败！");
                    return jsonObject;
                }else{
                    jsonObject.put("status", 1);
                    jsonObject.put("message", "添加成功！");
                    return jsonObject;
                }
            }

        } catch (Exception e) {
            jsonObject.put("status", -1);
            jsonObject.put("message", "异常错误！");
        }
        return jsonObject;
    }

    // 修改数据，部分字段不修改
    @PostMapping("/update")
    public JSONObject update(@RequestBody ConfigSensorSectionModule configSensorSectionModule,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            User user = (User) userInfoService.get();
            sysLogMapper.insertUserOprLog( new SysLog.builder()
                    .setUsername(user.getUserName())
                    .setOperateDes("传感器表修改")
                    .setFreshVal(configSensorSectionModule.toString())
                    .setAction("修改")
                    .setPreviousVal("")
                    .build());
            int status = configSensorSectionModuleMapper.update(configSensorSectionModule);
            if(status==0) {
                jsonObject.put("status", status);
                jsonObject.put("message", "修改失败，检查传感器名称或名称编号！");
                return jsonObject;
            }else{
                jsonObject.put("status", 1);
                jsonObject.put("message", "修改成功！");
                return jsonObject;
            }
        }catch (Exception e) {
            jsonObject.put("status", -1);
            jsonObject.put("message", "异常错误！");
        }
        return jsonObject;
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
