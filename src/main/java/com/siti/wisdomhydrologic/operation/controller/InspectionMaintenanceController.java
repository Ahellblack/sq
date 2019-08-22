package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.entity.ReportInspectionMaintenance;
import com.siti.wisdomhydrologic.operation.service.Impl.InspectionMaintenanceServiceImpl;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.EasyPoiUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
@RequestMapping("/inspectionMaintenance")
@RestController
public class InspectionMaintenanceController {
    @Resource
    private InspectionMaintenanceServiceImpl inspectionMaintenanceService;

    /**
     * 查询根据测站id 及 月份查询表
     * @PRARM String stationId 测站id
     * @PRARM String monthDate 月份日期 YYYY-MM
     * */
    @GetMapping("/selectByStationId")
    public List<ReportInspectionMaintenance> getByStationId(String stationId,String monthDate) {
        if(stationId!=null && monthDate != null){
            return inspectionMaintenanceService.getByStationId(stationId,monthDate);
        }
        monthDate = DateTransform.Date2String(new Date(),"YYYY-MM");
        return inspectionMaintenanceService.getByStationId("999",monthDate);
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

    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response,String stationId,String monthDate) {
        List<ReportInspectionMaintenance> list = inspectionMaintenanceService.getByStationId(stationId,monthDate);
        EasyPoiUtil.exportExcel(list, "记录表", "雨量计滴水", ReportHyetometerTest.class, "雨量计滴水实验记录表.xls", response);
    }

}
