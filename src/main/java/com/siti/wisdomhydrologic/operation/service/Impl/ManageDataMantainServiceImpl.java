package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ManageDataMantainService;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
@Service
public class ManageDataMantainServiceImpl implements ManageDataMantainService {
    @Resource
    private ManageDataMantainMapper reportManageDataMantainMapper;

    public List<ReportManageDataMantain> getByCreateDate(Date createDate) {
        String s = null;
        if(createDate != null){
            s = DateOrTimeTrans.Date2TimeString2(createDate);
        }
        return reportManageDataMantainMapper.getByCreateDate(s);
    }

    public int delete(Integer reportId) {
        return reportManageDataMantainMapper.deleteByReportId(reportId);
    }

    public int update(ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainMapper.updateByPrimaryKey(reportManageDataMantain);
    }

    public int insert(ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainMapper.insert(reportManageDataMantain);
    }
}
