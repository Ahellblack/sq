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
public class InspectionMaintenanceServiceImpl implements InspectionMaintenanceService{

    @Resource
    private InspectionMaintenanceMapper inspectionMaintenanceMapper;

    @Override
    public List<ReportInspectionMaintenance> getByStationId(String stationId) {
        return inspectionMaintenanceMapper.getByStationId(stationId);
    }
}
