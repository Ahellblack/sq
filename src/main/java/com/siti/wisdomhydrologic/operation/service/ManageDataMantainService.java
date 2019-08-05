package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/7/30.
 */
public interface
ManageDataMantainService {

    Map<String,Object> getByCreateDate(String createDate);
    int delete(Integer reportId);
    int update(ReportManageDataMantain reportManageDataMantain);
}
