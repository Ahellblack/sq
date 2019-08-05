package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportInspectionMaintenance;
import com.siti.wisdomhydrologic.operation.mapper.InspectionMaintenanceMapper;
import com.siti.wisdomhydrologic.operation.service.InspectionMaintenanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
@Service
public class InspectionMaintenanceServiceImpl implements InspectionMaintenanceService {

    @Resource
    private InspectionMaintenanceMapper inspectionMaintenanceMapper;

    @Override
    public List<ReportInspectionMaintenance> getByStationId(String stationId, String monthDate) {
        return inspectionMaintenanceMapper.getByStationId(stationId, monthDate);
    }

    @Override
    public int insert(ReportInspectionMaintenance reportInspectionMaintenance) {
        return inspectionMaintenanceMapper.insert(reportInspectionMaintenance);
    }

    @Override
    public int delete(Integer reportId) {
        return inspectionMaintenanceMapper.deleteByReportId(reportId);
    }

    @Override
    public int update(ReportInspectionMaintenance reportInspectionMaintenance) {
        return inspectionMaintenanceMapper.updateByPrimaryKey(reportInspectionMaintenance);
    }
}
