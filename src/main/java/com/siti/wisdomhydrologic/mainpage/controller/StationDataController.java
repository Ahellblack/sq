package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.realmessageprocess.entity.Real;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("/getRealData")
    public RealStationVo getList(Integer stationCode) throws Exception {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        String realtime = null;
        /**
         * 由于real表
         * */
        if ((calendar.get(calendar.MINUTE) % 5) > 4) {
            realtime = getCloseDate("YYYY-MM-dd HH:mm:ss", today, 5);
        } else {
            realtime = getCloseDate("YYYY-MM-dd HH:mm:ss", today, 10);
        }
        System.out.println((calendar.get(calendar.MINUTE) % 5)+"___"+realtime);
        List<RealStationVo> stationData = stationDataMapper.getStationData(stationCode, realtime);
        RealStationVo realStationVo = new RealStationVo();
        stationData.forEach(data -> {
            realStationVo.setStationId(data.getStationId());
            realStationVo.setStationName(data.getStationName());
            realStationVo.setTime(data.getTime());
            String sensorTypeId = String.valueOf(data.getSensorCode() % 100);
            if (sensorTypeId.equals(ConstantConfig.ES)) {
                realStationVo.setRealDataElectric(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.WFVY)) {
                realStationVo.setRealDataFlowY(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.WFV)) {
                realStationVo.setRealDataFlowX(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.TS)) {
                realStationVo.setRealDataTideLevel(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.RS)) {
                realStationVo.setRealDataRainFall(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.WS)) {
                realStationVo.setRealDataWaterLevel(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.WSS)) {
                realStationVo.setRealDataWindSpeed(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.WDS)) {
                realStationVo.setRealDataWindDirection(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.WAT)) {
                realStationVo.setRealDataAirTemperature(data.getRealVal());
            }
            if (sensorTypeId.equals(ConstantConfig.WAP)) {
                realStationVo.setRealDataAirPressure(data.getRealVal());
            }
        });

        return realStationVo;
    }

    @RequestMapping("/getLocation")
    public List<ConfigRiverStation> getList() {
        return stationDataMapper.getStationLocation();
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
