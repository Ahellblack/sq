package com.siti.wisdomhydrologic.operation.service;

import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zyw on 2019/8/1.
 */
public interface StationRainConstrastService {

    List<Map<String, Object>> getByMonth(String date);

}
