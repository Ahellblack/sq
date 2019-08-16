package com.siti.wisdomhydrologic.operation.utils;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.maintainconfig.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/8/14.
 */
public class AbnormalParamUtils {

    public List<ReportManageDataMantainVo> getAbnormalParam(String date, List<ReportManageDataMantainVo> all, List<ConfigAbnormalDictionary> abnormallist) {


        all.forEach(abnormalData -> {
            int accordingId = 0;
            Integer sensorCode = abnormalData.getSensorCode();
            //赋值测站号和修改日期
            abnormalData.setStationCode(abnormalData.getSensorCode() / 100);
            //添加时节选到某日
            abnormalData.setCreateTime(abnormalData.getDate());
            abnormalData.setAlterDate(abnormalData.getCreateTime().substring(0, 10));
            abnormalData.setErrorTimeSpace(abnormalData.getCreateTime());
            abnormalData.setErrorDataReRun(0);
            if (sensorCode % 100 == 84) {//雨量
                abnormalData.setAlterSensorTypeName("雨量");
                abnormalData.setAlterSensorTypeId(84);
                if (abnormalData.getDayAbove() == 1 || abnormalData.getDayBelow() == 1) {
                    //1天的数据
                    accordingId = 5;
                }
                if (abnormalData.getHourAbove() == 1 || abnormalData.getHourBelow() == 1) {
                    //小时的数据
                    accordingId = 4;
                }
                if (abnormalData.getFiveAbove() == 1 || abnormalData.getFiveBelow() == 1) {
                    //5分钟的数据
                    accordingId = 3;
                }
                if (abnormalData.getLessNear() == 1 || abnormalData.getMoreNear() == 1) {
                    //超出或低于附近传感器平均值10%
                    accordingId = 6;
                }

                for (ConfigAbnormalDictionary e : abnormallist) {
                    if (("data_"+accordingId).equals(e.getBrokenAccordingId())) {
                        abnormalData.setErrorDataType(e.getErrorDataId());
                        abnormalData.setErrorDataReason(e.getErrorName());
                        abnormalData.setBrokenAccordingId("data_"+accordingId);
                    }
                }
            }
            if (sensorCode % 100 == 83) {//水位
                //水位默认为实时数据
                abnormalData.setAlterSensorTypeName("水位");
                abnormalData.setAlterSensorTypeId(83);
                abnormalData.setErrorDataType(1);
                if (abnormalData.getFiveAbove() == 1) {
                    //超过最大阈值
                    accordingId = 7;
                }
                if (abnormalData.getFiveBelow() == 1) {
                    //低于
                    accordingId = 8;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 9;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 10;
                }
            }
            if (sensorCode % 100 == 81) {//潮位
                //潮位默认为实时数据
                abnormalData.setAlterSensorTypeName("潮位");
                abnormalData.setAlterSensorTypeId(81);
                abnormalData.setErrorDataType(1);
                if (abnormalData.getFiveAbove() == 1) {
                    //超过最大阈值
                    accordingId = 11;
                }
                if (abnormalData.getFiveBelow() == 1) {
                    //低于
                    accordingId = 12;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 13;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 14;
                }
            }
            if (sensorCode % 100 == 85) {//风速
                //风速默认为实时数据
                abnormalData.setAlterSensorTypeName("风速");
                abnormalData.setAlterSensorTypeId(85);
                abnormalData.setErrorDataType(1);
                if (abnormalData.getFiveAbove() == 1) {
                    //超过最大阈值
                    accordingId = 15;
                }
                if (abnormalData.getFiveBelow() == 1) {
                    //低于
                    accordingId = 16;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 17;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 18;
                }
            }
            if (sensorCode % 100 == 71) {//流速
                //流速默认为实时数据
                abnormalData.setAlterSensorTypeName("流速");
                abnormalData.setAlterSensorTypeId(71);
                abnormalData.setErrorDataType(1);
                if (abnormalData.getFiveAbove() == 1) {
                    //超过最大阈值
                    accordingId = 19;
                }
                if (abnormalData.getFiveBelow() == 1) {
                    //低于
                    accordingId = 20;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 21;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 22;
                }
            }
            if (sensorCode % 100 == 73) {//气压
                //气压默认为实时数据
                abnormalData.setAlterSensorTypeName("气压");
                abnormalData.setAlterSensorTypeId(73);
                abnormalData.setErrorDataType(1);
                if (abnormalData.getFiveAbove() == 1) {
                    //超过最大阈值
                    accordingId = 23;
                }
                if (abnormalData.getFiveBelow() == 1) {
                    //低于
                    accordingId = 24;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 25;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 26;
                }
            }
            if (sensorCode % 100 == 75) {//气温
                //气温默认为实时数据
                abnormalData.setAlterSensorTypeName("气温");
                abnormalData.setAlterSensorTypeId(75);
                abnormalData.setErrorDataType(1);
                if (abnormalData.getFiveAbove() == 1) {
                    //超过最大阈值
                    accordingId = 27;
                }
                if (abnormalData.getFiveBelow() == 1) {
                    //低于
                    accordingId = 28;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 29;
                }
                if (abnormalData.getFloatingDown() == 1 || abnormalData.getFloatingUp() == 1) {
                    //超出变化量最大最小值
                    accordingId = 30;
                }
            }
            // 数据连续中断,标记为实时 错误类型1
            if (abnormalData.getContinueInterrupt() == 1) {
                accordingId = 1;
                abnormalData.setErrorDataType(1);
            }
            //数据连续保持不变,标记为实时,错误类型2
            if (abnormalData.getKeepTime() == 1) {
                accordingId = 2;
                abnormalData.setErrorDataType(1);
            }
            for (ConfigAbnormalDictionary e : abnormallist) {
                if (("data_"+accordingId).equals(e.getBrokenAccordingId())) {
                    abnormalData.setErrorDataReason(e.getErrorName());
                    abnormalData.setBrokenAccordingId("data_"+accordingId);
                }
            }
        });
        return all;
    }
}
