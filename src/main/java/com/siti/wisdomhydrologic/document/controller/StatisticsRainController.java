package com.siti.wisdomhydrologic.document.controller;

import com.siti.wisdomhydrologic.document.mapper.StatisticsRainMapper;
import com.siti.wisdomhydrologic.document.vo.RainGroupVo;
import com.siti.wisdomhydrologic.util.RateUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.*;

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
        List<RainGroupVo> rainSumGroup = statisticsRainMapper.getRainSumGroupByMonth(databaseName, year);

        Double allyear = statisticsRainMapper.getYearRainSum(databaseName,year);
        Double allFloor = statisticsRainMapper.getFloorSeasonRainSum(databaseName,year);

        map.put("yearRain",allyear);//全年雨量
        map.put("yearRainDay",statisticsRainMapper.getYearRainNumber(databaseName, year));//全年降雨天数
        map.put("floorRain",allFloor);//汛期降雨量
        map.put("floorRainDay",statisticsRainMapper.getFloorSeasonRainNumber(databaseName, year));//汛期降雨天数
        map.put("rainList",statisticsRainMapper.getRainSumGroupByMonth(databaseName, year));//各月雨量
        map.put("floorRate", RateUtils.accuracy(allFloor, allyear, 2));//汛期占比
        try {
            if (rainSumGroup.size() > 3) {
                map.put("firstRain", rainSumGroup.get(0).getMonth());//最大雨量月份
                map.put("secondRain", rainSumGroup.get(1).getMonth());//第二雨量月份
                map.put("thirdRain", rainSumGroup.get(2).getMonth());//第三雨量月份
                Double rain = rainSumGroup.get((rainSumGroup.size() - 1)).getSum();
                String month = rainSumGroup.get(rainSumGroup.size() - 1).getMonth();
                for (int i = 0; i < rainSumGroup.size(); i++) {
                    if (rain == 0) {//为防止最低月份雨量为0 不可做被除数
                        rain = rainSumGroup.get((rainSumGroup.size() - 1 - i)).getSum();
                        month = rainSumGroup.get((rainSumGroup.size() - 1 - i)).getMonth();
                    } else {
                        break;
                    }
                }
                map.put("finalRain", month);//最低雨量月份'
                double sumRain= rainSumGroup.get(0).getSum();
                Double times = RateUtils.accuracyTimes(sumRain, rain, 2);
                map.put("times",times );//倍数
            }
            map.put("year", calendar.get(Calendar.YEAR));//年份
            return map;
        }catch (Exception e){
            map.put("yearRain",0);//全年雨量
            map.put("yearRainDay",0);//全年降雨天数
            map.put("floorRain",0);//汛期降雨量
            map.put("floorRainDay",0);//汛期降雨天数
            map.put("rainList",0);//各月雨量
            map.put("floorRate",new ArrayList<>());//汛期占比
            map.put("times",0);//倍数
            return map;
        }
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
        map.put("highestRainStation",maxVo);//雨量最高测站
        map.put("lowestRainStation",minVo);//雨量最低测站
        map.put("List",rainDistrbution);//雨量分布List
        map.put("year",calendar.get(Calendar.YEAR));
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
        map.put("dayMax",statisticsRainMapper.getMaxRainStation(databaseName, year));   //日降雨量最大数据

            databaseName = "history_hour_sensor_data_"+ calendar.get(Calendar.YEAR);
        map.put("hourMax",statisticsRainMapper.getMaxHourRainStation(databaseName)); // 小时降雨量最大数据
        map.put("year",calendar.get(Calendar.YEAR));
        return map;
    }

}
