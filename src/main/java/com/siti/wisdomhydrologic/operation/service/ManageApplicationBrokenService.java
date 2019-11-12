package com.siti.wisdomhydrologic.operation.service;

import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;

import javax.servlet.http.HttpSession;

/**
 * Created by zyw on 2019/7/31.
 */
public interface ManageApplicationBrokenService {
    PageInfo<ReportManageApplicationBroken> getAll(HttpSession session,int page, int pageSize, String createDate, String stationName,Integer status);

    int insert(ReportManageApplicationBroken reportManageApplicationBroken);

    int update(ReportManageApplicationBroken reportManageApplicationBroken);

    int delete(Integer reportId);

    int insertDataMantain();

}
