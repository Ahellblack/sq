package com.siti.wisdomhydrologic.datepull.mapper;

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
            "INSERT INTO `real_day_sensor_data` " +
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

    @Select("Select * from config_sensor_station_comparison")
    List<StationVo> getStation();
}
