package com.siti.wisdomhydrologic.configwarning.mapper;

import com.siti.wisdomhydrologic.configwarning.entity.*;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2019/10/12.
 */
@Component
public interface WarningAbnormalUnRainMapper {

   /* @Select("select * from abnormal_air_pressure")
    List<AbnormalAirPressure> getAbnormalAirPressure();

    @Select("select * from abnormal_air_temperature")
    List<AbnormalAirTemperature> getAbnormalAirTemperature();

    @Select("select * from abnormal_electric")
    List<AbnormalElectric> getAbnormalElectric();

    @Select("select * from abnormal_flow_velocity")
    List<AbnormalFlowVelocity> getAbnormalFlowVelocity();

    @Select("select * from abnormal_tide_level")
    List<AbnormalTideLevel> getAbnormalTideLevel();

    @Select("select * from abnormal_water_level")
    List<AbnormalWaterLevel> getAbnormalWaterLevel();

    @Select("select * from abnormal_wind_direction")
    List<AbnormalWindDirection> getAbnormalWindDirection();

    @Select("select * from abnormal_wind_speed")
    List<AbnormalWindSpeed> getAbnormalWindSpeed();*/

    @Select("select * from ${databaseName}")
    List<UnrainAbnormal> getAllAbnormalByType(@Param("databaseName") String databaseName);

    @Select("select * from ${databaseName} where sensor_code = #{sensorCode}")
    UnrainAbnormal getOneAbnormalByType(@Param("databaseName") String databaseName,@Param("sensorCode")String sensorCode);

    @Update("update ${databaseName} " +
            "set `sensor_code` = #{data.sensorCode}, `sensor_name` = #{data.sensorName}, " +
            "`interrupt_limit` = #{data.interruptLimit}, `level_max` = #{data.levelMax}, `level_min` = #{data.levelMin}, " +
            "`up_max` = #{data.upMax}, `down_max` = #{data.downMax}, `duration` = #{data.duration}, " +
            "`exception_value` = #{data.exceptionValue} WHERE `id` = #{data.id} ")
    int update(@Param("databaseName")String databaseName, @Param("data") UnrainAbnormal entity);

    @Insert("INSERT INTO ${databaseName} " +
            "(`sensor_code`, `sensor_name`, `interrupt_limit`, " +
            "`level_max`, `level_min`, `up_max`, `down_max`, `duration`, `exception_value`) " +
            "VALUES (#{data.sensorCode}, #{data.sensorName}, #{data.interruptLimit}, #{data.levelMax}," +
            " #{data.levelMin}, #{data.upMax}, #{data.downMax}, #{data.duration}, #{data.exceptionValue})")
    int insert(@Param("databaseName")String databaseName, @Param("data") UnrainAbnormal entity);
}
