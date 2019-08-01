package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.service.Impl.StationCheckMantainServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@RequestMapping("/stationCheck")
@RestController
public class StationCheckMantainController {
    @Resource
    private StationCheckMantainServiceImpl stationCheckMantainService;

    @GetMapping("/getAll")
    public List<ReportStationCheckMantain> getAll(){
        return stationCheckMantainService.getAll();
    }
    @PostMapping("/insert")
    public int insert(@RequestBody ReportStationCheckMantain reportStationCheckMantain){
        return stationCheckMantainService.insert(reportStationCheckMantain);
    }
    @GetMapping("/delete")
    public int delete(Integer reportId){
        return stationCheckMantainService.delete(reportId);
    }
    @PostMapping("/update")
    public int update(@RequestBody ReportStationCheckMantain reportStationCheckMantain){
        return stationCheckMantainService.update(reportStationCheckMantain);
    }
}
