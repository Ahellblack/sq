package com.siti.wisdomhydrologic.operation.task;

import com.siti.wisdomhydrologic.operation.service.Impl.StationRainConstrastServiceImpl;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zyw on 2019/8/26.
 */
@Component
public class StationRainConstrastTask {

    @Resource
    private StationRainConstrastServiceImpl stationRainConstrastService;

    /**
     * 定时每天13点执行,获取昨日数据
     * */
    @Scheduled(cron = "0 0 13 1/1 * ? ")
    public void insertData() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //cal.add(Calendar.DAY_OF_MONTH,0);
        String yesterday = DateTransform.Date2String(cal.getTime(),"yyyy-MM-dd");
        stationRainConstrastService.insertOrUpdateData(yesterday);
    }
}
