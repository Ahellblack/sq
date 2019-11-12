package com.siti.wisdomhydrologic.datepull.service.impl;

import com.siti.wisdomhydrologic.datepull.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.datepull.mapper.HourDataMapper;
import com.siti.wisdomhydrologic.datepull.service.DayDataService;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.datepull.vo.HourVo;
import com.siti.wisdomhydrologic.datepull.vo.StationVo;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zyw on 2019/7/18.
 */
@Component
public class DayDataServiceImpl implements DayDataService {

    private static final Logger logger = LoggerFactory.getLogger(DayDataServiceImpl.class);

    @Resource
    private DayDataMapper dayDataMapper;
    @Resource
    private HourDataMapper hourDataMapper;

    ReentrantLock lock = new ReentrantLock();

    @Override
    public int addDayData(List<DayVo> dayVo) {
        lock.lock();
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
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            lock.unlock();
        }
        /**
         * 分年份入库
         * */
        String time = DateTransform.Date2String(dayVo.get(0).getTime(),"YYYY-MM-dd HH:mm:ss").substring(0, 4);
        Integer inttime = Integer.valueOf(time);
        if (inttime < 2001) {
            logger.info("不存在{}的数据表", inttime);
            return 0;
        }
        String dateBaseName = null;
        if (inttime >= 2001 && inttime <= 2005) {
            dateBaseName = "history_day_sensor_data_2001_2005";
        } else if (inttime >= 2006 && inttime <= 2010) {
            dateBaseName = "history_day_sensor_data_2006_2010";
        } else if (inttime >= 2011 && inttime <= 2015) {
            dateBaseName = "history_day_sensor_data_2011_2015";
        } else if (inttime >= 2016 && inttime <= 2020) {
            dateBaseName = "history_day_sensor_data_2016_2020";
        } else {
            dateBaseName = "history_day_sensor_data_" + time;
            dayDataMapper.buildDayBase(dateBaseName);
        }
        return dayDataMapper.addDayData(dateBaseName, dayVo);

    }

    @Override
    public int addHourData(List<HourVo> HourVo) {
        lock.lock();
        try {
            List<ConfigSensorSectionModule> stationList = dayDataMapper.getStation();
            for (HourVo hour : HourVo) {
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
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            lock.unlock();
        }
        /**
         * 分年份入库
         * */
        String time = DateTransform.Date2String(HourVo.get(0).getTime(),"YYYY-MM-dd HH:mm:ss").substring(0, 4);
        Integer inttime = Integer.valueOf(time);
        String dateBaseName = "history_hour_sensor_data_" + time;
        if (inttime < 2001) {
            logger.info("不存在{}的数据表", inttime);
            return 0;
        } else if (inttime >= 2001 && inttime <= 2013) {
            dateBaseName = "history_hour_sensor_data_2001_2013";
            return hourDataMapper.addHourData(dateBaseName, HourVo);
        }
        hourDataMapper.buildHourBase(dateBaseName);
        return hourDataMapper.addHourData(dateBaseName, HourVo);
    }
}
