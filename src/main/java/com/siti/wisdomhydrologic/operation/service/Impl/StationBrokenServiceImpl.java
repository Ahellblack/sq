package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.StationBrokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class StationBrokenServiceImpl implements StationBrokenService {

    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;

    @Override
    public List<ReportStationBroken> getAll() {


        return reportStationBrokenMapper.getAll();
    }

    @Override
    public int delete(Integer reportId) {
        return reportStationBrokenMapper.deleteById(reportId);
    }

    @Override
    public int update(ReportStationBroken reportStationBroken) {
        return reportStationBrokenMapper.updateByPrimaryKey(reportStationBroken);
    }

    @Override
    public int insert(ReportStationBroken reportStationBroken) {
        return reportStationBrokenMapper.insertData(reportStationBroken);
    }
}
