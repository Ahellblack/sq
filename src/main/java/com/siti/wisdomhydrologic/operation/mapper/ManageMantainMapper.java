package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ManageMantainMapper extends Mapper<ReportManageMantain> {
    @Select("<script>select * from report_manage_mantain " +
            "<if test=\"date!=null and date!=''\"> where mantain_month = #{date}</if></script>")
    List<ReportManageMantain> getByDate(@Param("date") String date);

    @Delete("delete from report_manage_mantain where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);

    @Select("select * from report_manage_mantain where " +
            " SUBSTR(mantain_month,1,7) = #{yearMonth}" +
            " and manage_org_id = #{sysOrg} ")
    List<ReportManageMantain> getDataByMonth(@Param("yearMonth") String yearMonth, @Param("sysOrg") int sysOrg);

    @Select("select * from report_manage_mantain where " +
            " SUBSTR(mantain_month,1,10) = #{yearMonthDay} " +
            " and mantain_hour = #{hour} " +
            " and manage_org_id = #{sysOrg} ")
    ReportManageMantain getOneData(@Param("yearMonthDay") String yearMonthDay,@Param("hour") String hour, @Param("sysOrg") int sysOrg);



    @Update("UPDATE `report_manage_mantain` SET " +
            "`temp_huimidity_exception` = #{entity.tempHuimidityException}, `server_time_exception` = #{entity.serverTimeException}, " +
            "`database_server_exception` = #{entity.databaseServerException}, `communicate_server_exception` = #{entity.communicateServerException}, " +
            "`application_server_exception` = #{entity.applicationServerException}, `web_server_exception` = #{entity.webServerException}, `work_station` = #{entity.workStation}," +
            " `changtong_rate_exception` = #{entity.changtongRateException}, `voltage_exception` = #{entity.voltageException}, " +
            "`voltage_process_line_exception` = #{entity.voltageProcessLineException}, `day_rain_report_exception` = #{entity.dayRainReportException}, " +
            "`rain_bar_exception` = #{entity.rainBarException}, `day_sea_level_report_exception` = #{entity.daySeaLevelReportException}, `sea_leve_process_line_exception` = #{entity.seaLeveProcessLineException}," +
            " `other_report_exception` = #{entity.otherReportException}, `remark` = #{entity.remark}, `create_by` = #{entity.createBy} WHERE `report_id` = #{entity.reportId}")
    int update(@Param("entity") ReportManageMantain entity);
}
