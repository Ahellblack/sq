package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.service.Impl.StationBrokenServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@RequestMapping("/stationBroken")
@RestController
public class StationBrokenController {

    @Resource
    private StationBrokenServiceImpl stationBrokenService;
    @RequestMapping("/getALL")
    public List<ReportStationBroken> getAll(){
        return stationBrokenService.getAll();
    }
    @GetMapping("/delete")
    public int delete(Integer reportId){
        return stationBrokenService.delete(reportId);
    }
    @PostMapping("/update")
    public int update(@RequestBody ReportStationBroken reportStationBroken){
        return stationBrokenService.update(reportStationBroken);
    }
    @PostMapping("/insert")
    public int insert(@RequestBody ReportStationBroken reportStationBroken){
        return stationBrokenService.insert(reportStationBroken);
    }



}
