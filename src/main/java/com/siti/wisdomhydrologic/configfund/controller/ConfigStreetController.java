package com.siti.wisdomhydrologic.configfund.controller;

import com.siti.wisdomhydrologic.configfund.entity.ConfigStreet;
import com.siti.wisdomhydrologic.configfund.mapper.ConfigStreetMapper;
import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/street")
@Api(value="街镇配置表controller",tags={"街镇配置表"})
public class ConfigStreetController {

    @Resource
    private ConfigStreetMapper configStreetMapper;
    @Resource
    private RedisBiz redisBiz;
    @Resource
    private SysLogMapper sysLogMapper;
    @ApiOperation(value = "街道添加", httpMethod = "GET", notes = "街道添加")
    @PostMapping(value="/insert")
    private int insert(@RequestBody ConfigStreet configStreet, HttpSession session) {
        try {
            System.out.println(configStreet.getSysOrgName());
            User user = (User) redisBiz.get(session.getId());
            sysLogMapper.insertUserOprLog( new SysLog.builder()
                    .setUsername(user.getUserName())
                    .setOperateDes("街道表添加")
                    .setFreshVal(configStreet.toString())
                    .setAction("添加")
                    .setPreviousVal("")
                    .build());
            return configStreetMapper.insert(configStreet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation(value = "街道修改", httpMethod = "POST", notes = "街道修改")
    @PostMapping(value="/update")
    private int update(@RequestBody ConfigStreet configStreet,HttpSession session) {
        try {
            User user = (User) redisBiz.get(session.getId());
            sysLogMapper.insertUserOprLog( new SysLog.builder()
                    .setUsername(user.getUserName())
                    .setOperateDes("街道修改")
                    .setFreshVal(configStreet.toString())
                    .setAction("修改")
                    .setPreviousVal("")
                    .build());
            return configStreetMapper.update(configStreet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @ApiOperation(value = "街道删除", httpMethod = "GET", notes = "街道删除")
    @GetMapping(value="/delete")
    private int delete(@RequestParam(value = "streetId")  Integer streetID,HttpSession session) {
        try {
            User user = (User) redisBiz.get(session.getId());
            sysLogMapper.insertUserOprLog( new SysLog.builder()
                    .setUsername(user.getUserName())
                    .setOperateDes("街道删除")
                    .setFreshVal(streetID.toString())
                    .setAction("删除")
                    .setPreviousVal("")
                    .build());
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
