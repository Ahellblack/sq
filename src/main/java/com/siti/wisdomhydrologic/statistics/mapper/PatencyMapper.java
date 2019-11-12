package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.entity.Patency;
import java.util.List;

import com.siti.wisdomhydrologic.statistics.entity.UploadData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zyw on 2019/9/20.
 */
public interface PatencyMapper {

    @Select("<script>select a.sensor_code as station_id ,count(*) as number from " +
            "(select * FROM ${datebaseName} WHERE time &gt;= #{startTime} and time &lt; #{endTime} " +
            " and sensor_code in(" +
            "<foreach collection=\"sensorCode\" item=\"item\" separator=\",\"> #{item} </foreach>)) a " +
            "GROUP BY a.sensor_code</script>")
    Patency getPatency(@Param("datebaseName") String datebaseName,@Param("sensorCode")List<String> sensorCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT table_name FROM information_schema.TABLES WHERE table_name = #{datebaseName};")
    List<String> isExist(@Param("datebaseName") String datebaseName);

    @Select("<script>select count(*) from ${datebaseName} where to_days(sensor_data_time) = to_days(#{date})" +
            "<if test = \'stationId != null\'> and SUBSTR(sensor_code,1,5) = #{stationId} </if></script>")
    Integer getRealTSDBData(@Param("datebaseName") String datebaseName,@Param("date") String date,@Param("stationId") Integer stationId);

    @Select("<script>select count(*) from ${datebaseName} where to_days(sensor_data_upload_time) = to_days(#{date})" +
            "<if test = \'stationId != null\'> and SUBSTR(sensor_code,1,5) = #{stationId} </if></script>")
    Integer getRealHourData(@Param("datebaseName") String datebaseName,@Param("date") String date,@Param("stationId") Integer stationId);

    @Select("<script>select count(*) from `${datebaseName}` where to_days(time) = to_days(#{date})" +
            "<if test = \'stationId != null\'> and SUBSTR(sensor_code,1,5) = #{stationId} </if></script>")
    Integer getRealRTSQData(@Param("datebaseName") String datebaseName,@Param("date") String date,@Param("stationId") Integer stationId);

    @Select("select * from data_upload_info where substr(`year_month`,1,4) = #{year}")
    List<UploadData> uploadList(@Param("year") String year);
}
