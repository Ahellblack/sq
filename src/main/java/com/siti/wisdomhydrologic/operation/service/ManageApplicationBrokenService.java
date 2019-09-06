package com.siti.wisdomhydrologic.operation.service;

import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;

/**
 * Created by dell on 2019/7/31.
 */
public interface ManageApplicationBrokenService {
    PageInfo<ReportManageApplicationBroken> getAll(int page, int pageSize, String createDate,String stationName);

    int insert(ReportManageApplicationBroken reportManageApplicationBroken);

    int update(ReportManageApplicationBroken reportManageApplicationBroken);

    int delete(Integer reportId);

    int insertDataMantain(String date);

}
