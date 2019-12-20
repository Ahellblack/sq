package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;

import java.util.List;

/**
 * Created by zyw on 2019/7/31.
 */
public interface ManageApplicationBrokenService {
    List<ReportManageApplicationBroken> getAll(String createDate, String stationId);

    int delete(Integer reportId);

    int update(ReportManageApplicationBroken reportManageApplicationBroken);

    int insert(ReportManageApplicationBroken reportManageApplicationBroken);
}
