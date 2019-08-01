package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/1.
 */
public interface StationRainConstrastService {
    List<ReportStationRainConstrast> getByMonth(Date date);
}
