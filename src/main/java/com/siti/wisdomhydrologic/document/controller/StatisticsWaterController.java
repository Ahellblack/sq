package com.siti.wisdomhydrologic.document.controller;

import com.siti.wisdomhydrologic.document.mapper.StatisticWaterMapper;
import com.siti.wisdomhydrologic.util.CaffeineUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyw on 2019/10/16.
 */
@RequestMapping("statistics")
@RestController
public class StatisticsWaterController {

    @Resource
    StatisticWaterMapper statisticWaterMapper;

    @GetMapping("getWaterStatistics")
    public Map<String, Object> getWater() {
        return (Map<String,Object>)CaffeineUtil.build()
                .getValues("getWaterStatistics",(x)->waterStatistics());
    }

    Map<String,Object> waterStatistics(){
        Calendar calendar = Calendar.getInstance();
        String databaseName = null;
        String year = calendar.get(Calendar.YEAR) + "";
        if (calendar.get(Calendar.YEAR) <= 2020) {
            databaseName = "history_day_sensor_data_2016_2020";
        } else {
            databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("maxWater", statisticWaterMapper.getMaxWater(databaseName, year));//水位最高值
        map.put("minWater", statisticWaterMapper.getMinWater(databaseName, year));//水位最低值
        map.put("overSanLin",statisticWaterMapper.getWaterOver(databaseName, year,"1630483"));//三林超出水位预警次数
        map.put("year",calendar.get(Calendar.YEAR));//年份
        return map;
    }

}
