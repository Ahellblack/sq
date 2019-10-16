package com.siti.wisdomhydrologic.document.mapper;

import com.siti.wisdomhydrologic.document.vo.TideGroupVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/10/16.
 */
public interface StatisticsTideMapper {


    @Select("select max(sensor_data_value) MAXV, min(sensor_data_value) MINV, avg(sensor_data_value) AVGV," +
            "station_name,station_code from (" +
            "select * from ${databaseName} where sensor_code like '%81' " +
            "and SUBSTR(sensor_data_upload_time,1,4) = #{year} " +
            "and sensor_data_value > 0 ) a  where station_code =#{stationCode}  ")
    TideGroupVo getTide(@Param("databaseName") String databaseName, @Param("year") String year,@Param("stationCode") String stationCode);


    @Select("select count(*) from ${databaseName} " +
            "where sensor_code = #{sensorCode} " +
            "and SUBSTR(sensor_data_upload_time,1,4) = #{year} " +
            "and sensor_data_value > #{value}\n")
    Integer getTideOver(@Param("databaseName") String databaseName, @Param("year") String year,@Param("sensorCode") String sensorCode,@Param("value")Double value);


}
