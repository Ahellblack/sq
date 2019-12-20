package com.siti.wisdomhydrologic.document.mapper;

import com.siti.wisdomhydrologic.document.vo.RainGroupVo;
import com.siti.wisdomhydrologic.document.vo.RainMaxVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zyw on 2019/10/16.
 */
public interface StatisticsRainMapper {

    @Select("select sum(sensor_data_value) from ${databaseName} where sensor_code like '%84' and SUBSTR(sensor_data_upload_time,1,4) = #{year} \n")
    Double getYearRainSum(@Param("databaseName") String databaseName,@Param("year") String year);

    @Select("select sum(sensor_data_value) from ${databaseName} where sensor_code like '%84' and SUBSTR(sensor_data_upload_time,1,4) = #{year}  and SUBSTR(sensor_data_upload_time,6,2) in ('09','08','07','06')\n")
    Double getFloorSeasonRainSum(@Param("databaseName") String databaseName,@Param("year") String year);

    @Select("select count(*) from (select * from ${databaseName} where sensor_code like '%84' and SUBSTR(sensor_data_upload_time,1,4) = #{year} group by SUBSTR(sensor_data_upload_time,1,10) ) a")
    Integer getYearRainNumber(@Param("databaseName") String databaseName,@Param("year") String year);

    @Select("select count(*) from (select * from ${databaseName} where sensor_code like '%84' and SUBSTR(sensor_data_upload_time,1,4) = #{year} and SUBSTR(sensor_data_upload_time,6,2) in ('09','08','07','06')  group by SUBSTR(sensor_data_upload_time,1,10)) a")
    Integer getFloorSeasonRainNumber(@Param("databaseName") String databaseName,@Param("year") String year);

    @Select("select sum(sensor_data_value) AS sum , SUBSTR(sensor_data_upload_time,6,2) AS month from ${databaseName} where sensor_code like '%84' and SUBSTR(sensor_data_upload_time,1,4) = #{year} group by  SUBSTR(sensor_data_upload_time,6,2) order by sum desc")
    List<RainGroupVo> getRainSumGroupByMonth(@Param("databaseName") String databaseName,@Param("year") String year);

    @Select("select sum(sensor_data_value) as sum,station_name from " +
            "(select * from ${databaseName} " +
            "where sensor_code like '%84' and sensor_data_value < 500 " +
            "and SUBSTR(sensor_data_upload_time,1,4) = #{year}) a  " +
            "GROUP BY substr(sensor_code,1,5) " +
            "order by sum desc")
    List<RainGroupVo> getRainDistrbution(@Param("databaseName") String databaseName,@Param("year") String year);

    @Select("select max(sensor_data_value) maxV,station_name,station_code,sensor_data_upload_time  from " +
            "(select *  from ${databaseName} " +
            "where sensor_code like '%84' and SUBSTR(sensor_data_upload_time,1,4) = #{year} " +
            "and sensor_data_value <500 ) a " +
            "GROUP BY station_code ORDER BY maxV DESC LIMIT 1\n")
    RainMaxVo getMaxRainStation(@Param("databaseName") String databaseName, @Param("year") String year);

    @Select("select max(sensor_data_value) maxV,station_name,station_code,sensor_data_upload_time  from " +
            "(select *  from ${databaseName} " +
            "where sensor_code like '%84' " +
            "and sensor_data_value <500 ) a " +
            "GROUP BY station_code ORDER BY maxV DESC LIMIT 1\n")
    RainMaxVo getMaxHourRainStation(@Param("databaseName")String databaseName);
}
