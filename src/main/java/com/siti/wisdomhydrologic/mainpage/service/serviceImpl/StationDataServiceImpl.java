package com.siti.wisdomhydrologic.mainpage.service.serviceImpl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.configmaintain.entity.ModuleAndStation;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.StationDataService;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/20.
 */
@Service
public class StationDataServiceImpl implements StationDataService {

    @Resource
    private StationDataMapper stationDataMapper;

    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;

    @Resource
    private RealStationDataMapper realStationDataMapper;

    @Resource
    private ConfigRiverStationMapper configRiverStationMapper;

    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;


    @Override
    public void updateData() throws Exception {
        //待开发可添加根据riverStation的id生成添加数据
        Calendar calendar = Calendar.getInstance();
        //数据最新时间
        String realtime = realStationDataMapper.getStationLatestData();
        List<RealStationData> updateList = new ArrayList<>();
        calendar.setTime(DateTransform.String2Date(realtime, "yyyy-MM-dd HH:mm:ss"));
        /**
         * 查询上一个整5分再往前5分钟的real表数据
         * */
        List<AbnormalDetailEntity> abnormallist = abnormalDetailMapper.getCurrentAbnormal(realtime);

        List<RealStationVo> stationData = stationDataMapper.getStationData();

        /**
         * 如果riverStation多一条数据，自动生成,每日检查一次
         */
        if(realtime.equals(realtime.substring(0,10)+" 00:00:00")){
            List<ConfigRiverStation> stationAll = configRiverStationMapper.getAllstationPatency();
            stationDataMapper.replaceData(stationAll);
        }

        List<Integer> stationList = new ArrayList<>();

        abnormallist.forEach(data -> {
            stationList.add(data.getSensorCode() / 100);
        });
        List<ConfigSensorSectionModule> station = configSensorSectionModuleMapper.getStation();
        List<Integer> list = new ArrayList<>();
        station.forEach(data -> {
            list.add(data.getSectionCode());
        });

        stationData.forEach((RealStationVo data) -> {
            try {
                RealStationData realStationVo = realStationDataMapper.getData(Integer.parseInt(data.getStationCode()));
                //RealStationVo realStationVo = new RealStationVo();
                realStationVo.setStationId(Integer.parseInt(data.getStationCode()));
                realStationVo.setStationName(data.getStationName());
                realStationVo.setTime(data.getTime());
                String sensorTypeId = String.valueOf(data.getSensorCode() % 100);
                //if (list.contains(data.getSensorCode())) {
                if (sensorTypeId.equals(ConstantConfig.ES)) {
                    realStationVo.setElectric(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.WFVY)) {
                    realStationVo.setFlowVelocityY(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.WFV)) {
                    realStationVo.setFlowVelocityX(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.TS)) {
                    realStationVo.setTideLevel(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.RS)) {
                    try {
                        //雨量取上次数据与这次数据的差值
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(DateTransform.String2Date(data.getTime(), "yyyy-MM-dd HH:mm:ss"));
                        cal.add(Calendar.MINUTE, -5);
                        String lasttime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                        List<RealStationVo> latest5minData = stationDataMapper.getLatest5minData(data.getSensorCode() + "", lasttime);
                        if (latest5minData.size() > 0) {
                            double oldRain = latest5minData.get(0).getRealVal();
                            double realRain = data.getRealVal();
                            Double value = (realRain - oldRain);
                            realStationVo.setRainfall(value + "");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("实时雨量更新报错");
                    }
                }
                if (sensorTypeId.equals(ConstantConfig.WS)) {
                    realStationVo.setWaterLevel(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.WSS)) {
                    realStationVo.setWindSpeed(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.WDS)) {
                    realStationVo.setWindDirection(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.WAT)) {
                    realStationVo.setAirTemperature(data.getRealVal() + "");
                }
                if (sensorTypeId.equals(ConstantConfig.WAP)) {
                    realStationVo.setAirPressure(data.getRealVal() + "");
                }
                if (stationList.contains(realStationVo.getStationId())) {
                    //测站状态 1为正常,2为故障
                    realStationVo.setStatus(2);
                } else {
                    realStationVo.setStatus(1);
                }
                /**
                 * 分别在 12:10:00 08:10:00 15:10:00修改畅通率数据
                 * */
                String endTime = realtime;
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateTransform.String2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
                if ("08:10:00".equals(realtime.substring(11, 19))) {
                    cal.add(Calendar.HOUR, -12);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(data.getStationCode() + "89", startTime, endTime);
                    //通畅率变化
                    realStationVo.setPatencyRate(((LastDayRealList.size() * 100) / 144f));
                    realStationDataMapper.updateStationPatency(realStationVo);
                }
                if ("12:10:00".equals(realtime.substring(11, 19))) {
                    cal.add(Calendar.HOUR, -4);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(data.getStationCode() + "89", startTime, endTime);
                    //通畅率变化
                    realStationVo.setPatencyRate(((LastDayRealList.size() * 100) / 48f));
                    realStationDataMapper.updateStationPatency(realStationVo);
                }
                if ("15:10:00".equals(realtime.substring(11, 19))) {
                    cal.add(Calendar.HOUR, -3);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(data.getStationCode() + "89", startTime, endTime);
                    //通畅率变化
                    realStationVo.setPatencyRate(((LastDayRealList.size() * 100) / 36f));
                    realStationDataMapper.updateStationPatency(realStationVo);
                }
                // 数据更新添加
                updateList.add(realStationVo);

            } catch (NullPointerException e) {
                System.out.println("更新異常");
            }
        });

        realStationDataMapper.updateStationData(updateList);
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

    public static void main(String[] args) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MINUTE, -5);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(today);
        calendar2.set(Calendar.HOUR_OF_DAY, 9);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        Date date9 = calendar2.getTime();

        System.out.println(DateTransform.Date2String(date9, "yyyy-MM-dd HH:mm:ss").substring(11, 19));
    }


}
