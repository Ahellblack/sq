package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.statistics.entity.DeviceChange;
import com.siti.wisdomhydrologic.statistics.mapper.DeviceMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.siti.wisdomhydrologic.util.MonthListUtil;
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

    @RequestMapping("/devReplace")
    public Map<String, Object> getList(Integer stationId, Integer dateType, Integer year, Integer quarter, String month) {
        Map<String, Object> map = new HashMap<>();
        if(dateType ==null || "".equals(dateType) || year==null || "".equals(year)){
            map.put("status", -2);
            map.put("message", "参数错误");
            return map;
        }
        try {
            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);
            if(list.size() ==0 ){
                map.put("status", -2);
                map.put("message", "参数错误");
                return map;
            }
            List<DeviceChange> dataList = deviceMapper.getList(stationId, year, list);
            Integer sum = 0;
            for (int i = 0; i < dataList.size(); i++) {
                sum += dataList.get(i).getNumber();
            }
            if (dataList.size() > 0) {
                map.put("status", 0);
                map.put("message", "查询成功");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("devReplaceInfo", dataList);
            } else {
                map.put("status", 1);
                map.put("message", "暂无数据");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("devReplaceInfo", null);
            }
        }catch (Exception e){
            map.put("status", -1);
            map.put("message", "查询失败");
        }
        return map;
    }


}
