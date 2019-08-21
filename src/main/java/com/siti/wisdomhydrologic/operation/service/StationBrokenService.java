package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface StationBrokenService {
    List<ReportStationBroken> getAll();

    int delete(Integer reportId);

    int update(ReportStationBroken reportStationBroken);

    int insert(ReportStationBroken reportStationBroken);
}
