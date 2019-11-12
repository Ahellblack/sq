package com.siti.wisdomhydrologic.operation.task;

import com.siti.wisdomhydrologic.operation.service.Impl.ManageMantainServiceImpl;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.DatesUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zyw on 2019/9/9.
 */
@Component
public class ManageMantainTask {

    @Resource
    ManageMantainServiceImpl manageMantainService;

    /**
     * 定时每月初执行
     * 获取上个月的数据
     */
    @Scheduled(cron = "0 0 0 1 1/1 ?")
    public void insertData() throws Exception {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        manageMantainService.insertOrUpdate(DateTransform.Date2String(cal.getTime(), "yyyy-MM"));
    }

}
