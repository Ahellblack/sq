package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;

import java.util.List;

/**
 * Created by dell on 2019/7/26.
 */
public interface HyetometerService {

    List<ReportHyetometerTest> getAll(String createTime, String createBy, Integer stationId);
    int delByReportId(Integer reportId);
}
