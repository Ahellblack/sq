package com.siti.wisdomhydrologic.operation.service;

import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;

import javax.servlet.http.HttpSession;

/**
 * Created by zyw on 2019/7/31.
 */
public interface StationBrokenService {
    PageInfo<ReportStationBroken> getAll(HttpSession session, int page, int pageSize, String createDate, String stationName, Integer status);

    int insert(ReportStationBroken reportStationBroken);

    int update(ReportStationBroken reportStationBroken);

    int delete(Integer reportId);

    int insertDataMantain();

}
