package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
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

    @RequestMapping("/getRealData")
    public RealStationVo getRealList(Integer stationCode) throws Exception {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        String realtime = null;
        /**
         * 由于real表
         * */
        if ((calendar.get(calendar.MINUTE) % 5) >= 4) {
            realtime = getCloseDate("YYYY-MM-dd HH:mm:ss", today, 5);
        } else {
            realtime = getCloseDate("YYYY-MM-dd HH:mm:ss", today, 10);
        }
        System.out.println((calendar.get(calendar.MINUTE) % 5)+"___"+realtime);
        List<RealStationVo> stationData = stationDataMapper.getStationData(stationCode, realtime);
        List<ReportManageDataMantainVo> abnormallist = abnormalDetailMapper.getALL(realtime);
        List<Integer> stationList = new ArrayList<>();
        abnormallist.forEach(data->{
            stationList.add(data.getSensorCode()/100);
        });
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
            if(stationList.contains(realStationVo.getStationId())){
                //测站状态 1为故障,2为正常
                data.setStatus(1);
            }else{
                data.setStatus(2);
            }
        });


        return realStationVo;
    }

    /**
     * @Param level 站点级别
     * @Param status 站点状态
     * */
    @RequestMapping("/getLocation")
    public List<ConfigRiverStation> getList(Integer level,Integer status) throws Exception {
        Date today = new Date();
        String realtime = getCloseDate("YYYY-MM-dd HH:mm:ss", today, 5);
        List<ReportManageDataMantainVo> abnormallist = abnormalDetailMapper.getALL(realtime);
        List<Integer> stationList = new ArrayList<>();
        List<ConfigRiverStation> returnList= new ArrayList<>();
        abnormallist.forEach(data->{
            stationList.add(data.getSensorCode()/100);
        });
        System.out.println(stationList.get(0));
        List<ConfigRiverStation> stationLocation = stationDataMapper.getStationLocation(level);
        stationLocation.forEach(data->{
            if(stationList.contains(data.getStationId())){
                //测站状态 1为故障,2为正常
                data.setStatus(1);
            }else{
                data.setStatus(2);
            }
            if(status != null && status == data.getStatus()){
                returnList.add(data);
            }
        });
        return returnList;
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
