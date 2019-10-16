package com.siti.wisdomhydrologic.document.controller;

import com.siti.wisdomhydrologic.document.mapper.StatisticsRainMapper;
import com.siti.wisdomhydrologic.document.vo.RainGroupVo;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/10/16.
 */
@RequestMapping("statistics")
@RestController
public class StatisticsRainController {

    @Resource
    StatisticsRainMapper statisticsRainMapper;

    @GetMapping("getRainStatistics")
    public Map<String,Object> getRainStatistics(){
        Calendar calendar = Calendar.getInstance();
        String databaseName = null;
        String year = calendar.get(Calendar.YEAR)+"";
        if (calendar.get(Calendar.YEAR) <= 2020) {
            databaseName = "history_day_sensor_data_2016_2020";
        } else {
            databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
        }
        Map<String,Object> map = new HashMap<>();

        map.put("全年雨量",statisticsRainMapper.getYearRainSum(databaseName,year));
        map.put("全年降雨天数",statisticsRainMapper.getYearRainNumber(databaseName, year));
        map.put("汛期降雨量",statisticsRainMapper.getFloorSeasonRainSum(databaseName,year));
        map.put("汛期降雨天数",statisticsRainMapper.getFloorSeasonRainNumber(databaseName, year));
        map.put("各月雨量",statisticsRainMapper.getRainSumGroupByMonth(databaseName, year));
        return  map;
    }

    @GetMapping("getRainDistribution")
    public Map<String,Object> getRainDistrbution(){
        Map<String,Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        String databaseName = null;
        String year = calendar.get(Calendar.YEAR)+"";
        if (calendar.get(Calendar.YEAR) <= 2020) {
            databaseName = "history_day_sensor_data_2016_2020";
        } else {
            databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
        }
        List<RainGroupVo> rainDistrbution = statisticsRainMapper.getRainDistrbution(databaseName, year);
        RainGroupVo maxVo = new RainGroupVo(0.0,"");
        rainDistrbution.forEach(data->{
            if(data.getSum()>maxVo.getSum()){
                maxVo.setSum(data.getSum());
                maxVo.setStationName(data.getStationName());
            }
        });
        RainGroupVo minVo = new RainGroupVo(maxVo.getSum(),maxVo.getStationName());
        rainDistrbution.forEach(data->{
            if(data.getSum()<=minVo.getSum()){
                minVo.setSum(data.getSum());
                minVo.setStationName(data.getStationName());
            }
        });
        map.put("雨量最高测站",maxVo);
        map.put("雨量最低测站",minVo);
        map.put("雨量分布List",rainDistrbution);

        return map;
    }

    @GetMapping("getMaxStation")
    public Map<String,Object> getMaxStation(){
        Map<String,Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        String databaseName = null;
        String year = calendar.get(Calendar.YEAR)+"";
        if (calendar.get(Calendar.YEAR) <= 2020) {
            databaseName = "history_day_sensor_data_2016_2020";
        } else {
            databaseName = "history_day_sensor_data_" + calendar.get(Calendar.YEAR);
        }
        map.put("日降雨量最大数据",statisticsRainMapper.getMaxRainStation(databaseName, year));

            databaseName = "history_hour_sensor_data_"+ calendar.get(Calendar.YEAR);
        map.put("小时降雨量最大数据",statisticsRainMapper.getMaxHourRainStation(databaseName));
        return map;
    }

}
