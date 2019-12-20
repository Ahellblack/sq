package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import com.siti.wisdomhydrologic.operation.vo.RainVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zyw on 2019/7/31.
 */
public interface StationCheckMantainMapper extends Mapper<ReportStationCheckMantain>{

    @Delete("delete from report_station_check_mantain where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);

    @Select("select * from report_station_check_mantain where station_id = #{stationId}" +
            " and mantain_date = #{mantainDate}")
    ReportStationCheckMantain getByStationId(@Param("mantainDate") String mantainDate, @Param("stationId") String stationId);

    @Select("<script> " +
            "select a.* from report_station_check_mantain as a join " +
            "config_river_station as b on a.station_id=b.station_id where 1" +
            "<if test=\"maintainDate!=null and maintainDate!=''\"> and a.mantain_date like '${maintainDate}%'</if> " +
            "<if test=\"stationId!=null and stationId!='' \"> and a.station_id =#{stationId}</if> " +
            "<if test=\"orgList!=null and orgList.size()>0\">" +
            "   and b.sys_org in " +
            "   <foreach collection=\"orgList\" item=\"org\" " +
            "       index=\"index\" open=\"(\" close=\")\" separator=\",\">" +
            "       #{org}" +
            "   </foreach>" +
            "</if> " +
            "</script>")
    List<ReportStationCheckMantain> getListByDateAndStationId(@Param("maintainDate") String maintainDate,
                                                              @Param("stationId") String stationId,
                                                              @Param("orgList") List<Integer> orgList);


    @Insert("INSERT INTO `report_station_check_mantain` (" +
            "`station_id`," +
            "`station_name`," +
            "`mantain_date`, " +
            "`rtu_type`, " +
            "`check_rain_sensor`, " +
            "`check_water_level_sensor`, " +
            "`check_speed_direction_sensor`, " +
            "`check_flow_sensor`, " +
            "`check_other_sensor`, " +
            "`data_collection_correct`, " +
            "`data_collection_parameter_check`, " +
            "`data_collection_normal`, " +
            "`data_communicate_parameter_check`, " +
            "`data_communicate_channel`, " +
            "`solar_energy_voltage_check`, " +
            "`solar_energy_voltage_value`, " +
            "`storage_battery_voltage_check`, " +
            "`storage_battery_value`, " +
            "`rainfall_sensor_normal`, " +
            "`rainfall_sensor_clean_check`, " +
            "`rainfall_sensor_range_check`, " +
            "`water_level_check_normal`, " +
            "`water_level_check_shaft`, " +
            "`water_level_clean_check`, " +
            "`water_level_adjust`, " +
            "`water_level_zero_height`, " +
            "`water_level_staff_value`, " +
            "`water_level_time`, " +
            "`water_level_indicator_value`, " +
            "`water_level_value`, " +
            "`speed_direction_check_normal`, " +
            "`speed_direction_check_lighting_protection`, " +
            "`flowmeter_data_check_normal`, " +
            "`flowmeter_pitch_gesture`, " +
            "`flowmeter_rolling_gesture`, " +
            "`flowmeter_signal_strength`, " +
            "`flowmeter_record_time_warp`, " +
            "`line_pipe_check_normal`, " +
            "`station_enviroment`, " +
            "`station_clean_check`, " +
            "`remark`, " +
            "`create_by`) " +
            "VALUES (" +
            "#{obj.stationId}," +
            "#{obj.stationName}," +
            "#{obj.mantainDate}," +
            "#{obj.rtuType}," +
            "#{obj.checkRainSensor}," +
            "#{obj.checkWaterLevelSensor}," +
            "#{obj.checkSpeedDirectionSensor}," +
            "#{obj.checkFlowSensor}," +
            "#{obj.checkOtherSensor}," +
            "#{obj.dataCollectionCorrect}," +
            "#{obj.dataCollectionParameterCheck}," +
            "#{obj.dataCollectionNormal}," +
            "#{obj.dataCommunicateParameterCheck}," +
            "#{obj.dataCommunicateChannel}," +
            "#{obj.solarEnergyVoltageCheck}," +
            "#{obj.solarEnergyVoltageValue}," +
            "#{obj.storageBatteryVoltageCheck}," +
            "#{obj.storageBatteryValue}," +
            "#{obj.rainfallSensorNormal}," +
            "#{obj.rainfallSensorCleanCheck}," +
            "#{obj.rainfallSensorRangeCheck}," +
            "#{obj.waterLevelCheckNormal}," +
            "#{obj.waterLevelCheckShaft}," +
            "#{obj.waterLevelCleanCheck}," +
            "#{obj.waterLevelAdjust}," +
            "#{obj.waterLevelZeroHeight}," +
            "#{obj.waterLevelStaffValue}," +
            "#{obj.waterLevelTime}," +
            "#{obj.waterLevelIndicatorValue}," +
            "#{obj.waterLevelValue}," +
            "#{obj.speedDirectionCheckNormal}," +
            "#{obj.speedDirectionCheckLightingProtection}," +
            "#{obj.flowmeterDataCheckNormal}," +
            "#{obj.flowmeterPitchGesture}," +
            "#{obj.flowmeterRollingGesture}," +
            "#{obj.flowmeterSignalStrength}," +
            "#{obj.flowmeterRecordTimeWarp}," +
            "#{obj.linePipeCheckNormal}," +
            "#{obj.stationEnviroment}," +
            "#{obj.stationCleanCheck}," +
            "#{obj.remark}," +
            "#{obj.createBy})")
    int insert(@Param("obj") ReportStationCheckMantain reportStationCheckMantain);

    @Update("update `report_station_check_mantain` set " +
            "`station_id`=#{obj.stationId}," +
            "`station_name`=#{obj.stationName}," +
            "`mantain_date`=#{obj.mantainDate}, " +
            "`rtu_type`=#{obj.rtuType}, " +
            "`check_rain_sensor`=#{obj.checkRainSensor}, " +
            "`check_water_level_sensor`=#{obj.checkWaterLevelSensor}, " +
            "`check_speed_direction_sensor`=#{obj.checkSpeedDirectionSensor}, " +
            "`check_flow_sensor`=#{obj.checkFlowSensor}, " +
            "`check_other_sensor`=#{obj.checkOtherSensor}, " +
            "`data_collection_correct`=#{obj.dataCollectionCorrect}, " +
            "`data_collection_parameter_check`=#{obj.dataCollectionParameterCheck}, " +
            "`data_collection_normal`=#{obj.dataCollectionNormal}, " +
            "`data_communicate_parameter_check`=#{obj.dataCommunicateParameterCheck}, " +
            "`data_communicate_channel`=#{obj.dataCommunicateChannel}, " +
            "`solar_energy_voltage_check`=#{obj.solarEnergyVoltageCheck}, " +
            "`solar_energy_voltage_value`=#{obj.solarEnergyVoltageValue}, " +
            "`storage_battery_voltage_check`=#{obj.storageBatteryVoltageCheck}, " +
            "`storage_battery_value`=#{obj.storageBatteryValue}, " +
            "`rainfall_sensor_normal`=#{obj.rainfallSensorNormal}, " +
            "`rainfall_sensor_clean_check`=#{obj.rainfallSensorCleanCheck}, " +
            "`rainfall_sensor_range_check`=#{obj.rainfallSensorRangeCheck}, " +
            "`water_level_check_normal`=#{obj.waterLevelCheckNormal}, " +
            "`water_level_check_shaft`=#{obj.waterLevelCheckShaft}, " +
            "`water_level_clean_check`=#{obj.waterLevelCleanCheck}, " +
            "`water_level_adjust`=#{obj.waterLevelAdjust}, " +
            "`water_level_zero_height`=#{obj.waterLevelZeroHeight}, " +
            "`water_level_staff_value`=#{obj.waterLevelStaffValue}, " +
            "`water_level_time`=#{obj.waterLevelTime}, " +
            "`water_level_indicator_value`=#{obj.waterLevelIndicatorValue}, " +
            "`water_level_value`=#{obj.waterLevelValue}, " +
            "`speed_direction_check_normal`=#{obj.speedDirectionCheckNormal}, " +
            "`speed_direction_check_lighting_protection`=#{obj.speedDirectionCheckLightingProtection}, " +
            "`flowmeter_data_check_normal`=#{obj.flowmeterDataCheckNormal}, " +
            "`flowmeter_pitch_gesture`=#{obj.flowmeterPitchGesture}, " +
            "`flowmeter_rolling_gesture`=#{obj.flowmeterRollingGesture}, " +
            "`flowmeter_signal_strength`=#{obj.flowmeterSignalStrength}, " +
            "`flowmeter_record_time_warp`=#{obj.flowmeterRecordTimeWarp}, " +
            "`line_pipe_check_normal`=#{obj.linePipeCheckNormal}, " +
            "`station_enviroment`=#{obj.stationEnviroment}, " +
            "`station_clean_check`=#{obj.stationCleanCheck}, " +
            "`remark`=#{obj.remark}, " +
            "`create_by`=#{obj.createBy} where report_id=#{obj.reportId}")
    int update(@Param("obj")ReportStationCheckMantain reportStationCheckMantain);


    @Select("select * from report_station_check_mantain where report_id=#{reportId}")
    ReportStationCheckMantain getByReportId(@Param("reportId") Integer reportId);

//    @Update("update report_station_check_mantain SET `station_manange_org_id` = #{data.stationManangeOrgId}, " +
//            "`station_manage_org_name` = #{data.stationManageOrgName}, `rtu_type` = #{data.rtuType}," +
//            " `check_rain_sensor` = #{data.checkRainSensor}, `check_water_level_sensor` = #{data.checkWaterLevelSensor}, " +
//            "`check_speed_direction_sensor` = #{data.checkSpeedDirectionSensor}, `check_flow_sensor` = #{data.checkFlowSensor}, " +
//            "`check_other_sensor` = #{data.checkOtherSensor}, `data_collection_correct` = #{data.dataCollectionCorrect}, " +
//            "`data_collection_parameter_check` = #{data.dataCollectionParameterCheck}, `data_collection_normal` = #{data.dataCollectionNormal}, " +
//            "`data_communicate_parameter_check` = #{data.dataCommunicateParameterCheck}, `data_communicate_channel` = #{data.dataCommunicateChannel}, " +
//            "`solar_energy_voltage_check` = #{data.solarEnergyVoltageCheck}, `solar_energy_voltage_value` = #{data.solarEnergyVoltageValue}, " +
//            "`storage_battery_voltage_check` = #{data.storageBatteryVoltageCheck}, `storage_battery_value` = #{data.storageBatteryValue}, " +
//            "`rainfall_sensor_normal` = #{data.rainfallSensorNormal}, `rainfall_sensor_clean_check` = #{data.rainfallSensorCleanCheck}, " +
//            "`rainfall_sensor_range_check` = #{data.rainfallSensorRangeCheck}, `water_level_check_normal` = #{data.waterLevelCheckNormal}, " +
//            "`water_level_check_shaft` = #{data.waterLevelCheckShaft},`water_level_zero_height`=#{data.waterLevelZeroHeight}," +
//            "`water_level_staff_value` =#{data.waterLevelStaffValue},`water_level_time` = #{data.waterLevelTime}, `water_level_indicator_value` =#{data.waterLevelIndicatorValue}," +
//            "`water_level_value` = #{data.waterLevelValue}, `water_level_clean_check` = #{data.waterLevelCleanCheck}, " +
//            "`water_level_adjust` = #{data.waterLevelAdjust}, `speed_direction_check_normal` = #{data.speedDirectionCheckNormal}, " +
//            "`speed_direction_check_lighting_protection` = #{data.speedDirectionCheckLightingProtection}, `flowmeter_data_check_normal` = #{data.flowmeterDataCheckNormal}," +
//            " `flowmeter_pitch_gesture` = #{data.flowmeterPitchGesture}, `flowmeter_rolling_gesture` = #{data.flowmeterRollingGesture}, `flowmeter_signal_strength` = #{data.flowmeterSignalStrength}, " +
//            "`flowmeter_record_time_warp` = #{data.flowmeterRecordTimeWarp}, `line_pipe_check_normal` = #{data.linePipeCheckNormal}, `station_enviroment` = #{data.stationEnviroment}, " +
//            "`station_clean_check` = #{data.stationCleanCheck}, `remark` = #{data.remark}, `create_by` = #{data.createBy} " +
//            "WHERE `mantain_date` = #{data.mantainDate} AND `station_name` = #{data.stationName}")
//    int update(@Param("data")ReportStationCheckMantain reportStationCheckMantain);
}
