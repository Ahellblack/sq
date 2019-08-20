package com.siti.wisdomhydrologic.mainpage.task;

import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import java.util.*;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/8/20.
 */
public class RealStationDataTask {

    @Resource
    private StationDataServiceImpl stationDataService;
    @Resource
    private StationDataMapper stationDataMapper;

    @Scheduled(cron = "0 1/5 * * * ? ")
    public void insertAbnormal() throws Exception {
        List<Integer> stationId = stationDataMapper.getStationId();
        System.out.println("real station data insert...");
        stationId.forEach(id -> {
            try {
                stationDataService.insertData(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
