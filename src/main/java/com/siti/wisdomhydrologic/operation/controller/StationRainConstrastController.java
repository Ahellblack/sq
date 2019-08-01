package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.service.Impl.StationRainConstrastServiceImpl;
import com.siti.wisdomhydrologic.operation.service.StationRainConstrastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @GetMapping("/getAll")
    public List<ReportStationRainConstrast> getByMonth(Date date){
        return stationRainConstrastService.getByMonth(date);
    }


}
