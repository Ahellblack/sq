package com.siti.wisdomhydrologic.datepull.mapper;

import com.siti.wisdomhydrologic.datepull.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.datepull.vo.StationVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by dell on 2019/7/18.
 */
public interface DayDataMapper {

    @Insert("<script>" +
            "INSERT INTO `history_day_sensor_data_2018_2022` " +
            "( `sensor_code`, `sensor_data_value`, `sensor_type_id`, `sensor_type_name`, " +
            "`sensor_data_unit`, `sensor_data_upload_time`, `sensor_avg_data`, " +
            "`sensor_max_data`, `sensor_max_data_time`, `sensor_min_data`, `sensor_min_data_time`," +
            " `sensor_data_value_flag`, `sensor_avg_data_value_flag`, `sensor_max_data_value_flag`," +
            " `sensor_min_data_value_flag`, `station_code`, `station_name`) VALUES" +
            "<foreach collection=\"dayList\" item=\"item\" separator=\",\">" +
            " (#{item.senId},#{item.v},#{item.sensorTypeId},#{item.sensorTypeName},#{item.sensorDataUnit}," +
            "#{item.time},#{item.avgV},#{item.maxV},#{item.maxT},#{item.minV},#{item.minT}," +
            "#{item.s},#{item.avgS},#{item.maxS},#{item.minS},#{item.stationId},#{item.stationName})" +
            "</foreach></script>")
    int addDayData(@Param("dayList") List<DayVo> dayList) ;

    @Select("Select * from config_sensor_section_module")
    List<ConfigSensorSectionModule> getStation();

    @Insert("<script>" +
            "INSERT INTO `wisdomhydrologic`.`history_hour_sensor_data_2014` " +
            "(`sensor_code`, `sensor_data_value`, `sensor_type_id`, `sensor_type_name`, `sensor_data_unit`," +
            " `sensor_data_upload_time`,`sensor_avg_data`, `sensor_max_data`, `sensor_max_data_time`, `sensor_min_data`, `sensor_min_data_time`, " +
            "`sensor_data_value_flag`, `sensor_avg_data_value_flag`, `sensor_max_data_value_flag`, `sensor_min_data_value_flag`," +
            "  `station_code`, `station_name`)  VALUES" +
            "<foreach collection=\"hourList\" item=\"item\" separator=\",\">" +
            " (#{item.senId},#{item.v},#{item.sensorTypeId},#{item.sensorTypeName},#{item.sensorDataUnit}," +
            "#{item.time},#{item.avgV},#{item.maxV},#{item.maxT},#{item.minV},#{item.minT}," +
            "#{item.s},#{item.avgS},#{item.maxS},#{item.minS},#{item.stationId},#{item.stationName})" +
            "</foreach></script>")
    int add2014HourData(@Param("hourList") List<DayVo> hourVo);

    @Insert("<script>" +
            "INSERT INTO `wisdomhydrologic`.`history_hour_sensor_data_2015` " +
            "(`sensor_code`, `sensor_data_value`, `sensor_type_id`, `sensor_type_name`, `sensor_data_unit`," +
            " `sensor_data_upload_time`,`sensor_avg_data`, `sensor_max_data`, `sensor_max_data_time`, `sensor_min_data`, `sensor_min_data_time`, " +
            "`sensor_data_value_flag`, `sensor_avg_data_value_flag`, `sensor_max_data_value_flag`, `sensor_min_data_value_flag`," +
            "  `station_code`, `station_name`)  VALUES" +
            "<foreach collection=\"hourList\" item=\"item\" separator=\",\">" +
            " (#{item.senId},#{item.v},#{item.sensorTypeId},#{item.sensorTypeName},#{item.sensorDataUnit}," +
            "#{item.time},#{item.avgV},#{item.maxV},#{item.maxT},#{item.minV},#{item.minT}," +
            "#{item.s},#{item.avgS},#{item.maxS},#{item.minS},#{item.stationId},#{item.stationName})" +
            "</foreach></script>")
    int add2015HourData(@Param("hourList") List<DayVo> hourVo);

    @Insert("<script>" +
            "INSERT INTO `wisdomhydrologic`.`history_hour_sensor_data_2016` " +
            "(`sensor_code`, `sensor_data_value`, `sensor_type_id`, `sensor_type_name`, `sensor_data_unit`," +
            " `sensor_data_upload_time`,`sensor_avg_data`, `sensor_max_data`, `sensor_max_data_time`, `sensor_min_data`, `sensor_min_data_time`, " +
            "`sensor_data_value_flag`, `sensor_avg_data_value_flag`, `sensor_max_data_value_flag`, `sensor_min_data_value_flag`," +
            "  `station_code`, `station_name`)  VALUES" +
            "<foreach collection=\"hourList\" item=\"item\" separator=\",\">" +
            " (#{item.senId},#{item.v},#{item.sensorTypeId},#{item.sensorTypeName},#{item.sensorDataUnit}," +
            "#{item.time},#{item.avgV},#{item.maxV},#{item.maxT},#{item.minV},#{item.minT}," +
            "#{item.s},#{item.avgS},#{item.maxS},#{item.minS},#{item.stationId},#{item.stationName})" +
            "</foreach></script>")
    int add2016HourData(@Param("hourList") List<DayVo> hourVo);

    @Insert("<script>" +
            "INSERT INTO `wisdomhydrologic`.`history_hour_sensor_data_2017` " +
            "(`sensor_code`, `sensor_data_value`, `sensor_type_id`, `sensor_type_name`, `sensor_data_unit`," +
            " `sensor_data_upload_time`,`sensor_avg_data`, `sensor_max_data`, `sensor_max_data_time`, `sensor_min_data`, `sensor_min_data_time`, " +
            "`sensor_data_value_flag`, `sensor_avg_data_value_flag`, `sensor_max_data_value_flag`, `sensor_min_data_value_flag`," +
            "  `station_code`, `station_name`)  VALUES" +
            "<foreach collection=\"hourList\" item=\"item\" separator=\",\">" +
            " (#{item.senId},#{item.v},#{item.sensorTypeId},#{item.sensorTypeName},#{item.sensorDataUnit}," +
            "#{item.time},#{item.avgV},#{item.maxV},#{item.maxT},#{item.minV},#{item.minT}," +
            "#{item.s},#{item.avgS},#{item.maxS},#{item.minS},#{item.stationId},#{item.stationName})" +
            "</foreach></script>")
    int add2017HourData(@Param("hourList") List<DayVo> hourVo);

    @Insert("<script>" +
            "INSERT INTO `wisdomhydrologic`.`history_hour_sensor_data_2019` " +
            "(`sensor_code`, `sensor_data_value`, `sensor_type_id`, `sensor_type_name`, `sensor_data_unit`," +
            " `sensor_data_upload_time`,`sensor_avg_data`, `sensor_max_data`, `sensor_max_data_time`, `sensor_min_data`, `sensor_min_data_time`, " +
            "`sensor_data_value_flag`, `sensor_avg_data_value_flag`, `sensor_max_data_value_flag`, `sensor_min_data_value_flag`," +
            "  `station_code`, `station_name`)  VALUES" +
            "<foreach collection=\"hourList\" item=\"item\" separator=\",\">" +
            " (#{item.senId},#{item.v},#{item.sensorTypeId},#{item.sensorTypeName},#{item.sensorDataUnit}," +
            "#{item.time},#{item.avgV},#{item.maxV},#{item.maxT},#{item.minV},#{item.minT}," +
            "#{item.s},#{item.avgS},#{item.maxS},#{item.minS},#{item.stationId},#{item.stationName})" +
            "</foreach></script>")
    int add2019HourData(@Param("hourList") List<DayVo> hourVo);

    @Insert("<script>" +
            "INSERT INTO `wisdomhydrologic`.`history_hour_sensor_data_2019` " +
            "(`sensor_code`, `sensor_data_value`, `sensor_type_id`, `sensor_type_name`, `sensor_data_unit`," +
            " `sensor_data_upload_time`,`sensor_avg_data`, `sensor_max_data`, `sensor_max_data_time`, `sensor_min_data`, `sensor_min_data_time`, " +
            "`sensor_data_value_flag`, `sensor_avg_data_value_flag`, `sensor_max_data_value_flag`, `sensor_min_data_value_flag`," +
            "  `station_code`, `station_name`)  VALUES" +
            "<foreach collection=\"hourList\" item=\"item\" separator=\",\">" +
            " (#{item.senId},#{item.v},#{item.sensorTypeId},#{item.sensorTypeName},#{item.sensorDataUnit}," +
            "#{item.time},#{item.avgV},#{item.maxV},#{item.maxT},#{item.minV},#{item.minT}," +
            "#{item.s},#{item.avgS},#{item.maxS},#{item.minS},#{item.stationId},#{item.stationName})" +
            "</foreach></script>")
    int addHourData(@Param("hourList") List<DayVo> hourVo);
}
