package com.siti.wisdomhydrologic.operation.service.Impl;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.mapper.StationCheckMantainMapper;
import com.siti.wisdomhydrologic.operation.service.StationCheckMantainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@Service
public class StationCheckMantainServiceImpl implements StationCheckMantainService {

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private StationCheckMantainMapper stationCheckMantainMapper;


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
