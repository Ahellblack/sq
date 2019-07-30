package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/26.
 */
public interface ReportHyetometerMapper extends Mapper<ReportHyetometerTest>{

    @Select("select * from report_hyetometer_test")
    List<ReportHyetometerTest> getAll();

    @Delete("delete from report_hyetometer_test where report_id = #{reportId}")
    int delByReportId(@Param("reportId")Integer reportId);

    @Update("UPDATE `report_hyetometer_test` SET `station_code`=#{hyetometer.stationCode}, " +
            "`station_name`=#{hyetometer.stationName},`manage_org_id`=#{hyetometer.manageOrgId}, " +
            "`manage_org_name`=#{hyetometer.manageOrgName}, `library_date`=#{hyetometer.libraryDate}," +
            " `device_type_code`=#{hyetometer.deviceTypeCode}, `device_code`=#{hyetometer.deviceCode}, " +
            "`start_time`=#{hyetometer.startTime}, `end_time`=#{hyetometer.endTime}, `time_duration`=#{hyetometer.timeDuration}, " +
            "`water_poll`=#{hyetometer.waterPoll}, `water_poll_strength`=#{hyetometer.waterPollStrength}, `water_display`=#{hyetometer.waterDisplay}, " +
            "`error_value`=#{hyetometer.errorValue}, `create_by`=#{hyetometer.createBy} WHERE `report_id`=#{hyetometer.reportId}")
    int update(@Param("hyetometer") ReportHyetometerTest reportHyetometer);
}
