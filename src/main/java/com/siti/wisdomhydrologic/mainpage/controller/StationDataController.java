package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateTransform;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/15.
 */
@RestController
@RequestMapping("/station")
@Api(value = "首页测站实时数据controller", tags = {"首页测站实时数据"})
public class StationDataController {

    @Resource
    private StationDataMapper stationDataMapper;

    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;

    @Resource
    private RealStationDataMapper realStationDataMapper;

    @Resource
    private StationDataServiceImpl stationDataService;


    @ApiOperation(value = "首页地图站点点击数据展示接口", httpMethod = "GET", notes = "查询station_data表获取最近各站点的传感器数据,返回各类传感器数据值")
    @ApiParam(name = "stationCode", value = "测站id(5位)")
    @GetMapping("/getRealData")
    public RealStationData getRealList(Integer stationCode) throws Exception {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        String realtime = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        calendar.setTime(DateTransform.String2Date(realtime, "yyyy-MM-dd HH:mm:ss"));
        calendar.add(calendar.MINUTE, -10);
        realtime = DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(realtime);
        return realStationDataMapper.getData(stationCode);
    }

    @ApiOperation(value = "测站实时状况表更新接口", httpMethod = "GET", notes = "测站实时状况表更新接口")
    @GetMapping("/updateData")
    public int InsertRealData() throws Exception {
        /*List<Integer> stationId = stationDataMapper.getStationId();
        stationId.forEach(id -> {
            try {*/
        stationDataService.updateData();
           /* } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
        return 1;
    }

    /**
     * @Param level 站点级别
     * @Param status 站点状态
     */
    @ApiOperation(value = "首页地图站点地址经纬度展示接口", httpMethod = "GET", notes = "返回各类站点的地图坐标,根据不同测站状态及测站等级进行筛选" +
            "测站状态 0位离线； 1为正常；2为故障" +
            "站点类型：0 基本站；1国家站；2一般站")
    @ApiParam(name = "level", value = "测站级别")
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
        System.out.println("level:" + level + ";status:" + status + ";realtime:" + realtime);
        List<ConfigRiverStationVo> stationLocation = stationDataMapper.getStationLocation(level, status, realtime);

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
