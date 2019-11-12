package com.siti.wisdomhydrologic.document.mapper;

import com.siti.wisdomhydrologic.document.vo.WaterGroupVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zyw on 2019/10/16.
 */
public interface StatisticWaterMapper {

    @Select("select max(sensor_data_value) MAXV,avg(sensor_data_value)," +
            "station_name,station_code from (" +
            "select * from ${databaseName} " +
            "where sensor_code like '%83' and SUBSTR(sensor_data_upload_time,1,4) = #{year} " +
            "and sensor_data_value < 27 and sensor_data_value > 0) a ")
    WaterGroupVo getMaxWater(@Param("databaseName") String databaseName, @Param("year") String year);

    @Select("select min(sensor_data_value) MAXV,avg(sensor_data_value)," +
            "station_name,station_code from (" +
            "select * from ${databaseName} " +
            "where sensor_code like '%83' and SUBSTR(sensor_data_upload_time,1,4) = #{year} " +
            "and sensor_data_value < 27 and sensor_data_value > 0) a ")
    WaterGroupVo getMinWater(@Param("databaseName") String databaseName, @Param("year") String year);

    @Select("select count(*) from ${databaseName} " +
            "where sensor_code = #{sensorCode} " +
            "and SUBSTR(sensor_data_upload_time,1,4) = #{year} " +
            "and sensor_data_value < 27 " +
            "and sensor_data_value > 3.0")
    Integer getWaterOver(@Param("databaseName") String databaseName, @Param("year") String year,@Param("sensorCode")String sensorCode);
}
