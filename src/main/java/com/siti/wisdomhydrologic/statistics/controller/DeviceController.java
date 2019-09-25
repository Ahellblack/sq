package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.statistics.entity.DeviceChange;
import com.siti.wisdomhydrologic.statistics.mapper.DeviceMapper;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/9/23.
 */
@RequestMapping("/device")
@RestController
public class DeviceController {

    @Resource
    private DeviceMapper deviceMapper;

    @RequestMapping("/getAll")
    public List<DeviceChange> getList(String stationName){

        return deviceMapper.getList(stationName);
    }


}
