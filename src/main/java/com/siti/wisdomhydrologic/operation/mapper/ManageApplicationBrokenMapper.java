package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.ReportManageApplicationBrokenVo;
import com.siti.wisdomhydrologic.mainpage.vo.StationMalFunction;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.vo.ManageMantainVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ManageApplicationBrokenMapper extends Mapper<ReportManageApplicationBroken>{

    @Select("<script>select * from report_station_broken" +
            "<if test=\"createDate!=null\">where DATE_FORMAT(create_time,'%Y-%m') = #{createDate}</if> " +
            "<if test=\"stationName!=null\"> and station_name like '%${stationName}%'</if></script>")
    List<ReportManageApplicationBroken> getAll(@Param("createDate") String createDate,@Param("stationName")String stationName);


    @Select("<script>select * from report_station_broken" +
            "<if test=\"createDate!=null\">where create_time = #{createDate} </if> " +
            "<if test=\"stationId!=null\"> and  station_id = #{stationId}</if></script>")
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


    @Insert("INSERT INTO `report_station_broken` " +
            "(`station_id`, `station_name`, `broken_name`, " +
            "`broken_according_id`, `broken_according`, `create_time`, " +
            "`broken_response_time`, `request_designating_time`, `broken_resolve_time`," +
            " `resolve_method`, `resolve_user_id`, `remark`, " +
            "`request_designating_status`, `broken_ask_to_resolve_time`," +
            " `broken_request_report_time`) VALUES " +
            "(#{data.stationId},#{data.stationName}, #{data.brokenName}," +
            " #{data.brokenAccordingId}, #{data.brokenAccording}, #{data.createTime}," +
            "#{data.brokenResponseTime}, #{data.requestDesignatingTime}," +
            " #{data.brokenResolveTime}, #{data.resolveMethod}, #{data.resolveUserId}," +
            " #{data.remark}, #{data.requestDesignatingStatus},#{data.brokenAskToResolveTime},#{data.brokenrRequestReportTime})")
    int insert(@Param("data") ReportManageApplicationBroken broken);


    @Update("UPDATE `report_station_broken` SET  `station_id` = #{data.stationId}, `station_name` = #{data.stationName}, " +
            "`broken_name` = #{data.brokenName}, `broken_according_id` = #{data.brokenAccordingId}, `broken_according` = #{data.brokenAccording}," +
            " `create_time` = #{data.createTime}, `broken_response_time` = #{data.brokenResponseTime}, " +
            "`request_designating_time` = #{data.requestDesignatingTime}, `broken_resolve_time` = #{data.brokenResolveTime}, " +
            "`resolve_method` =  #{data.resolveMethod}, `resolve_user_id` = #{data.resolveUserId}, `remark` = #{data.remark}, " +
            "`request_designating_status` = #{data.requestDesignatingStatus}, `broken_ask_to_resolve_time` = #{data.brokenAskToResolveTime}, " +
            "`broken_request_report_time` = #{data.brokenrRequestReportTime} WHERE report_id = #{data.reportId}")
    int update(@Param("data") ReportManageApplicationBroken broken);


}
