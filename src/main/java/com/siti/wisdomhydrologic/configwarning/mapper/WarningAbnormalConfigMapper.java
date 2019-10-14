package com.siti.wisdomhydrologic.configwarning.mapper;

import com.siti.wisdomhydrologic.configwarning.entity.AbnormalRainfall;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2019/10/12.
 */
@Component
public interface WarningAbnormalConfigMapper {

    @Select("<script>select * from abnormal_rainfall " +
            "<if test = \'sensorCode !=null \'>where sensor_code = #{sensorCode} </if>" +
            "</script>")
    List<AbnormalRainfall> getRainAll(@Param("sensorCode")String sensorCode);

    @Insert("INSERT INTO `abnormal_rainfall`" +
            "(`id`, `sensor_code`, `sensor_name`, `interrupt_limit`, `max_day_level`, `min_day_level`, `max_hour_level`, `min_hour_level`, `max_five_level`, `min_five_level`, `nearby_sensor_code`, `nearby_rate`, `exception_value`) " +
            "VALUES ( #{data.sensorCode},#{data.sensorName}, #{data.interruptLimit}, #{data.maxDayLevel}, #{data.minDayLevel}, #{data.maxHourLevel}, #{data.minHourLevel}, #{data.maxFiveLevel}, #{data.minFiveLevel},#{data.nearbySensorCode} ,#{data.nearbyRate},#{data.nearbyRate},#{data.exceptionValue})")
    int addAbnormalRainfall(@Param("data") AbnormalRainfall abnormalRainfall);

    @Update("UPDATE `abnormal_rainfall` SET `sensor_code` = #{data.sensorCode}, `sensor_name` = #{data.sensorName}, `interrupt_limit` = #{data.interruptLimit}," +
            " `max_day_level` = #{data.maxDayLevel}, `min_day_level` = #{data.minDayLevel}, `max_hour_level` = #{data.maxHourLevel}," +
            " `min_hour_level` = #{data.maxHourLevel}, `max_five_level` =  #{data.maxFiveLevel}," +
            "`min_five_level` = #{data.minFiveLevel}, `nearby_sensor_code` = #{data.nearbySensorCode}, `nearby_rate` = #{data.nearbyRate}, " +
            "`exception_value` = #{data.exceptionValue} WHERE `id` = #{data.id}")
    int updateAbnormalRainfall(@Param("data")AbnormalRainfall abnormalRainfall);

    @Delete("delete from abnormal_rainfall where id =#{id} ")
    int deleteAbnormalRainfall(@Param("id") Integer id);

    @Select("select * from abnormal_rainfall where sensor_code = #{sensorCode}")
    AbnormalRainfall getOneAbnormal(@Param("sensorCode") String sensorCode);
}
