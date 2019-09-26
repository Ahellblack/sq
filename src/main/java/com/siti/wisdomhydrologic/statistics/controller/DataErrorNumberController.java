package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.statistics.entity.BrokenType;
import com.siti.wisdomhydrologic.statistics.entity.DataError;
import com.siti.wisdomhydrologic.statistics.mapper.DataErrorNumberMapper;
import com.siti.wisdomhydrologic.util.MonthListUtil;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/9/26.
 */
@RequestMapping("/dataError")
@RestController
public class DataErrorNumberController {

    @Resource
    private DataErrorNumberMapper dataErrorNumberMapper;

    @GetMapping("getAll")
    public Map<String, Object> getList(Integer stationId, Integer dateType, Integer year, Integer quarter, String month, String dataTime) {

        Map<String, Object> map = new HashMap<>();
        try {

            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);

            List<DataError> dataList = dataErrorNumberMapper.getList(stationId, year, list, dataTime);

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
                map.put("dataErrorInfo", dataList);
            } else {
                map.put("status", -1);
                map.put("message", "暂无数据");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("dataErrorInfo", null);
            }
        } catch (Exception e) {
            map.put("status", 1);
            map.put("message", "查询失败");
        }
        return map;


    }

}
