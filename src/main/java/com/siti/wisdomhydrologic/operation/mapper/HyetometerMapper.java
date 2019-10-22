package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/26.
 */
public interface HyetometerMapper extends Mapper<ReportHyetometerTest>{

    @Select("<script>select * from report_hyetometer_test a " +
            " left join config_river_station b on a.station_code = b.station_id " +
            " where b.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            "<if test=\"createTime!=null and createTime!=''\"> and DATE_FORMAT(a.create_time,'%Y-%m') = #{createTime}</if>" +
            "<if test=\"stationId!=null and stationId!=''\"> and a.station_code = #{stationId} </if>" +
            "order by a.create_time desc" +
            " </script>")
    List<ReportHyetometerTest> getAll(@Param("createTime") String createTime,@Param("stationId")String stationId,@Param("orgId")Integer orgId);

    @Delete("delete from report_hyetometer_test where report_id = #{reportId}")
    int delByReportId(@Param("reportId") Integer reportId);

    @Update("UPDATE `report_hyetometer_test` SET `station_code`=#{hyetometer.stationCode}, " +
            "`station_name`=#{hyetometer.stationName},`manage_org_id`=#{hyetometer.manageOrgId}, " +
            "`manage_org_name`=#{hyetometer.manageOrgName}, `library_date`=#{hyetometer.libraryDate}," +
            " `device_type_code`=#{hyetometer.deviceTypeCode}, `device_code`=#{hyetometer.deviceCode}, " +
            "`start_time`=#{hyetometer.startTime}, `end_time`=#{hyetometer.endTime}, `time_duration`=#{hyetometer.timeDuration}, " +
            "`water_poll`=#{hyetometer.waterPoll}, `water_poll_strength`=#{hyetometer.waterPollStrength}, `water_display`=#{hyetometer.waterDisplay}, " +
            "`error_value`=#{hyetometer.errorValue}, `create_by`=#{hyetometer.createBy} WHERE `report_id`=#{hyetometer.reportId}")
    int update(@Param("hyetometer") ReportHyetometerTest reportHyetometer);
}
