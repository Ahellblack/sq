package com.siti.wisdomhydrologic.mainpage.service.serviceImpl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigRiverStation;
import com.siti.wisdomhydrologic.mainpage.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.StationDataService;
import com.siti.wisdomhydrologic.mainpage.vo.AbnormalDetailVo;
import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigRiverStationMapper;
import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.mainpage.vo.RealVo;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.operation.vo.RealDeviceStatus;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zyw on 2019/8/20.
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
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;
    //现展示浦东数据
    private final Integer SYSORG = 1002;

    @Override
    public void updateData() throws Exception {
        //待开发可添加根据riverStation的id生成添加数据
        //数据最新时间
        String realtime = realStationDataMapper.getStationLatestData();
        List<RealStationData> updateList = new ArrayList<>();
        /**
         * 对应未恢复故障数据
         * */
        List<AbnormalDetailEntity> abnormallist = abnormalDetailMapper.getCurrentAbnormal();

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


    public List<ConfigRiverStationVo> getLocationStation(Integer level, Integer status, Integer snId, Integer stationId) throws Exception {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        orgList.get(0).getPath();
        List<ConfigRiverStationVo> stationLocation = stationDataMapper.getStationLocation(level, status, snId, SYSORG, stationId);//暂展示浦东点位
        try {
            stationLocation.forEach(data -> {
                //设置字段WindDirectionName
                if (data.getWindDirection() != "" && data.getWindDirection() != null) {
                    Double dirction = Double.parseDouble(data.getWindDirection());
                    if (dirction >= 348.76 || dirction <= 11.25) {
                        data.setWindDirectionName("北");
                    }
                    if (dirction >= 11.26 || dirction <= 33.75) {
                        data.setWindDirectionName("北东北");
                    }
                    if (dirction >= 33.76 || dirction <= 56.25) {
                        data.setWindDirectionName("东北");
                    }
                    if (dirction >= 56.76 || dirction <= 78.25) {
                        data.setWindDirectionName("东东北");
                    }
                    if (dirction >= 78.76 || dirction <= 101.25) {
                        data.setWindDirectionName("东");
                    }
                    if (dirction >= 101.26 || dirction <= 123.75) {
                        data.setWindDirectionName("东东南");
                    }
                    if (dirction >= 123.76 || dirction <= 146.75) {
                        data.setWindDirectionName("东南");
                    }
                    if (dirction >= 146.76 || dirction <= 168.75) {
                        data.setWindDirectionName("南东南");
                    }
                    if (dirction >= 168.76 || dirction <= 191.75) {
                        data.setWindDirectionName("南");
                    }
                    if (dirction >= 191.76 || dirction <= 213.75) {
                        data.setWindDirectionName("南西南");
                    }
                    if (dirction >= 213.76 || dirction <= 236.75) {
                        data.setWindDirectionName("西南");
                    }
                    if (dirction >= 236.76 || dirction <= 258.25) {
                        data.setWindDirectionName("西西南");
                    }
                    if (dirction >= 258.76 || dirction <= 281.25) {
                        data.setWindDirectionName("西");
                    }
                    if (dirction >= 281.76 || dirction <= 303.25) {
                        data.setWindDirectionName("西西北");
                    }
                    if (dirction >= 303.76 || dirction <= 326.25) {
                        data.setWindDirectionName("西北");
                    }
                    if (dirction >= 326.76 || dirction <= 348.25) {
                        data.setWindDirectionName("北西北");
                    }
                }
                //故障站状态赋值
                if (data.getStatus() == 2) {
                    //查询该测站的异常状态,赋值AbnormalDetailList
                    List<AbnormalDetailVo> stationLatestData = abnormalDetailMapper.getStationLatestData(data.getTime(), data.getStationId());
                    List<String> errorList = new ArrayList<>();
                    List<String> description = new ArrayList<>();
                    stationLatestData.forEach(error -> {
                        errorList.add(error.getErrorName());
                        description.add(error.getDescription());
                    });
                    data.setAbnormalDetailList(errorList);
                    data.setDescriptionList(description);
                }
                //箱门状态展示
                List<RealDeviceStatus> devList = reportStationBrokenMapper.getRealDeviceList(data.getStationId());
                if (devList.size() > 0) {
                    data.setRealDeviceStatusList(devList);
                }
            });
        } catch (Exception e) {
            System.out.println("传感器添加失败");
        }
        return stationLocation;

    }
}
