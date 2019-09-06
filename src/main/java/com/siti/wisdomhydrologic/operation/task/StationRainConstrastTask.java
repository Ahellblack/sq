package com.siti.wisdomhydrologic.operation.task;

import com.siti.wisdomhydrologic.operation.service.Impl.StationRainConstrastServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/8/26.
 */
@Component
public class StationRainConstrastTask {

    @Resource
    private StationRainConstrastServiceImpl stationRainConstrastService;

    /**
     * 定时每天13点执行
     * */
    @Scheduled(cron = "0 0 13 1/1 * ? ")
    public void insertData() throws Exception {
        stationRainConstrastService.insertOrUpdateData();
    }
}
