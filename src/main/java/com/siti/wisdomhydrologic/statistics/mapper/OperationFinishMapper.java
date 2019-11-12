package com.siti.wisdomhydrologic.statistics.mapper;

import com.siti.wisdomhydrologic.statistics.vo.OperationFinish;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zyw on 2019/11/1.
 */
public interface OperationFinishMapper {


    @Select( "<script>SELECT" +
            "result4," +
            "result5," +
            "result6," +
            "mal4," +
            "a.region_id," +
            "b.region_name " +
            "FROM" +
            "(" +
            "SELECT" +
            "count( * ) AS result4," +
            "rsb.create_time," +
            "crs.region_id," +
            "crs.region_name " +
            "FROM" +
            "report_station_broken rsb" +
            "LEFT JOIN config_river_station crs ON rsb.station_id = crs.station_id " +
            "WHERE" + "resolve_user_id IS NOT NULL " +
            "AND region_id IS NOT NULL " +
            "and SUBSTR(rsb.create_time,1,4) = #{year} "  +
            "and SUBSTR(rsb.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) "+
            "GROUP BY" +
            "crs.region_id " +
            ") a" +
            "LEFT JOIN (" +
            "SELECT" +
            "count( * ) AS result5," +
            "rscm.create_time," +
            "crs.region_id," +
            "crs.region_name " +
            "FROM" +
            "report_station_check_mantain rscm" +
            "LEFT JOIN config_river_station crs ON rscm.station_code = crs.station_id " +
            "WHERE" +
            "create_by IS NOT NULL " +
            "AND region_id IS NOT NULL " +
            "and SUBSTR(rscm.create_time,1,4) = #{year} "  +
            "and SUBSTR(rscm.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) "+
            "GROUP BY" +
            "crs.region_id " +
            ") b ON a.region_id = b.region_id" +
            "LEFT JOIN (" +
            "SELECT" +
            "count( * ) AS result6," +
            "rht.create_time," +
            "crs.region_id," +
            "crs.region_name " +
            "FROM" +
            "report_hyetometer_test rht" +
            "LEFT JOIN config_river_station crs ON rht.station_code = crs.station_id " +
            "WHERE" +
            "create_by IS NOT NULL " +
            "AND region_id IS NOT NULL " +
            "and SUBSTR(rht.create_time,1,4) = #{year} "  +
            "and SUBSTR(rht.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) "+
            "GROUP BY" +
            "crs.region_id " +
            ") c ON b.region_id = c.region_id left join (" +
            "select count(*) as mal4,crs.region_id,rsb.create_time," +
            "crs.region_name  " +
            "from report_station_broken rsb " +
            "left join config_river_station crs " +
            "ON rsb.station_id = crs.station_id " +
            "where mal_status = 1 " +
            "and DATEDIFF(CURDATE(),request_designating_time) &gt; 10 " +
            "AND region_id IS NOT NULL  " +
            "and SUBSTR(rht.create_time,1,4) = #{year} "  +
            "and SUBSTR(rht.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) "+
            "group by crs.region_id) d  ON c.region_id = d.region_id</script>")
    List<OperationFinish> getAll(@Param("year")Integer year,
                                 @Param("list")List<String> list);


    @Select("<script>SELECT " +
            "count( * ) AS result4," +
            "rsb.create_time," +
            "crs.region_id," +
            "crs.region_name " +
            "FROM " +
            "report_station_broken rsb " +
            "LEFT JOIN config_river_station crs ON rsb.station_id = crs.station_id " +
            "WHERE " +
            "resolve_user_id IS NOT NULL " +
            "AND region_id IS NOT NULL " +
            "and SUBSTR(rsb.create_time,1,4) = #{year} "  +
            "<if test=\'list!=null\'>and SUBSTR(rsb.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>)</if> "+
            "GROUP BY " +
            "crs.region_id </script>" )
    List<OperationFinish> getResult4(@Param("year")Integer year,
                                 @Param("list")List<String> list);

    @Select("<script>SELECT " +
            "count( * ) AS result5," +
            "crs.region_id," +
            "crs.region_name " +
            "FROM " +
            "report_station_check_mantain rscm " +
            "LEFT JOIN config_river_station crs ON rscm.station_code = crs.station_id " +
            "WHERE " +
            "create_by IS NOT NULL " +
            "AND region_id IS NOT NULL " +
            "and SUBSTR(rscm.create_time,1,4) = #{year} "  +
            "<if test=\'list!=null\'>and SUBSTR(rscm.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>) </if>"+
            "GROUP BY " +
            "crs.region_id </script>" )
    List<OperationFinish> getResult5(@Param("year")Integer year,
                                     @Param("list")List<String> list);


    @Select("<script>SELECT " +
            "count( * ) AS result6," +
            "rht.create_time," +
            "crs.region_id," +
            "crs.region_name " +
            "FROM " +
            "report_hyetometer_test rht " +
            "LEFT JOIN config_river_station crs ON rht.station_code = crs.station_id " +
            "WHERE " +
            "create_by IS NOT NULL " +
            "AND region_id IS NOT NULL " +
            "and SUBSTR(rht.create_time,1,4) = #{year} "  +
            "<if test=\'list!=null\'>and SUBSTR(rht.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>)</if> "+
            "GROUP BY " +
            "crs.region_id </script>" )
    List<OperationFinish> getResult6(@Param("year")Integer year,
                                     @Param("list")List<String> list);


    @Select("<script>select count(*) as mal4,crs.region_id,rsb.create_time," +
            "crs.region_name  " +
            "from report_station_broken rsb " +
            "left join config_river_station crs " +
            "ON rsb.station_id = crs.station_id " +
            "where mal_status = 1 " +
            "and DATEDIFF(CURDATE(),request_designating_time) &gt; 10 " +
            "AND region_id IS NOT NULL  " +
            "and SUBSTR(rsb.create_time,1,4) = #{year} "  +
            "<if test=\'list!=null\'>and SUBSTR(rsb.create_time,6,2) in " +
            "(<foreach collection=\"list\" item=\"item\" separator=\",\">#{item}</foreach>)</if> "+
            "group by crs.region_id</script>")
    List<OperationFinish> getMal(@Param("year")Integer year,
                                 @Param("list")List<String> list);


    @Select(" select region_id,region_name from config_river_station " +
            " where region_id is not null  " +
            " and sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) )" +
            " group by region_id")
    List<OperationFinish> getRegionGroup(@Param("orgId") Integer orgId);
}
