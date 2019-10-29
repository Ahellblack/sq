package com.siti.wisdomhydrologic.document.controller;

import com.siti.wisdomhydrologic.document.mapper.StatisticsRainMapper;
import com.siti.wisdomhydrologic.document.mapper.StatisticsTideMapper;
import com.siti.wisdomhydrologic.document.vo.TideGroupVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/10/16.
 */
@RequestMapping("statistics")
@RestController
public class StatisticsTideController {

    @Resource
    StatisticsTideMapper statisticsTideMapper;

    @GetMapping("getTideStatistics")
    public Map<String, Object> getTideStatistics() {
        Calendar calendar = Calendar.getInstance();
        String databaseName = null;
        String year = calendar.get(Calendar.YEAR)+"";
        if (calendar.get(Calendar.YEAR) <= 2020) {
            databaseName = "history_day_sensor_data_2016_2020";
        } else {
            databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
        }
        Map<String,Object> map = new HashMap<>();
        TideGroupVo LCtide = statisticsTideMapper.getTide(databaseName, year, "18502");
        TideGroupVo GQtide = statisticsTideMapper.getTide(databaseName,year,"16802");
        BigDecimal bd = new BigDecimal(GQtide.getAvgV());
        Double avg = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        GQtide.setAvgV(avg);
        map.put("芦潮港潮位",LCtide);
        map.put("芦潮港潮位超过4.0",statisticsTideMapper.getTideOver(databaseName,year,"1850281",4.0));
        map.put("芦潮港潮位超过4.5",statisticsTideMapper.getTideOver(databaseName,year,"1850281",4.5));
        map.put("高桥外侧亭潮位",GQtide);
        map.put("高桥外侧亭潮位超过4.0",statisticsTideMapper.getTideOver(databaseName,year,"1680281",4.0));
        map.put("高桥外侧亭潮位超过4.5",statisticsTideMapper.getTideOver(databaseName,year,"1680281",4.5));
        map.put("年份",calendar.get(Calendar.YEAR));
        return map;
    }


}
