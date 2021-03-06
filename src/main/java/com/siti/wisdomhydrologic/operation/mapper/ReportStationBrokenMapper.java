package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.ReportManageApplicationBrokenVo;
import com.siti.wisdomhydrologic.mainpage.vo.StationMalFunction;
import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.vo.ManageMantainVo;
import com.siti.wisdomhydrologic.operation.vo.RealDeviceStatus;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zyw on 2019/7/31.
 */
public interface ReportStationBrokenMapper extends Mapper<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken>{

    @Select("<script>select * from report_station_broken a left join config_river_station b on a.station_id = b.station_id " +
            " where  b.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            "<if test=\"createDate!=null and createDate!='' \">and  DATE_FORMAT(a.create_time,'%Y-%m') = #{createDate} </if> " +
            "<if test=\"stationId!=null and stationId!=''\"> and a.station_id = #{stationId} </if>" +
            "<if test=\"status!=null and status!=''\"> and a.request_designating_status = #{status} </if>" +
            "<if test=\"display!=null and display!=''\"> and a.display_status = #{display} </if>" +
            "order by a.request_designating_status asc, a.create_time desc " +
            "</script>")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getAll(@Param("createDate") String createDate, @Param("stationId")String stationId, @Param("orgId") Integer orgId, @Param("status") Integer status, @Param("display")Integer display);

    @Select("<script>select " +
            "* " +
            "from report_station_broken a " +
            "left join config_river_station b " +
            "on a.station_id = b.station_id " +
            " where DATE_FORMAT(a.error_lastest_appear_time,'%Y-%m-%d') = #{createDate} " +
            "<if test=\"typeNameList!=null \">and SUBSTRING_INDEX(broken_according_id,'_', 1) in (" +
            "<foreach collection=\"typeNameList\" item=\"item\" separator=\",\">" +
            "#{item}" +
            "</foreach>)</if> " +
            "<if test=\"display!=null and display!=''\"> and a.display_status = #{display} </if>" +
            "order by a.create_time desc " +
            "</script>")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getAllDay(@Param("createDate") String createDate, @Param("display")Integer display, @Param("typeNameList") List<String> typeNameList);

    @Select("<script>select " +
            "* " +
            "from report_station_broken a " +
            "left join config_river_station b " +
            "on a.station_id = b.station_id " +
            " where DATE_FORMAT(a.error_lastest_appear_time,'%Y') = #{createDate} " +
            "<if test=\"typeNameList!=null \">and SUBSTRING_INDEX(broken_according_id,'_', 1) in (" +
            "<foreach collection=\"typeNameList\" item=\"item\" separator=\",\">" +
            "#{item}" +
            "</foreach>)</if> " +
            "<if test=\"display!=null and display!=''\"> and a.display_status = #{display} </if>" +
            "order by a.create_time desc " +
            "</script>")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getAllYear(@Param("createDate") String createDate, @Param("display")Integer display, @Param("typeNameList") List<String> typeNameList);


    @Select("<script>select " +
            "* " +
            "from report_station_broken a " +
            "left join config_river_station b " +
            "on a.station_id = b.station_id " +
            " where DATE_FORMAT(a.error_lastest_appear_time,'%Y-%m') = #{createDate} " +
            "<if test=\"typeNameList!=null \">and SUBSTRING_INDEX(broken_according_id,'_', 1) in (" +
            "<foreach collection=\"typeNameList\" item=\"item\" separator=\",\">" +
            "#{item}" +
            "</foreach>)</if> " +
            "<if test=\"display!=null and display!=''\"> and a.display_status = #{display} </if>" +
            "order by a.create_time desc " +
            "</script>")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getAllMonth(@Param("createDate") String createDate, @Param("display")Integer display, @Param("typeNameList") List<String> typeNameList);


    @Select("<script>select * from report_station_broken where request_designating_status  &lt; 4</script> ")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getNotResolve();


    @Select("<script>select * from report_station_broken" +
            "<if test=\"createDate!=null\">where create_time = #{createDate} </if> " +
            "<if test=\"stationId!=null\"> and  station_id = #{stationId}</if></script>")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getAllData();


    @Insert("<script>INSERT ignore INTO `report_station_broken`" +
            "(`report_id`,`station_id`, `station_name`, `broken_name`, `broken_according_id`, " +
            "`broken_according`, `broken_response_time`, `create_time`,`resolve_method`, `resolve_user_id`, `remark`,`error_lastest_appear_time`,`description`)" +
            " VALUES " +
            "(#{item.reportId},#{item.stationId},#{item.stationName}, #{item.brokenName}, #{item.brokenAccordingId}," +
            " #{item.brokenAccording}, #{item.brokenResponseTime}, #{item.createTime}," +
            "#{item.resolveMethod}, #{item.resolveUserId}, #{item.remark},#{item.errorLastestAppearTime},#{item.description})</script>")
    int insertDataMantain(@Param("item") com.siti.wisdomhydrologic.operation.entity.ReportStationBroken brokenList);

    @Select("<script>SELECT * FROM report_station_broken a left join config_river_station b" +
            " on a.station_id = b.station_id " +
            " <if test=\'regionId != null \'>where region_id = #{regionId}</if> " +
            " and display_status = 1 " +
            " order by  a.request_designating_status asc,a.create_time desc " +
            " LIMIT 10 </script> ")
    List<ReportManageApplicationBrokenVo> getLatest10(@Param("regionId") Integer regionId);

    @Select(" select region_name,region_id,request_designating_status,mal_status from config_river_station a left join report_station_broken b on a.station_id = b .station_id " +
            " where request_designating_status is not null " +
            " and display_status = 1 " +
            " and DATE_FORMAT( b.error_lastest_appear_time, '%Y%m%d' ) = DATE_FORMAT( CURDATE( ) , '%Y%m%d' )")
    List<StationMalFunction> getRegAndStatusListDay();


    @Select(" select region_name,region_id,request_designating_status,mal_status from config_river_station a left join report_station_broken b on a.station_id = b .station_id " +
            " where request_designating_status is not null  " +
            " and display_status = 1 " +
            " and DATE_FORMAT( b.error_lastest_appear_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )\n")
    List<StationMalFunction> getRegAndStatusListMonth();


    @Select(" select region_name,region_id,request_designating_status,mal_status from config_river_station a left join report_station_broken b on a.station_id = b .station_id " +
            " where request_designating_status is not null  " +
            " and display_status = 1 " +
            " and YEAR(b.error_lastest_appear_time)=YEAR(NOW());\n")
    List<StationMalFunction> getRegAndStatusListYear();

    @Select("<script>select a.sys_org,b.broken_name,b.create_time " +
            "from config_river_station a " +
            "left join report_station_broken b " +
            "on a.station_id = b .station_id " +
            "where b.broken_name is not null and " +
            "SUBSTR(b.create_time,1,7) = #{createTime} " +
            "and a.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) )  " +
            "and b.request_designating_status &lt; 4 </script>")
    List<ManageMantainVo> getLastMonthList(@Param("createTime") String createTime, @Param("orgId") int orgId);


    @Insert("INSERT INTO `report_station_broken` " +
            "(`station_id`, `station_name`, `broken_name`, " +
            "`broken_according_id`, `broken_according`, `create_time`, " +
            "`broken_response_time`, `broken_on_resolve_time`,`request_designating_time`, `broken_resolve_time`," +
            " `resolve_method`, `resolve_user_id`, `remark`, " +
            "`request_designating_status`, `broken_ask_to_resolve_time`," +
            " `broken_request_report_time`,`error_lastest_appear_time`,`builder_code`) VALUES " +
            "(#{data.stationId},#{data.stationName}, #{data.brokenName}," +
            " #{data.brokenAccordingId}, #{data.brokenAccording}, #{data.createTime}," +
            "#{data.brokenResponseTime},#{data.brokenOnResolveTime}, #{data.requestDesignatingTime}," +
            " #{data.brokenResolveTime}, #{data.resolveMethod}, #{data.resolveUserId}," +
            " #{data.remark}, #{data.requestDesignatingStatus},#{data.brokenAskToResolveTime}," +
            "#{data.brokenRequestReportTime},#{data.errorLastestAppearTime},#{data.builderCode})")
    int insert(@Param("data") com.siti.wisdomhydrologic.operation.entity.ReportStationBroken broken);


    @Update("UPDATE `report_station_broken` SET  `station_id` = #{data.stationId}, `station_name` = #{data.stationName}, " +
            "`broken_name` = #{data.brokenName}, `broken_according_id` = #{data.brokenAccordingId}, `broken_according` = #{data.brokenAccording}," +
            " `create_time` = #{data.createTime}, `broken_response_time` = #{data.brokenResponseTime}, " +
            "`resolve_method` =  #{data.resolveMethod}, `resolve_user_id` = #{data.resolveUserId}, `remark` = #{data.remark}, " +
            " `broken_ask_to_resolve_time` = #{data.brokenAskToResolveTime}, " +
            "`broken_request_report_time` = #{data.brokenRequestReportTime} WHERE report_id = #{data.reportId}")
    int update(@Param("data") com.siti.wisdomhydrologic.operation.entity.ReportStationBroken broken);

    @Update("UPDATE `report_station_broken` SET  "+
            "`request_designating_status` = #{data.requestDesignatingStatus} ," +
            "`broken_response_time` = #{data.brokenResponseTime}, " +
            "`request_designating_time` = #{data.requestDesignatingTime}, " +
            "`broken_resolve_time` = #{data.brokenResolveTime}," +
            "`broken_on_resolve_time` = #{data.brokenOnResolveTime}," +
            "`mal_status` = #{data.malStatus} " +
            " WHERE report_id = #{data.reportId}")
    int updateStatus(@Param("data") com.siti.wisdomhydrologic.operation.entity.ReportStationBroken broken);

    @Select("select * from report_station_broken where station_id = #{stationId} " +
            "and broken_according_id = #{accordingId} " +
            "and error_lastest_appear_time > #{last24HourTime} ")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getLastestData(@Param("stationId") int stationId, @Param("accordingId") String accordingId, @Param("last24HourTime") String last24HourTime);

    @Update("UPDATE `report_station_broken` SET `error_lastest_appear_time` = #{date}  " +
            "WHERE report_id = #{reportId}")
    void updateTime(@Param("reportId") Integer reportId, @Param("date") String date);

    @Select("<script>" +
            "select a.phone_num " +
            "from sys_user as a " +
            "LEFT JOIN sys_user_org as b on a.id=b.uid " +
            "LEFT JOIN config_region as c on c.manage_org_id=b.org_id " +
            "<if test =\" RegionId!=null \">where c.region_id=#{RegionId} </if> " +
            "and b.is_leader=1 " +
            "</script>")
    List<String> getNumberByRegionId(@Param("RegionId") Integer RegionId);

    @Select("select * from report_station_broken where report_id = #{reportId}")
    com.siti.wisdomhydrologic.operation.entity.ReportStationBroken getOne(@Param("reportId") Integer reportId);

    @Update("UPDATE `report_station_broken` SET `display_status` = 0  WHERE report_id = #{reportId}")
    int updateDisplayStatus(@Param("reportId") Integer reportId);

    /**
     *获取现在到5分钟前的开关门状态,查询时间段内有开关门记录，并记录
     * */
    @Select("SELECT drs.devid,mc,drs.last_upload_time,station_id FROM `device_real_status` drs left join config_dev_station cds  on drs.devid = cds.devid \n" +
            "where drs.is_online = 1 \n" +
            "and station_id is not null\n" +
            "and last_upload_time BETWEEN  date_add(now(), interval -5 minute) and now() " +
            "and station_id = #{stationId}")
    List<RealDeviceStatus> getRealDeviceStatus();

    //查询装有设备的测站
    @Select("SELECT drs.devid,mc,drs.last_upload_time,station_id FROM `device_real_status` drs left join config_dev_station cds  on drs.devid = cds.devid \n" +
            "where station_id = #{stationId}")
    List<RealDeviceStatus> getRealDeviceList(@Param("stationId")Integer stationId);


    /**
     * 只修改系统判定的故障异常
     * builder_code 0
     * */
    @Select("<script>select * from report_station_broken where report_id in (" +
            "<foreach collection=\"idList\" item=\"item\" separator=\",\">#{item}</foreach>) " +
            " and builder_code = 0 </script>")
    List<com.siti.wisdomhydrologic.operation.entity.ReportStationBroken> getById(@Param("idList") List<Integer> idList);

    @Delete("DELETE from report_station_broken where report_id in (" +
            "<foreach collection=\"idList\" item=\"item\" separator=\",\">#{item}</foreach>)")
    int deleteById(@Param("idList") List<Integer> idList);

    @Select("SELECT sensor_code FROM `abnormal_detail_current` adc " +
            "left join `report_station_broken` rsb  on adc.id = rsb.report_id " +
            "where id = #{reportId} and rsb.builder_code <> 1 ")
    Integer findModule(@Param("reportId")Integer reportId);
}
