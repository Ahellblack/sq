package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ReportManageApplicationBrokenService {
    List<ReportManageApplicationBroken> getAll();

    int insert(ReportManageApplicationBroken reportManageApplicationBroken);

    int update(ReportManageApplicationBroken reportManageApplicationBroken);
}
