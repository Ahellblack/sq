package com.siti.wisdomhydrologic.operation.service.Impl;


import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.mapper.HyetometerMapper;
import com.siti.wisdomhydrologic.operation.service.HyetometerService;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/26.
 */
@Service
public class HyetometerServiceImpl implements HyetometerService {

    @Resource
    ConfigRiverStationMapper configRiverStationMapper;
    @Resource
    private HyetometerMapper reportHyetometerMapper;

    public List<ReportHyetometerTest> getAll(String createTime, String stationName) {
        return reportHyetometerMapper.getAll(createTime, stationName);
    }

    public int delByReportId(Integer reportId) {
        return reportHyetometerMapper.delByReportId(reportId);
    }

    public int insert(ReportHyetometerTest reportHyetometer) {

        List<ConfigRiverStation> allByStationName = configRiverStationMapper.getAllByStationName(reportHyetometer.getStationName());
        reportHyetometer.setStationCode(allByStationName.get(0).getStationId());
        if (reportHyetometer.getCreateTime() == null) {
            reportHyetometer.setCreateTime(DateTransform.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }
        reportHyetometer.setLibraryDate(reportHyetometer.getCreateTime());
        try {
            reportHyetometerMapper.insert(reportHyetometer);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int update(ReportHyetometerTest reportHyetometer) {
        List<ConfigRiverStation> allByStationName = configRiverStationMapper.getAllByStationName(reportHyetometer.getStationName());
        reportHyetometer.setStationCode(allByStationName.get(0).getStationId());
        try {
            reportHyetometerMapper.update(reportHyetometer);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public int delByReportIdList(List<Integer> reportIdList) {
        int sum = 0;
        for (Integer id : reportIdList) {
            int i = delByReportId(id);
            sum = sum + i;
        }
        return sum;
    }
}
