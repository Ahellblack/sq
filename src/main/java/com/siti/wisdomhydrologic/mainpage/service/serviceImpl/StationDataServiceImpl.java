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
import java.util.*;

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
         * 对应时间的故障数据
         * */
        List<AbnormalDetailEntity> abnormallist = abnormalDetailMapper.getCurrentAbnormal(realtime);

        List<RealStationVo> stationData = stationDataMapper.getStationData();

        /**
         * 如果riverStation多一条数据，自动生成,每日检查一次
         */
        if (realtime.equals(realtime.substring(0, 10) + " 00:00:00")) {
            List<ConfigRiverStation> stationAll = configRiverStationMapper.getAllstationPatency();
            stationDataMapper.replaceData(stationAll);
        }
        List<Integer> stationList = new ArrayList<>();
        abnormallist.forEach(data -> stationList.add(data.getSensorCode() / 100));
        List<Integer> list = new ArrayList<>();
        configSensorSectionModuleMapper.getStation(null).forEach(data -> list.add(data.getSectionCode()));

        Map<String, List<RealStationVo>> stationModuleMap = new HashMap<>();
        stationData.forEach((RealStationVo data) -> {
            if (stationModuleMap.get(data.getStationCode()) == null) {
                List newList = new ArrayList();
                newList.add(data);
                stationModuleMap.put(data.getStationCode(), newList);
            } else {
                List<RealStationVo> modules = stationModuleMap.get(data.getStationCode());
                modules.add(data);
                stationModuleMap.put(data.getStationCode(), modules);
            }
        });

        stationModuleMap.forEach((station, modules) -> {
            try {
                RealStationData realDTO = realStationDataMapper.getData(Integer.parseInt(station));
                modules.forEach(data -> {
                    realDTO.setStationId(Integer.parseInt(data.getStationCode()));
                    realDTO.setStationName(data.getStationName());
                    realDTO.setTime(data.getTime());
                    String sensorTypeId = String.valueOf(data.getSensorCode() % 100);
                    String value = data.getRealVal() + "";
                    if (sensorTypeId.equals(ConstantConfig.ES)) {
                        realDTO.setElectric(value);
                    }
                    if (sensorTypeId.equals(ConstantConfig.WFVY)) {
                        realDTO.setFlowVelocityY(value);
                    }
                    if (sensorTypeId.equals(ConstantConfig.WFV)) {
                        realDTO.setFlowVelocityX(value);
                    }
                    if (sensorTypeId.equals(ConstantConfig.TS)) {
                        realDTO.setTideLevel(value);
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
                                Double rainValue = (realRain - oldRain);
                                realDTO.setRainfall(rainValue + "");
                            }
                        } catch (NullPointerException e) {
                            System.out.println("实时雨量更新报错");
                        }
                    }
                    if (sensorTypeId.equals(ConstantConfig.WS)) {
                        realDTO.setWaterLevel(value);
                    }
                    if (sensorTypeId.equals(ConstantConfig.WSS)) {
                        realDTO.setWindSpeed(value);
                    }
                    if (sensorTypeId.equals(ConstantConfig.WDS)) {
                        realDTO.setWindDirection(value);
                    }
                    if (sensorTypeId.equals(ConstantConfig.WAT)) {
                        realDTO.setAirTemperature(value);
                    }
                    if (sensorTypeId.equals(ConstantConfig.WAP)) {
                        realDTO.setAirPressure(value);
                    }

                });
                if (stationList.contains(realDTO.getStationId())) {
                    //测站状态 1为正常,2为故障
                    realDTO.setStatus(2);
                } else {
                    realDTO.setStatus(1);
                }
                /**
                 * 分别在 12:10:00 08:10:00 15:10:00修改畅通率数据
                 * */
                String endTime = realtime;
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateTransform.String2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
                if ("08:10:00".equals(realtime.substring(11, 19))) {/*"08:10:00".equals(realtime.substring(11, 19))*/
                    cal.add(Calendar.HOUR, -12);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(station + "89", startTime, endTime);
                    //通畅率变化
                    realDTO.setPatencyRate(((LastDayRealList.size() * 100) / 144f));
                    realStationDataMapper.updateStationPatency(realDTO);
                }
                if ("12:10:00".equals(realtime.substring(11, 19))) {
                    cal.add(Calendar.HOUR, -4);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(station + "89", startTime, endTime);
                    //通畅率变化
                    realDTO.setPatencyRate(((LastDayRealList.size() * 100) / 48f));
                    realStationDataMapper.updateStationPatency(realDTO);
                }
                if ("15:10:00".equals(realtime.substring(11, 19))) {
                    cal.add(Calendar.HOUR, -3);
                    String startTime = DateTransform.Date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
                    List<RealVo> LastDayRealList = realStationDataMapper.getLastDayList(station + "89", startTime, endTime);
                    //通畅率变化
                    realDTO.setPatencyRate(((LastDayRealList.size() * 100) / 36f));
                    realStationDataMapper.updateStationPatency(realDTO);
                }
                // 数据更新添加
                updateList.add(realDTO);

            } catch (Exception e) {
                System.out.println("更新出现异常");
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
