package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.mapper.StationCheckMantainMapper;
import com.siti.wisdomhydrologic.operation.service.StationCheckMantainService;
import com.siti.wisdomhydrologic.quartz.job.DynamicJob;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class StationCheckMantainServiceImpl implements StationCheckMantainService {

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private StationCheckMantainMapper stationCheckMantainMapper;

    public List<ReportStationCheckMantain> getAll() {
        return stationCheckMantainMapper.selectAll();
    }

    public int insert(ReportStationCheckMantain reportStationCheckMantain) {
        ConfigRiverStation allByStationName = configRiverStationMapper.getAllByCode(reportStationCheckMantain.getStationCode());

        if (allByStationName != null) {
            reportStationCheckMantain.setStationName(allByStationName.getStationName());
        }
        try {
            return stationCheckMantainMapper.insert(reportStationCheckMantain);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(Integer reportId) {
        return stationCheckMantainMapper.deleteById(reportId);
    }

    public int update(ReportStationCheckMantain reportStationCheckMantain) {
        ConfigRiverStation allByStationName = configRiverStationMapper.getAllByCode(reportStationCheckMantain.getStationCode());

        if (allByStationName != null) {
            reportStationCheckMantain.setStationName(allByStationName.getStationName());
        }
        try {
            return stationCheckMantainMapper.update(reportStationCheckMantain);
        } catch (Exception e) {
            return 0;
        }
    }

}
