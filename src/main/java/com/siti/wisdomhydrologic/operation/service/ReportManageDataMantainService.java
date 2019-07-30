package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
public interface ReportManageDataMantainService {

    List<ReportManageDataMantain> getByCreateDate(Date createDate);
    int delete(Integer reportId);
    int update(ReportManageDataMantain reportManageDataMantain);
}
