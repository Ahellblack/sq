package com.siti.wisdomhydrologic.datepull.service;

import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.datepull.vo.HourVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2019/7/18.
 */
public interface DayDataService {

    int addDayData(List<DayVo> dayVo);

    int addHourData(List<HourVo> hourVo);
}
