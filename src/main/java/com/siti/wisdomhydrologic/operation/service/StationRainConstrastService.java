package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/1.
 */
public interface StationRainConstrastService {
    List<ReportStationRainConstrastVo> getAutoByMonth(Date date);
    List<ReportStationRainConstrastVo> getBaseByMonth(Date date);
    List<ReportStationRainConstrastVo> getDiffByMonth(Date date);
    List<ReportStationRainConstrast> getAll(Date date);
    List<ReportStationRainConstrastVo> getByMonth(String date);

    /*int update(ReportStationRainConstrastVo reportStationRainConstrastVo);*/
}
