package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ReportManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ReportManageDataMantainService;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
@Service
public class ReportManageDataMantainServiceImpl implements ReportManageDataMantainService{
    @Resource
    private ReportManageDataMantainMapper reportManageDataMantainMapper;

    public List<ReportManageDataMantain> getByCreateDate(Date createDate) {
        String s = DateOrTimeTrans.Date2TimeString2(createDate);
        return reportManageDataMantainMapper.getByCreateDate(s);
    }

    public int delete(Integer reportId) {
        return reportManageDataMantainMapper.deleteByPrimaryKey(reportId);
    }

    public int update(ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainMapper.updateByPrimaryKey(reportManageDataMantain);
    }

    public int insert(ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainMapper.insert(reportManageDataMantain);
    }
}
