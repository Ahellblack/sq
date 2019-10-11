package com.siti.wisdomhydrologic.operation.service;

import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;

import javax.servlet.http.HttpSession;

/**
 * Created by dell on 2019/7/30.
 */
public interface
ManageDataMantainService {

    PageInfo<ReportManageDataMantain> getByCreateDate(int page, int pageSize, String stationId, String alterType, String createDate, HttpSession session);
    int delete(Integer reportId);
    int update(ReportManageDataMantain reportManageDataMantain);

    int insertAbnormalData(String date);
}
