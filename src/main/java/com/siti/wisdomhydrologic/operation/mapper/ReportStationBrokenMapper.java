package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ReportStationBrokenMapper extends Mapper<ReportStationBroken>{
    @Delete("delete from report_manage_application_broken where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);

    @Select("select * from report_manage_application_broken")
    List<ReportStationBroken> getAll();

    @Select("select * from report_manage_application_broken " +
            "where SUBSTR(broken_happen_time,1,7) = '2019-07'")
    List<ReportStationBroken> getLastMonthAll(@Param("brokenTime") String brokenTime);

    @Insert("INSERT INTO `report_manage_application_broken`" +
            "( `station_code`, `station_name`, `broken_happen_time`, " +
            "`broken_situation`, `broken_resolve_create_time`, " +
            "`resolve_method`, `resolve_user_ids`, `remark`, " +
            "`create_by`, `manage_org_id`, `manage_org_name`, " +
            "`broken_resolve_time`, `broken_response_time`, `create_time`) " +
            "VALUES (#{Broken.stationCode}, #{Broken.stationName}, #{Broken.brokenHappenTime}," +
            " #{Broken.brokenSituation},#{Broken.brokenResolveCreateTime}," +
            " #{Broken.resolveMethod}, #{Broken.resolveUserIds}, #{Broken.remark}," +
            " #{Broken.createBy}, #{Broken.manageOrgId}, #{Broken.manageOrgName}," +
            " #{Broken.brokenResolveTime}, #{Broken.brokenResponseTime}, " +
            "#{Broken.createTime})" )
    int insertData(@Param("Broken") ReportStationBroken reportStationBroken);

    @Update("UPDATE report_manage_application_broken set `station_code` =#{Broken.stationCode},`station_name`=#{Broken.stationName}," +
            "`broken_happen_time` =#{Broken.brokenHappenTime}, `broken_situation` =#{Broken.brokenSituation},`broken_resolve_create_time`=#{Broken.brokenResolveCreateTime}," +
            "`resolve_method` =  #{Broken.resolveMethod},`resolve_user_ids` = #{Broken.resolveUserIds}," +
            "`remark`=#{Broken.remark}, `create_by`=#{Broken.createBy}, `manage_org_id`=#{Broken.manageOrgId}," +
            " `manage_org_name`=#{Broken.manageOrgName}, `broken_resolve_time`=#{Broken.brokenResolveTime}, `broken_response_time`= #{Broken.brokenResponseTime}, " +
            "`create_time`=#{Broken.createTime} WHERE `report_id`=#{Broken.reportId}")
    int updateData(@Param("Broken") ReportStationBroken reportStationBroken);
}

