package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.entity.BrokenType;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/9/23.
 */
public interface BrokenNumberMapper {

    @Select("<script> SELECT " +
            " count( * ) AS number,d.broken_according_id,d.broken_according FROM " +
            " (" +
            " SELECT " +
            " c.broken_according,c.broken_according_id,a.station_id " +
            " FROM " +
            " report_station_broken a " +
            " RIGHT JOIN config_river_station b ON a.station_id = b.station_id " +
            " RIGHT JOIN config_abnormal_dictionary c ON c.broken_according_id = a.broken_according_id " +
            " WHERE " +
            "1 = 1 " +
            " and b.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            " <if test=\"stationId!=null\">AND a.station_id = #{stationId} </if> " +
            " AND SUBSTR( a.create_time, 1, 4 ) = #{year} " +
            " AND SUBSTR( a.create_time, 6, 2 ) IN (<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) " +
            " ) d " +
            " WHERE " +
            " d.station_id IS NOT NULL " +
            " GROUP BY " +
            " d.broken_according_id </script>")
    List<BrokenType> getList(@Param("stationId") Integer stationId,
                             @Param("year")Integer year,
                             @Param("list")List<String> list,
                             @Param("orgId")Integer orgId);

}
