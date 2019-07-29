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


    @Update("UPDATE `report_hyetometer_test` SET `station_code`=#{hyetometer.stationCode}, `station_name`={hyetometer.stationName},`manage_org_id`='2', `manage_org_name`='2', `library_date`='2', `device_type_code`='2', `device_code`='2', `start_time`='2', `end_time`='2', `time_duration`='2', `water_poll`='2', `water_poll_strength`='2', `water_display`='2', `error_value`='2', `create_by`='2' WHERE `report_id`=#{hyetometer.reportId}")
    int update(@Param("hyetometer") ReportHyetometerTest reportHyetometer);
}
