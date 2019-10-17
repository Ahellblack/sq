package com.siti.wisdomhydrologic.document.controller;

import com.siti.wisdomhydrologic.document.mapper.StatisticWaterMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/10/16.
 */
@RequestMapping("statistics")
@RestController
public class StatisticsWaterController {

    @Resource
    StatisticWaterMapper statisticWaterMapper;

    @GetMapping("getWaterStatistics")
    public Map<String, Object> getWater() {
        Calendar calendar = Calendar.getInstance();
        String databaseName = null;
        String year = calendar.get(Calendar.YEAR) + "";
        if (calendar.get(Calendar.YEAR) <= 2020) {
            databaseName = "history_day_sensor_data_2016_2020";
        } else {
            databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("水位最高值", statisticWaterMapper.getMaxWater(databaseName, year));
        map.put("水位最低值", statisticWaterMapper.getMinWater(databaseName, year));
        map.put("三林超出水位预警次数",statisticWaterMapper.getWaterOver(databaseName, year,"1630483"));
        map.put("年份",calendar.get(Calendar.YEAR));
        return map;
    }

}
