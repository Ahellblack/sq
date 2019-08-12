package com.siti.wisdomhydrologic.datepull.service.impl;

import com.siti.wisdomhydrologic.datepull.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.datepull.mapper.TSDBMapper;
import com.siti.wisdomhydrologic.datepull.service.TSDBService;
import com.siti.wisdomhydrologic.datepull.vo.TSDBVo;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dell on 2019/7/23.
 */
@Component
public class TSDBServiceImpl implements TSDBService {

    private static final Logger logger = LoggerFactory.getLogger(DayDataServiceImpl.class);

    @Resource
    private DayDataMapper dayDataMapper;
    @Resource
    private TSDBMapper tsdbMapper;

    @Override
    public synchronized int insertTSDB(List<TSDBVo> list) {
        List<ConfigSensorSectionModule> stationList = dayDataMapper.getStation();
        System.out.println("测站信息获取...");
        for (TSDBVo tsdb : list) {
            for (ConfigSensorSectionModule station : stationList) {
                if (tsdb.getSENID() == station.getSectionCode()) {
                    tsdb.setSensorTypeName(station.getSensorName());
                    tsdb.setSensorDataUnit(station.getSectionDataUnit());
                    tsdb.setSensorTypeId(station.getSensorCode());
                    tsdb.setStationId(station.getStationCode());
                    tsdb.setStationName(station.getStationName());
                }
            }
        }
        /**
         * 分年份入库
         * */
        String time = list.get(0).getTime().substring(0, 4);
        Integer inttime = Integer.valueOf(time);
        String dateBaseName = "history_5min_sensor_data_" + time;
        if (inttime < 2001) {
            logger.info("不存在{}的数据表", inttime);
            return 0;
        } else if (inttime >= 2001 && inttime <= 2013) {
            dateBaseName = "history_5min_sensor_data_2001_2013";
            return tsdbMapper.insertTSDB(dateBaseName, list);
        }
        Calendar cal = Calendar.getInstance();
        tsdbMapper.buildDatabase(dateBaseName);
        return tsdbMapper.insertTSDB(dateBaseName, list);

    }
}
