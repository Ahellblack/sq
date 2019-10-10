package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.serviceImpl.StationDataServiceImpl;
import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigSensorSectionModuleMapper;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import com.siti.wisdomhydrologic.util.DateTransform;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private UserMapper userMapper;
    @Resource
    private RedisBiz redisBiz;
    @Resource
    private StationDataMapper stationDataMapper;

    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;

    @Resource
    private RealStationDataMapper realStationDataMapper;

    @Resource
    private StationDataServiceImpl stationDataService;
    @Resource
    private ConfigSensorSectionModuleMapper configSensorSectionModuleMapper;


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
        RealStationData returndata = realStationDataMapper.getData(stationCode);
        List<ConfigSensorSectionModule> station = configSensorSectionModuleMapper.getStation();
        List<String> list = new ArrayList<>();
        station.forEach(data -> {
            list.add(data.getSectionCode() + "");
        });

        return returndata;
    }

    @ApiOperation(value = "首页地图方大级别后显示全部数据接口", httpMethod = "GET", notes = "查询station_data表获取最近各站点的传感器数据,返回各类传感器数据值")
    @ApiParam(name = "stationCode", value = "测站id(5位)")
    @GetMapping("/getRealDataAll")
    public RealStationData getRealListAll() throws Exception {
        return realStationDataMapper.getAllData();
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
    @ApiOperation(value = "首页地图站点地址经纬度展示接口", httpMethod = "GET", notes = "返回各类站点的地图坐标,根据不同测站状态及测站等级进行筛选" + "测站状态 0位离线； 1为正常；2为故障" + "站点类型：0 基本站；1国家站；2一般站 " + "南北片：42 北片 ； 43 南片")
    @ApiParam(name = "level", value = "测站级别")
    @GetMapping("/getLocation")
    public List<ConfigRiverStationVo> getList(Integer level, Integer status, Integer snId, HttpSession session) throws Exception {

        User user = (User) redisBiz.get(session.getId());
        List<Org> orgList = userMapper.findOrg(user.getId());
        orgList.get(0).getPath();

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        String realtime = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        calendar.setTime(DateTransform.String2Date(realtime, "yyyy-MM-dd HH:mm:ss"));
        calendar.add(calendar.MINUTE, -10);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(today);
        realtime = DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("level:" + level + ";status:" + status + ";realtime:" + realtime);
        List<ConfigRiverStationVo> stationLocation = stationDataMapper.getStationLocation(level, status, realtime, snId,1002 );//暂展示浦东点位

        List<ConfigSensorSectionModule> station = configSensorSectionModuleMapper.getStation();
        List<String> list = new ArrayList<>();
        station.forEach(data -> {
            list.add(data.getSectionCode() + "");
        });
        stationLocation.forEach(data -> {
            if (!list.contains(data.getStationId() + ConstantConfig.ES)) {
                data.setElectric("");
            }
            if (!list.contains(data.getStationId() + (ConstantConfig.WFVY))) {
                data.setFlowVelocityY("");
            }
            if (!list.contains(data.getStationId() + (ConstantConfig.WFV))) {
                data.setFlowVelocityX("");
            }
            if (!list.contains(data.getStationId() + (ConstantConfig.TS))) {
                data.setTideLevel("");
            }
            if (!list.contains(data.getStationId() + (ConstantConfig.RS))) {
                //Double value = (data.getRealVal() - Double.parseDouble(realStationVo.getRainfall()));
                data.setRainfall("");
            }
            if (!list.contains(data.getStationId() + (ConstantConfig.WS))) {
                data.setWaterLevel("");
            }
            if (!list.contains(data.getStationId() + (ConstantConfig.WSS))) {
                data.setWindSpeed("");
            }
            //设置字段WindDirectionName
            if (!list.contains(data.getStationId() + (ConstantConfig.WDS))) {
                data.setWindDirection("");
            } else {
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
            if (!list.contains(data.getStationId() + (ConstantConfig.WAT))) {
                data.setAirTemperature("");
            }
            if (!list.contains(data.getStationId() + (ConstantConfig.WAP))) {
                data.setAirPressure("");
            }
        });
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
