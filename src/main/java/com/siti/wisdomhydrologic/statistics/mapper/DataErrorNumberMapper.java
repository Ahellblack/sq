package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.statistics.entity.DataError;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/9/26.
 */
public interface DataErrorNumberMapper {

    @Select("<script> SELECT " +
            " count( * ) AS number,d.broken_according_id,d.broken_according FROM " +
            " (" +
            " SELECT " +
            " c.broken_according,c.broken_according_id,a.station_code " +
            " FROM " +
            " report_manage_data_mantain a " +
            " RIGHT JOIN config_river_station b ON a.station_code = b.station_id " +
            " RIGHT JOIN config_abnormal_dictionary c ON c.broken_according_id = a.broken_according_id " +
            " WHERE " +
            "1 = 1 " +
            " and b.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            " <if test=\"stationId!=null and stationId != ''\">AND a.station_code = #{stationId} </if> " +
            " <if test=\"year!=null\"> AND SUBSTR( a.create_time, 1, 4 ) = #{year} </if> " +
            " <if test=\"list!=null\">AND SUBSTR( a.create_time, 6, 2 ) IN (<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>)</if> " +
            " <if test=\"dataTime!=null\">AND SUBSTR( a.create_time, 1, 10 ) = #{dataTime} </if>" +
            " ) d " +
            " WHERE " +
            " d.station_code IS NOT NULL " +
            " GROUP BY " +
            " d.broken_according_id </script>")
    List<DataError> getList(@Param("stationId") Integer stationId,
                            @Param("year")Integer year,
                            @Param("list") List<String> list,
                            @Param("dataTime")String dataTime,
                            @Param("orgId")Integer orgId);

    //本月的异常易发时间查询
    @Select("<script>SELECT * FROM `report_manage_data_mantain` a " +
            " left JOIN config_river_station b ON a.station_code = b.station_id " +
            " WHERE 1=1" +
            " AND b.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            " <if test = \'stationId != null \'>and a.station_code = #{stationId}</if> " +
            " <if test=\"year!=null\"> AND SUBSTR( a.create_time, 1, 4 ) = #{year} </if> " +
            " <if test=\"list!=null\">AND SUBSTR( a.create_time, 6, 2 ) IN (<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>)</if> " +
            " <if test=\"dataTime!=null\">AND SUBSTR( a.create_time, 1, 10 ) = #{dataTime} </if>" +
            " GROUP BY SUBSTR(a.create_time,12,8) </script>")
    List<ReportManageDataMantain> getGroupByTime(@Param("stationId") Integer stationId,
                                                 @Param("year")Integer year,
                                                 @Param("list") List<String> list,
                                                 @Param("dataTime")String dataTime,
                                                 @Param("orgId")Integer orgId);


}
