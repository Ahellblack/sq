package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;

import java.util.List;

/**
 * Created by zyw on 2019/7/31.
 */
public interface ManageMantainService {
    List<ReportManageMantain> getAll(String date);

    int update(ReportManageMantain reportManageMantain);
}
