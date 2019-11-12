package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;

import java.util.List;

/**
 * Created by zyw on 2019/7/31.
 */
public interface StationBrokenService {
    List<ReportStationBroken> getAll(String createDate,String stationId);

    int delete(Integer reportId);

    int update(ReportStationBroken reportStationBroken);

    int insert(ReportStationBroken reportStationBroken);
}
