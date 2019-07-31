package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import com.siti.wisdomhydrologic.operation.mapper.ReportManageMantainMapper;
import com.siti.wisdomhydrologic.operation.service.ReportManageMantainService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class ReportManageMantainServiceImpl implements ReportManageMantainService {
    @Resource
    private ReportManageMantainMapper reportManageMantainMapper;

    public List<ReportManageMantain> getAll(String date) {
        return reportManageMantainMapper.getByDate(date);
    }

    @Override
    public int insert(ReportManageMantain reportManageMantain) {
        return reportManageMantainMapper.insert(reportManageMantain);
    }

    @Override
    public int update(ReportManageMantain reportManageMantain) {
        return reportManageMantainMapper.updateByPrimaryKey(reportManageMantain);
    }
    public int delete(Integer reportId){
        return reportManageMantainMapper.deleteById(reportId);
    }
}
