package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.entity.Patency;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/9/20.
 */
public interface PatencyMapper {

    @Select("<script>select a.sensor_code as station_id ,count(*) as number from " +
            "(select * FROM `real` WHERE time BETWEEN " +
            "#{startTime} and #{endTime} " +
            "and sensor_code in(" +
            "<foreach collection=\"sensorCode\" item=\"item\" separator=\",\"> #{item} </foreach>)) a " +
            "GROUP BY a.sensor_code</script>")
    List<Patency> getPatency(@Param("sensorCode")List<String> sensorCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("select count(*) from ${datebaseName} where to_days(sensor_data_time) = to_days(now());")
    Integer getRealTSDBData(@Param("datebaseName") String datebaseName);

    @Select("select count(*) from ${datebaseName} where to_days(sensor_data_upload_time) = to_days(now());")
    Integer getRealHourData(@Param("datebaseName") String datebaseName);

    @Select("select count(*) from `${datebaseName}` where to_days(time) = to_days(now());")
    Integer getRealRTSQData(@Param("datebaseName") String datebaseName);
}
