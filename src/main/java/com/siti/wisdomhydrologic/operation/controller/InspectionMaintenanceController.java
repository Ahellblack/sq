package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportInspectionMaintenance;
import com.siti.wisdomhydrologic.operation.service.InspectionMaintenanceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
@RequestMapping("/inspectionMaintenance")
@RestController
public class InspectionMaintenanceController {
    @Resource
    private InspectionMaintenanceService inspectionMaintenanceService;

    @GetMapping("/selectByStationId")
    public List<ReportInspectionMaintenance> getByStationId(String stationId) {
        return inspectionMaintenanceService.getByStationId(stationId);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportInspectionMaintenance reportInspectionMaintenance) {
        return inspectionMaintenanceService.insert(reportInspectionMaintenance);
    }

    @GetMapping("/delete")
    public int delete(Integer reportId) {
        return inspectionMaintenanceService.delete(reportId);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportInspectionMaintenance reportInspectionMaintenance) {
        return inspectionMaintenanceService.update(reportInspectionMaintenance);
    }
}
