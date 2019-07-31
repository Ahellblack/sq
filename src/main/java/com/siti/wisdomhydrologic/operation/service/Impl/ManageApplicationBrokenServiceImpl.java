package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.ManageApplicationBrokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class ManageApplicationBrokenServiceImpl  implements ManageApplicationBrokenService {
    @Resource
    private ManageApplicationBrokenMapper reportManageApplicationBrokenMapper;

    public List<ReportManageApplicationBroken> getAll() {
        return reportManageApplicationBrokenMapper.getAll();
    }

    @Override
    public int insert(ReportManageApplicationBroken reportManageApplicationBroken) {
        return reportManageApplicationBrokenMapper.insert(reportManageApplicationBroken);
    }

    @Override
    public int update(ReportManageApplicationBroken reportManageApplicationBroken) {
        return reportManageApplicationBrokenMapper.updateByPrimaryKey(reportManageApplicationBroken);
    }

    @Override
    public int delete(Integer reportId) {
        return reportManageApplicationBrokenMapper.deleteById(reportId);
    }
}
