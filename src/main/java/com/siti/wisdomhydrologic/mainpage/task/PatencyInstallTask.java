package com.siti.wisdomhydrologic.mainpage.task;

import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import com.siti.wisdomhydrologic.util.DatesUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by dell on 2019/12/30.
 */
@Component
public class PatencyInstallTask {

    @Resource
    private StationDataServiceImpl stationDataService;
    @Resource
    private StationDataMapper stationDataMapper;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void PatencyInstall() throws Exception {
        String endtime = DatesUtils.getCloseDate("YYYY-MM-dd HH:mm:ss", new Date(), 5);
        String time = endtime.substring(11, 19);
        if (time.equals("12:10:00")) {
            stationDataService.patencyRateInstall(endtime, 4,48f);
        }else if(time.equals("08:10:00")){
            stationDataService.patencyRateInstall(endtime, 24,288f);
        }else if(time.equals("15:10:00")){
            stationDataService.patencyRateInstall(endtime, 3,36f);
        }
    }
}
