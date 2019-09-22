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
}
