package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
public interface ManageDataMantainMapper extends Mapper<ReportManageDataMantain> {
    @Select("<script>Select * from report_manage_data_mantain" +
            "<if test=\"createDate!=null\"> where DATE_FORMAT(create_time,'%Y-%m') = #{createDate} </if>" +
            "</script>")
    List<ReportManageDataMantain> getByCreateDate(@Param("createDate") String createDate);

    @Delete("delete from report_manage_data_mantain where report_id = #{reportId}")
    int deleteByReportId(@Param("reportId") Integer reportId);

    @Update("UPDATE `wisdomhydrologic`.`report_manage_data_mantain` " +
            "SET `station_code` = #{manage.stationCode}, `alter_date`= #{manage.alterDate}, `station_name`= #{manage.stationName}, `alter_sensor_type_id`=#{manage.alterSensorTypeId}, `alter_sensor_type_name`=#{manage.alterSensorTypeName}, `error_data_reason`=#{manage.errorDataReason}, `error_data_type`=#{manage.errorDataType}, `error_time_space` = #{manage.errorTimeSpace} ," +
            "`error_value` = #{manage.errorValue}, `confir_value` = #{manage.confirValue}, `error_unit` = #{manage.errorUnit}, `error_data_re_run` = #{manage.errorDataReRun}, " +
            "`miss_data_type` = #{manage.missDataType}, `miss_time_space` = #{manage.missTimeSpace}, `miss_data_re_run` =#{manage.missDataReRun} , `create_by` = #{manage.createBy}, " +
            "`manage_org_id` = #{manage.manageOrgId}, `manage_org_name` = #{manage.manageOrgName} WHERE `report_id` = #{manage.reportId}")
    int update(@Param("manage") ReportManageDataMantain reportManageDataMantain);

    @Insert("INSERT INTO `wisdomhydrologic`.`report_manage_data_mantain`(`station_code`, `alter_date`, `station_name`, `alter_sensor_type_id`, `alter_sensor_type_name`, `error_data_reason`, `error_data_type`, `error_time_space`, " +
            "`error_value`, `confir_value`, `error_unit`, `error_data_re_run`, `miss_data_type`, `miss_time_space`, " +
            "`miss_data_re_run`, `create_by`, `manage_org_id`, `manage_org_name`) " +
            "VALUES (#{manage.stationCode}, #{manage.alterDate}, #{manage.stationName},#{manage.alterSensorTypeId}, #{manage.alterSensorTypeName},#{manage.errorDataReason},#{manage.errorDataType},#{manage.errorTimeSpace}," +
            " #{manage.errorValue}, #{manage.confirValue}, #{manage.errorUnit}, #{manage.errorDataReRun}, #{manage.missDataType}, #{manage.missTimeSpace}," +
            " #{manage.missDataReRun}, #{manage.createBy}, #{manage.manageOrgId},#{manage.manageOrgName})")
    int insert(@Param("manage") ReportManageDataMantain reportManageDataMantain);

    @Update("UPDATE `wisdomhydrologic`.`report_manage_data_mantain` " +
            "SET `station_code`= #{stationcode} WHERE `report_id` = 24 ")
    int updateS(@Param("stationcode")String stationcode);
}
