package com.siti.wisdomhydrologic.mainpage.service.serviceImpl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.StationDataService;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
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
        //待开发可添加根据riverStation的id先生成添加数据
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        /**
         * 查询上一个整5分 如在 00：43分时 取到 00:40分
         * */
        String realtime = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        calendar.setTime(DateTransform.String2Date(realtime, "yyyy-MM-dd HH:mm:ss"));
        calendar.add(calendar.MINUTE, -10);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(today);
        calendar2.set(Calendar.HOUR_OF_DAY, 9);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);

        realtime = DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        /**
         * 查询上一个整5分再往前5分钟的real表数据
         * */
        List<AbnormalDetailEntity> abnormallist = abnormalDetailMapper.getAbnormal(realtime);

        List<RealStationVo> stationData = stationDataMapper.getStationData();
        //RealStationData entity = realStationDataMapper.getData(stationCode);
        List<Integer> stationList = new ArrayList<>();

        abnormallist.forEach(data -> {
            stationList.add(data.getSensorCode() / 100);
        });
        List<ConfigSensorSectionModule> station = configSensorSectionModuleMapper.getStation();
        List<Integer> list = new ArrayList<>();
        station.forEach(data -> {
            list.add(data.getSectionCode());
        });
        String finalRealtime = realtime;
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
                String endTime = finalRealtime;
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateTransform.String2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
                if (finalRealtime.substring(11, 19) == "08:10:00") {
                    cal.add(Calendar.HOUR, -24);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(data.getStationCode() + "89", startTime, endTime);
                    //通畅率变化
                    realStationVo.setPatencyRate(((LastDayRealList.size() * 100) / 188f));
                    realStationDataMapper.updateStationPatency(realStationVo);
                } else if (finalRealtime.substring(11, 19) == "12:10:00") {
                    cal.add(Calendar.HOUR, -4);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(data.getStationCode() + "89", startTime, endTime);
                    //通畅率变化
                    realStationVo.setPatencyRate(((LastDayRealList.size() * 100) / 48f));
                    realStationDataMapper.updateStationPatency(realStationVo);
                } else if (finalRealtime.substring(11, 19) == "15:10:00") {
                    cal.add(Calendar.HOUR, -3);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(data.getStationCode() + "89", startTime, endTime);
                    //通畅率变化
                    realStationVo.setPatencyRate(((LastDayRealList.size() * 100) / 36f));
                    realStationDataMapper.updateStationPatency(realStationVo);
                }
                // 数据更新
                realStationDataMapper.updateStationData(realStationVo);
            } catch (NullPointerException e) {
            }
        });
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
