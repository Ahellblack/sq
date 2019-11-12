package com.siti.wisdomhydrologic.mainpage.task;

import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zyw on 2019/8/20.
 */
@Component
public class RealStationDataTask {

    @Resource
    private StationDataServiceImpl stationDataService;
    @Resource
    private StationDataMapper stationDataMapper;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void insertAbnormal() throws Exception {

        stationDataService.updateData();
    }
}
