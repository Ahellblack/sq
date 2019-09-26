package com.siti.wisdomhydrologic.operation.task;

import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageApplicationBrokenServiceImpl;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dell on 2019/8/12.
 */
@Component
public class ManageApplicationBrokenTask {

    @Resource
    private ManageApplicationBrokenServiceImpl manageApplicationBrokenService;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;
    @Resource
    private ManageApplicationBrokenMapper reportManageApplicationBrokenMapper;

    @Scheduled(cron = "0 4/5 * * * ? ")
    public int insertAbnormal() throws Exception {
        Date today = new Date();
        String date = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(DateTransform.String2Date(date, "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
        }
        /**
         * 查询上一个整5分再往前5分钟数据
         * */
        cal.add(cal.MINUTE, -5);
        date = DateTransform.Date2String(cal.getTime(),"yyyy-MM-dd HH:mm:ss");

        int i = manageApplicationBrokenService.insertDataMantain(date);
        if(i>0) System.out.println("在 " + date + " 时插入5分钟内的异常数据至表四" + i + "条");
        //异常状态恢复方法
        manageApplicationBrokenService.updateBrokenStatus();
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
