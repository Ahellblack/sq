package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.entity.DeviceChange;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zyw on 2019/9/23.
 */
public interface DeviceMapper {

    @Select("<script>select count(*) as number,new_device_name " +
            "from record_device_replace  rdr  " +
            "left join config_river_station crs " +
            "on rdr.station_code = crs.station_id  " +
            "where SUBSTR(replace_date,1,4) = #{year} " +
            "and  SUBSTR(replace_date,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) " +
            "<if test=\"regionId !=null\">and region_id = #{regionId} </if> " +
            "GROUP BY new_device_name </script>")
    List<DeviceChange> getList(@Param("regionId") Integer regionId,
                               @Param("year")Integer year,
                               @Param("list")List<String> list);
}
