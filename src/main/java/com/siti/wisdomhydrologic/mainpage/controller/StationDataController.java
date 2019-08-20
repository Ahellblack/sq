package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.Real;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/8/15.
 */
@RestController
@RequestMapping("/station")
public class StationDataController {

    @Resource
    private StationDataMapper stationDataMapper;

    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;

    @Resource
    private RealStationDataMapper realStationDataMapper;
    @Resource
    private StationDataServiceImpl stationDataService;


    @RequestMapping("/getRealData")
    public RealStationData getRealList(Integer stationCode) throws Exception {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        String realtime = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        calendar.setTime(DateTransform.String2Date(realtime, "yyyy-MM-dd HH:mm:ss"));
        calendar.add(calendar.MINUTE, -10);
        realtime = DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(realtime);
        return realStationDataMapper.getData(realtime,stationCode);
    }

    @RequestMapping("/updateData")
    public int InsertRealData() {
        List<Integer> stationId = stationDataMapper.getStationId();
        stationId.forEach(id -> {
            try {
                stationDataService.updateData(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return 1;
    }
    /**
     * @Param level 站点级别
     * @Param status 站点状态
     */
    @RequestMapping("/getLocation")
    public List<ConfigRiverStationVo> getList(@Param("level") Integer level, @Param("status") Integer status) throws Exception {

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        String realtime = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        calendar.setTime(DateTransform.String2Date(realtime, "yyyy-MM-dd HH:mm:ss"));
        calendar.add(calendar.MINUTE, -10);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(today);
        realtime = DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("level:"+level+";status:"+status+";realtime:"+realtime);
        List<ConfigRiverStationVo> stationLocation = stationDataMapper.getStationLocation(level,status,realtime);

        return stationLocation;
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
     * @Param dateFormat dateFormat的格式 如 yyyy-MM-dd
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
