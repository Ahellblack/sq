package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportInspectionMaintenance;

import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
public interface InspectionMaintenanceService {
    List<ReportInspectionMaintenance> getByStationId(String stationId);

    int insert(ReportInspectionMaintenance reportInspectionMaintenance);

    int delete(Integer reportId);
}
