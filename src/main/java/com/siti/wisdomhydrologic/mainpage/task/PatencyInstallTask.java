package com.siti.wisdomhydrologic.mainpage.task;

import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DatesUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dell on 2019/12/30.
 */
@Component
public class PatencyInstallTask {

    private static final Integer DELAY_MINUTE = 30;

    @Resource
    private StationDataServiceImpl stationDataService;
    @Resource
    private StationDataMapper stationDataMapper;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void PatencyInstall() throws Exception {
        Date today = new Date();
        String endtime = DatesUtils.getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        String time = endtime.substring(11, 19);
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateOrTimeTrans.String2DateTime(endtime));
        cal.add(Calendar.MINUTE,-DELAY_MINUTE);
        endtime = DateOrTimeTrans.Date2TimeString2(cal.getTime());
        if (time.equals("12:10:00")) {//
            stationDataService.patencyRateInstall(endtime, 4,48f);
        }else if(time.equals("08:10:00")){
            stationDataService.patencyRateInstall(endtime, 24,288f);
        }else if(time.equals("15:10:00")){
            stationDataService.patencyRateInstall(endtime, 3,36f);
        }
    }
}
