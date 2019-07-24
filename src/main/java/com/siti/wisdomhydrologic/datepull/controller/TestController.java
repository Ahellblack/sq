package com.siti.wisdomhydrologic.datepull.controller;

import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/19.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private DayDataMapper dayDataMapper;

    @RequestMapping("insert")
    public int insertList(){
        List<DayVo> list = new ArrayList<>();
        while(list.size()<1000){
            DayVo dayVo = new DayVo();
            dayVo.setSensorTypeId(111);
            dayVo.setV(1.1);
            dayVo.setTime("3333333");
            dayVo.setStationId(11);
            dayVo.setS(1);
            dayVo.setSenId(99);
            dayVo.setStationName("1");
            dayVo.setSensorDataUnit("1");
            dayVo.setSensorTypeName("1");
            dayVo.setAvgS(1);
            dayVo.setAvgV(1.1);
            dayVo.setMaxS(1);
            dayVo.setMaxT("1");
            dayVo.setMaxV(1);
            dayVo.setMinS(1);
            dayVo.setMinT("1");
            dayVo.setMinV(1.1);
            list.add(dayVo);
        }
       return dayDataMapper.addDayData(list);
    }


}
