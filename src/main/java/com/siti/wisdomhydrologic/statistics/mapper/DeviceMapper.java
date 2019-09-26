package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.entity.DeviceChange;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/9/23.
 */
public interface DeviceMapper {

    @Select("<script>select count(*) as number,new_device_name " +
            "from record_device_replace  " +
            "where SUBSTR(replace_date,1,4) = #{year} " +
            "and  SUBSTR(replace_date,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) " +
            "<if test=\"stationId!=null\">and station_code = #{stationId} </if> " +
            "GROUP BY new_device_name </script>")
    List<DeviceChange> getList(@Param("stationId") Integer stationId,
                               @Param("year")Integer year,
                               @Param("list")List<String> list);
}
