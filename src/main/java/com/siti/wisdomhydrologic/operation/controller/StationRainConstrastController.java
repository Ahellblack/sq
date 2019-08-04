package com.siti.wisdomhydrologic.operation.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.service.Impl.StationRainConstrastServiceImpl;
import com.siti.wisdomhydrologic.operation.service.StationRainConstrastService;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/1.
 */
@RequestMapping("/rainConstrast")
@RestController
public class StationRainConstrastController {
    @Resource
    private StationRainConstrastServiceImpl stationRainConstrastService;

    /**
     * 获取某个月的全部站点数据对表
     * 当没有选择具体某个月时,默认返回上个月的数据
     * @PARAM date 选择查询的月份
     * */
    @GetMapping("/getAll")
    public List<ReportStationRainConstrastVo> getByMonth(String date) {
        List<ReportStationRainConstrastVo> list = stationRainConstrastService.getByMonth(date);
        return list;
    }
    /**
     * 客户可修改基本站数值
     * */
    @GetMapping("/update")
    public int update(ReportStationRainConstrastVo reportStationRainConstrastVo) {
        return stationRainConstrastService.update(reportStationRainConstrastVo);
    }


    @GetMapping("/getAllAuto")
    public List<ReportStationRainConstrastVo> getByAutoMonth(Date date) {
        return stationRainConstrastService.getAutoByMonth(date);
    }

    @GetMapping("/getAllBase")
    public List<ReportStationRainConstrastVo> getByBaseMonth(Date date) {
        return stationRainConstrastService.getBaseByMonth(date);
    }

    @GetMapping("/getAllDiff")
    public List<ReportStationRainConstrastVo> getByDiffMonth(Date date) {
        return stationRainConstrastService.getDiffByMonth(date);
    }


}
