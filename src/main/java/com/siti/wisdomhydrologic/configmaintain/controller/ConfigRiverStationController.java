package com.siti.wisdomhydrologic.configmaintain.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
@RestController
@RequestMapping("/RiverStation")
public class ConfigRiverStationController {

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private RedisBiz redisBiz;
    @Resource
    private SysLogMapper sysLogMapper;
    @RequestMapping("/getAll")
    public List<ConfigRiverStation> getAll(HttpSession session) {
        User user = (User) redisBiz.get(session.getId());
        Integer uid = user.getId();
        return configRiverStationMapper.getAll(uid);
    }

    @RequestMapping("/getAllIDAndName")
    public JSONObject getAllByUser(HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {

            User user = (User) redisBiz.get(session.getId());
            Integer uid = user.getId();

            List<ConfigRiverStation> list = configRiverStationMapper.getAllByUser(uid);
            if (null != list) {
                jsonObject.put("riverStationList", list);
                jsonObject.put("status", 1);
                jsonObject.put("message", "查询成功！");
                return jsonObject;
            } else {
                jsonObject.put("status", 0);
                jsonObject.put("message", "查询结束,无匹配记录！");
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        return jsonObject;
    }

/*
    @RequestMapping("/getAllIDAndName")
    public List<JSONObject> getAllIDAndName(HttpSession session) {
        try {
            List<JSONObject> jsonList = new ArrayList<JSONObject>();
            User user = (User) redisBiz.get(session.getId());
            Integer uid = user.getId();
            // 从ConfigRiverStation取出stationID与stationName，构建json数组传送给前端
            for (ConfigRiverStation rs:configRiverStationMapper.getAll(uid)) {
                JSONObject json = new JSONObject();
                json.put("stationID", rs.getStationId());
                json.put("stationName", rs.getStationName());
                jsonList.add(json);
            }
            return jsonList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
*/

    @RequestMapping("/getPart")
    public List<ConfigRiverStation> getPart() {
        try {
            // 后续需替换为根据登录用户信息查询出的orgID
            Integer orgId = 1002;
            return configRiverStationMapper.getAllStationBySysOrg(orgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // 后续登录做好之后，需要根据登录用户ID，获取到其org_id,再根据org_id 查询对应该看到的测站信息
    @RequestMapping("/getAllStationBySysOrg")
    public List<ConfigRiverStation> getAllStationBySysOrg(@RequestParam(value = "orgId") Integer orgId) {
        try {
            return configRiverStationMapper.getAllStationBySysOrg(orgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据测站级别进行查询
    @RequestMapping("/getAllByStationLevel")
    public List<ConfigRiverStation> getAllByStationLevel(@RequestParam(value = "stationLevel") Integer stationLevel) {
        try {
            return configRiverStationMapper.getAllByStationLevel(stationLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据测站名称模糊进行查询
    @RequestMapping("/getAllByStationName")
    public List<ConfigRiverStation> getAllByStationName(@RequestParam(value = "stationName") String stationName) {
        try {
            return configRiverStationMapper.getAllByStationName(stationName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody ConfigRiverStation configRiverStation,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (null == configRiverStation.getStationCode() || "".equals(configRiverStation.getStationCode())) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "StationCode缺失！");
                return jsonObject;
            }else{
                configRiverStation.setCreateTime(new Timestamp(System.currentTimeMillis()).toString());
                if (0 != configRiverStationMapper.insert(configRiverStation)) {
                    jsonObject.put("status", 1);
                    jsonObject.put("message", "添加成功！");
                    User user = (User) redisBiz.get(session.getId());
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("测站配置表添加")
                            .setFreshVal(configRiverStation+"")
                            .setAction("添加")
                            .setPreviousVal("")
                            .build());
                }else{
                    jsonObject.put("status", 2);
                    jsonObject.put("message", "其它原因，添加失败！");
                    return jsonObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        return jsonObject;
    }

    // 并不是所有内容都更新，注意查看sql语句
    @RequestMapping("/update")
    public JSONObject update(@RequestBody ConfigRiverStation configRiverStation,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (null == configRiverStation.getStationCode() || "".equals(configRiverStation.getStationCode())) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "StationCode缺失！");
                return jsonObject;
            }else{
                configRiverStation.setOrgId(1002);
                configRiverStation.setUpdateTime(new Timestamp(System.currentTimeMillis()).toString());
                if (0 != configRiverStationMapper.update(configRiverStation)) {
                    User user = (User) redisBiz.get(session.getId());
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("测站配置表修改")
                            .setFreshVal(configRiverStation+"")
                            .setAction("修改")
                            .setPreviousVal("")
                            .build());
                }else{
                    jsonObject.put("status", 2);
                    jsonObject.put("message", "其它原因，修改失败！");
                    return jsonObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        return jsonObject;
    }

    // 后续不建议开放删除接口，仅供内部使用
    @RequestMapping(value = "/delete")
    public JSONObject delete(@RequestParam(value = "stationCode") String stationCode,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (null == stationCode || "".equals(stationCode)) {
                jsonObject.put("status", 0);
                jsonObject.put("message", "stationCode缺失，删除失败！");
                return jsonObject;
            } else{
                if (0 != configRiverStationMapper.deleteByStationCode(stationCode)) {
                    jsonObject.put("status", 1);
                    jsonObject.put("message", "删除成功！");
                    User user = (User) redisBiz.get(session.getId());
                    sysLogMapper.insertUserOprLog( new SysLog.builder()
                            .setUsername(user.getUserName())
                            .setOperateDes("测站配置表删除")
                            .setFreshVal(stationCode+"")
                            .setAction("删除")
                            .setPreviousVal("")
                            .build());
                }else{

                    jsonObject.put("status", 2);
                    jsonObject.put("message", "其它原因，删除失败！");
                    return jsonObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("status", -1);
        jsonObject.put("message", "异常错误！");
        return jsonObject;
    }
}
