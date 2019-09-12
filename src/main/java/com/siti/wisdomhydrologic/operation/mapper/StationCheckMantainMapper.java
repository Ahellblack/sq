package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by dell on 2019/7/31.
 */
public interface StationCheckMantainMapper extends Mapper<ReportStationCheckMantain>{

    @Delete("delete from report_station_check_mantain where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);

    @Select("select * from report_station_check_mantain where station_code = #{stationId}" +
            " and mantain_date = #{mantainDate}")
    ReportStationCheckMantain getByStationId(@Param("mantainDate") String mantainDate, @Param("stationId") Integer stationId);

    @Update("update report_station_check_mantain SET `station_manange_org_id` = #{data.stationManangeOrgId}, " +
            "`station_manage_org_name` = #{data.stationManageOrgName}, `rtu_type` = #{data.rtuType}," +
            " `check_rain_sensor` = #{data.checkRainSensor}, `check_water_level_sensor` = #{data.checkWaterLevelSensor}, " +
            "`check_speed_direction_sensor` = #{data.checkSpeedDirectionSensor}, `check_flow_sensor` = #{data.checkFlowSensor}, " +
            "`check_other_sensor` = #{data.checkOtherSensor}, `data_collection_correct` = #{data.dataCollectionCorrect}, " +
            "`data_collection_parameter_check` = #{data.dataCollectionParameterCheck}, `data_collection_normal` = #{data.dataCollectionNormal}, " +
            "`data_communicate_parameter_check` = #{data.dataCommunicateParameterCheck}, `data_communicate_channel` = #{data.dataCommunicateChannel}, " +
            "`solar_energy_voltage_check` = #{data.solarEnergyVoltageCheck}, `solar_energy_voltage_value` = #{data.solarEnergyVoltageValue}, " +
            "`storage_battery_voltage_check` = #{data.storageBatteryVoltageCheck}, `storage_battery_value` = #{data.storageBatteryValue}, " +
            "`rainfall_sensor_normal` = #{data.rainfallSensorNormal}, `rainfall_sensor_clean_check` = #{data.rainfallSensorCleanCheck}, " +
            "`rainfall_sensor_range_check` = #{data.rainfallSensorRangeCheck}, `water_level_check_normal` = #{data.waterLevelCheckNormal}, " +
            "`water_lever_check_shaft` = #{data.waterLeverCheckShaft}, `water_lever_clean_check` = #{data.waterLeverCleanCheck}, " +
            "`water_lever_adjust` = #{data.waterLeverAdjust}, `speed_direction_check_normal` = #{data.speedDirectionCheckNormal}, " +
            "`speed_direction_check_lighting_protection` = #{data.speedDirectionCheckLightingProtection}, `flowmeter_data_check_normal` = #{data.flowmeterDataCheckNormal}," +
            " `flowmeter_pitch_gesture` = #{data.flowmeterPitchGesture}, `flowmeter_rolling_gesture` = #{data.flowmeterRollingGesture}, `flowmeter_signal_strength` = #{data.flowmeterSignalStrength}, " +
            "`flowmeter_record_time_warp` = #{data.flowmeterRecordTimeWarp}, `line_pipe_check_normal` = #{data.linePipeCheckNormal}, `station_enviroment` = #{data.stationEnviroment}, " +
            "`station_clean_check` = #{data.stationCleanCheck}, `remark` = #{data.remark}, `create_by` = #{data.createBy} " +
            "WHERE `mantain_date` = #{data.mantainDate} AND `station_name` = #{data.stationName}")
    int update(@Param("data")ReportStationCheckMantain reportStationCheckMantain);
}
