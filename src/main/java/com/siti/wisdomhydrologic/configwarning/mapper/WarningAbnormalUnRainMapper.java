package com.siti.wisdomhydrologic.configwarning.mapper;

import com.siti.wisdomhydrologic.configwarning.entity.*;
import com.sun.tools.javac.util.List;
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

    @Update("update ${databaseName} set ")
    int update(@Param("databaseName")String databaseName, @Param("data") UnrainAbnormal entity);
}
