package com.siti.wisdomhydrologic.mainpage.service.serviceImpl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.StationDataMapper;
import com.siti.wisdomhydrologic.mainpage.service.StationDataService;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    @Override
    public void updateData(Integer stationCode) throws Exception {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        /**
         * 查询上一个整5分
         * */
        String realtime = getCloseDate("yyyy-MM-dd HH:mm:ss", today, 5);
        calendar.setTime(DateTransform.String2Date(realtime, "yyyy-MM-dd HH:mm:ss"));
        /**
         * 查询上一个整5分再往前5分钟的real表数据
         * */
        calendar.add(calendar.MINUTE, -5);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(today);
        realtime = DateTransform.Date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        List<RealStationVo> stationData = stationDataMapper.getStationData(stationCode, realtime);
        List<ReportManageDataMantainVo> abnormallist = abnormalDetailMapper.getALL(realtime);
        List<Integer> stationList = new ArrayList<>();
        abnormallist.forEach(data -> {
            stationList.add(data.getSensorCode() / 100);
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
            if (stationList.contains(realStationVo.getStationId())) {
                //测站状态 1为故障,2为正常
                realStationVo.setStatus(1);
            } else {
                realStationVo.setStatus(2);
            }
        });
        if (realStationVo.getTime() != null) {
            realStationDataMapper.updateStationData(realStationVo);
        }
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
