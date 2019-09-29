package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.operation.vo.ReportManageDataMantainVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
public interface ManageDataMantainMapper extends Mapper<ReportManageDataMantain> {
    @Select("<script>Select * from report_manage_data_mantain a " +
            "left join config_abnormal_dictionary b on a.broken_according_id = b.broken_according_id " +
            "left join config_river_station c on a.station_name = c.station_name " +
            " where b.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            "<if test=\"createDate!=null\"> and DATE_FORMAT(a.create_time,'%Y-%m') = #{createDate} </if>" +
            "<if test=\"stationName!=null\"> and a.station_name like '%${stationName}%'  </if>"+
            "<if test=\"alterType!=null\"> and a.alter_sensor_type_name like '%${alterType}%' </if>" +
            "order by a.create_time desc" +
            "</script>")
    List<ReportManageDataMantain> getByCreateDate(@Param("stationName") String stationName, @Param("alterType") String alterType, @Param("createDate") String createDate,@Param("orgId") Integer orgId);

    @Select("<script>Select * from report_manage_data_mantain a left join config_river_station c on a.station_name = c.station_name " +
            " where FIND_IN_SET(region_id,(SELECT user_role from sys_user so WHERE id = #{uid})) " +
            "<if test=\"createDate!=null\">and  DATE_FORMAT(a.create_time,'%Y-%m') = #{createDate} </if>" +
            "<if test=\"stationName!=null\"> and a.station_name like '%${stationName}%'  </if>"+
            "<if test=\"alterType!=null\"> and a.alter_sensor_type_name like '%${alterType}%' </if>"+
            "</script>")
    List<ReportManageDataMantainVo> getVoByCreateDate(@Param("stationName") String stationName, @Param("alterType") String alterType, @Param("createDate") String createDate,@Param("uid") Integer uid);


    @Delete("delete from report_manage_data_mantain where report_id = #{reportId}")
    int deleteByReportId(@Param("reportId") Integer reportId);

    @Update("UPDATE `report_manage_data_mantain` " +
            "SET `station_code` = #{manage.stationCode}, `alter_date`= #{manage.alterDate}, `station_name`= #{manage.stationName}, `alter_sensor_type_id`=#{manage.alterSensorTypeId}, `alter_sensor_type_name`=#{manage.alterSensorTypeName}, `error_data_reason`=#{manage.errorDataReason}, `error_data_type`=#{manage.errorDataType}, `error_time_space` = #{manage.errorTimeSpace} ," +
            "`error_value` = #{manage.errorValue}, `confir_value` = #{manage.confirValue}, `error_unit` = #{manage.errorUnit}, `error_data_re_run` = #{manage.errorDataReRun}, " +
            "`miss_data_type` = #{manage.missDataType}, `miss_time_space` = #{manage.missTimeSpace}, `miss_data_re_run` =#{manage.missDataReRun} , `create_by` = #{manage.createBy}, " +
            "`manage_org_id` = #{manage.manageOrgId}, `manage_org_name` = #{manage.manageOrgName} WHERE `report_id` = #{manage.reportId}")
    int update(@Param("manage") ReportManageDataMantain reportManageDataMantain);

   /* @Insert("INSERT INTO `report_manage_data_mantain`(`station_code`, `alter_date`, `station_name`, `alter_sensor_type_id`, `alter_sensor_type_name`, `error_data_reason`, `error_data_type`, `error_time_space`, " +
            "`error_value`, `confir_value`, `error_unit`, `error_data_re_run`, `miss_data_type`, `miss_time_space`, " +
            "`miss_data_re_run`, `create_by`, `manage_org_id`, `manage_org_name`,`broken_according_id`) " +
            "VALUES (#{manage.stationCode}, #{manage.alterDate}, #{manage.stationName},#{manage.alterSensorTypeId}, #{manage.alterSensorTypeName},#{manage.errorDataReason},#{manage.errorDataType},#{manage.errorTimeSpace}," +
            " #{manage.errorValue}, #{manage.confirValue}, #{manage.errorUnit}, #{manage.errorDataReRun}, #{manage.missDataType}, #{manage.missTimeSpace}," +
            " #{manage.missDataReRun}, #{manage.createBy}, #{manage.manageOrgId},#{manage.manageOrgName},#{manage.brokenAccordingId})")
    int insert(@Param("manage") ReportManageDataMantain reportManageDataMantain);

*/

    @Update("UPDATE `report_manage_data_mantain` " +
            "SET  `error_time_space` = #{manage.errorTimeSpace} ," +
            " `error_lastest_appear_time` = #{manage.errorLastestAppearTime}" +
            "  WHERE `report_id` = #{manage.reportId}")
    int updateTime(@Param("manage") ReportManageDataMantainVo reportManageDataMantain);



    @Insert("<script>INSERT INTO `report_manage_data_mantain`(`station_code`, `alter_date`, `station_name`, `alter_sensor_type_id`, `alter_sensor_type_name`, `error_data_reason`, `error_data_type`, `error_time_space`, " +
            "`error_value`, `confir_value`, `error_unit`, `error_data_re_run`, `miss_data_type`, `miss_time_space`, " +
            "`miss_data_re_run`, `create_by`,`create_time`, `manage_org_id`, `manage_org_name`,`broken_according_id`,`error_lastest_appear_time`) " +
            "VALUES <foreach collection=\"list\" item=\"item\" separator=\",\">" +
            "(#{item.stationCode}, #{item.alterDate}, #{item.stationName},#{item.alterSensorTypeId}, #{item.alterSensorTypeName},#{item.errorDataReason},#{item.errorDataType},#{item.errorTimeSpace}," +
            " #{item.errorValue}, #{item.confirValue}, #{item.errorUnit}, #{item.errorDataReRun}, #{item.missDataType}, #{item.missTimeSpace}," +
            " #{item.missDataReRun}, #{item.createBy},#{item.createTime}, #{item.manageOrgId},#{item.manageOrgName},#{item.brokenAccordingId},#{item.errorLastestAppearTime})</foreach></script>")
    int insertAbnormal(@Param("list") List<ReportManageDataMantainVo> all);


    @Select("select * from report_manage_data_mantain where station_code = #{stationCode} and create_time = #{last5MinuteTime}")
    List<ReportManageDataMantain> getLastOne(@Param("stationCode") Integer stationCode, @Param("last5MinuteTime") String last5MinuteTime);

    @Select("select * from report_manage_data_mantain where station_code = #{stationId} and error_lastest_appear_time = #{last5MinuteTime} ")
    ReportManageDataMantain getLastestData(@Param("stationId") int stationId, @Param("last5MinuteTime") String last5MinuteTime);


}
