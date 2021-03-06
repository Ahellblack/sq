package com.siti.wisdomhydrologic.operation.task;

import com.siti.wisdomhydrologic.operation.service.Impl.ManageDataMantainServiceImpl;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zyw on 2019/8/12.
 */
@Component
public class ManageDataMantainTask {

    @Resource
    private ManageDataMantainServiceImpl reportManageDataMantainService;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public int insertAbnormal() throws Exception {
        Date today = new Date();
        String date = getCloseDate("YYYY-MM-dd HH:mm:ss", today, 5);
        int i = reportManageDataMantainService.insertAbnormalData();
        //if (i > 0) System.out.println("在 " + date + " 时插入5分钟内的异常数据至表二" + i + "条");
        return i;
    }

    /**
     * 取当前日期的年月日
     *
     * @return
     * @throws ParseException
     */
    public static Date getMinDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = sdf.parse(sdf.format(date));
        return newDate;
    }

    /**
     * 获取最近的整5分时间点real表数据
     *
     * @Param dateFormat dateFormat的格式 如 YYYY-MM-dd
     * @Param date 当前时间
     * @Param min 相隔时间
     */
    public static String getCloseDate(String dateFormat, Date date, long min) throws Exception {
        long dateTime = date.getTime();
        long needTime = 0;
        if (min >= 8 * 60) {
            return new SimpleDateFormat(dateFormat).format(getMinDate(date));
        } else {
            needTime = dateTime - dateTime % (min * 60L * 1000L);
        }
        return new SimpleDateFormat(dateFormat).format(new Date(needTime));
    }

}
