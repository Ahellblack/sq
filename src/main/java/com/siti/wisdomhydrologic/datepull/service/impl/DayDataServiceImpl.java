package com.siti.wisdomhydrologic.datepull.service.impl;

import com.siti.wisdomhydrologic.datepull.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.datepull.service.DayDataService;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.datepull.vo.StationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dell on 2019/7/18.
 */
@Component
public class DayDataServiceImpl implements DayDataService {

    private static final Logger logger = LoggerFactory.getLogger(DayDataServiceImpl.class);

    @Resource
    private DayDataMapper dayDataMapper;

    ReentrantLock lock = new ReentrantLock();

    @Override
    public int addDayData(List<DayVo> dayVo) {
        lock.lock();
        int backInt = 0;
        try {
            List<ConfigSensorSectionModule> stationList = dayDataMapper.getStation();
            for (DayVo d : dayVo) {
                for (ConfigSensorSectionModule station : stationList) {
                    if (d.getSenId() == station.getSectionCode()) {
                        d.setSensorTypeName(station.getSensorName());
                        d.setSensorDataUnit(station.getSectionDataUnit());
                        d.setSensorTypeId(station.getSensorCode());
                        d.setStationId(station.getStationCode());
                        d.setStationName(station.getStationName());
                    }
                }
            }
            backInt = dayDataMapper.addDayData(dayVo);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            lock.unlock();
        }
        return backInt;
    }

    @Override
    public int addHourData(List<DayVo> HourVo) {
        lock.lock();
        int backInt = 0;
        try {
            List<ConfigSensorSectionModule> stationList = dayDataMapper.getStation();
            for (DayVo hour : HourVo) {
                for (ConfigSensorSectionModule station : stationList) {
                    if (hour.getSenId() == station.getSectionCode()) {
                        hour.setSensorTypeName(station.getSensorName());
                        hour.setSensorDataUnit(station.getSectionDataUnit());
                        hour.setSensorTypeId(station.getSensorCode());
                        hour.setStationId(station.getStationCode());
                        hour.setStationName(station.getStationName());
                    }
                }
            }
            backInt = dayDataMapper.addHourData(HourVo);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            lock.unlock();
        }
        return backInt;
    }
}
