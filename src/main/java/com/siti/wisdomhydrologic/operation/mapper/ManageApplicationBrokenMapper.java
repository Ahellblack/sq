package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.ReportManageApplicationBrokenVo;
import com.siti.wisdomhydrologic.mainpage.vo.StationMalFunction;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.vo.ManageMantainVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ManageApplicationBrokenMapper extends Mapper<ReportManageApplicationBroken>{

    @Select("<script>select * from report_station_broken" +
            "<if test=\"createDate!=null\">where DATE_FORMAT(create_time,'%Y-%m') = #{createDate}</if> </script>")
    List<ReportManageApplicationBroken> getAll(@Param("createDate") String createDate);


    @Select("<script>select * from report_station_broken" +
            "<if test=\"createDate!=null\">where create_time = #{createDate} and </if> " +
            "<if test=\"stationId!=null\"> station_id = #{stationId}</if></script>")
    List<ReportManageApplicationBroken> getAllData();


    @Delete("DELETE from report_station_broken where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);

    @Insert("<script>INSERT INTO `report_station_broken`" +
            "(`station_id`, `station_name`, `broken_name`, `broken_according_id`, " +
            "`broken_according`, `broken_response_time`, `create_time`,`resolve_method`, `resolve_user_id`, `remark`)" +
            " VALUES <foreach collection=\"brokenList\" item=\"item\" separator=\",\">" +
            "(#{item.stationId},#{item.stationName}, #{item.brokenName}, #{item.brokenAccordingId}," +
            " #{item.brokenAccording}, #{item.brokenResponseTime}, #{item.createTime},#{item.resolveMethod}, #{item.resolveUserId}, #{item.remark})</foreach></script>")
    int insertDataMantain(@Param("brokenList") List<ReportManageApplicationBroken> brokenList);

    @Select("SELECT * FROM report_station_broken a left join config_river_station b" +
            " on a.station_id = b.station_id GROUP BY a.station_id LIMIT 10")
    List<ReportManageApplicationBrokenVo> getLatest10();

    @Select(" select region_name,request_designating_status from config_river_station a left join report_station_broken b on a.station_id = b .station_id " +
            "where request_designating_status is not null and " +
            "to_days(b.create_time) = to_days(now())")
    List<StationMalFunction> getRegAndStatusListDay();
    @Select(" select region_name,request_designating_status from config_river_station a left join report_station_broken b on a.station_id = b .station_id " +
            "where request_designating_status is not null and " +
            "DATE_FORMAT( b.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )\n")
    List<StationMalFunction> getRegAndStatusListMonth();
    @Select(" select region_name,request_designating_status from config_river_station a left join report_station_broken b on a.station_id = b .station_id " +
            "where request_designating_status is not null and " +
            "YEAR(b.create_time)=YEAR(NOW());\n")
    List<StationMalFunction> getRegAndStatusListYear();

    @Select("select a.sys_org,b.broken_according_id,b.create_time from config_river_station a left join report_station_broken b on a.station_id = b .station_id " +
            "where request_designating_status is not null and " +
            "SUBSTR(b.create_time,1,7) = #{createTime} " +
            "and a.sys_org = #{sysOrg}")
    List<ManageMantainVo> getLastMonthList(@Param("createTime") String createTime, @Param("sysOrg") int sysOrg);
}
