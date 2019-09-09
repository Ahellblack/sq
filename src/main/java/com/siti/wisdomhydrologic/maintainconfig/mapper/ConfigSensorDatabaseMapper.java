package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorDatabase;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
public interface ConfigSensorDatabaseMapper extends Mapper<ConfigSensorDatabase>{

    @Select("SELECT * FROM config_sensor_database")
    List<ConfigSensorDatabase> getAll();


    // 根据资产ID查询
    @Select("SELECT * FROM `config_sensor_database` WHERE property_code=#{propertyCode}")
    ConfigSensorDatabase findAllByPropertyCode(@Param("propertyCode") Long propertyCode);

    // 根据传感器编号查询
    @Select("SELECT * FROM `config_sensor_database` WHERE sensor_code=#{sensorCode}")
    List<ConfigSensorDatabase> findAllBySensorCode(@Param("sensorCode") String sensorCode);

    // getSensorTypeId
    @Select("SELECT * FROM `config_sensor_database` WHERE sensor_type_id=#{sensorTypeId}")
    List<ConfigSensorDatabase> findAllByTypeID(@Param("sensorTypeId") String sensorTypeId);

    // 根据资产类型名模糊查询
    @Select("SELECT *  FROM `config_sensor_database` WHERE  sensor_type_name like CONCAT(CONCAT('%', #{sensorTypeName}), '%')")
    List<ConfigSensorDatabase> findAllByTypeName(@Param("sensorTypeName") String sensorTypeName);

    // 根据所属测站ID查询
    @Select("SELECT * FROM `config_sensor_database` WHERE manage_org_id=#{manageOrgId}")
    List<ConfigSensorDatabase> findAllByOrgID(@Param("manageOrgId") Integer manageOrgId);

    // 根据所属测站名称模糊查询
    @Select("SELECT *  FROM `config_sensor_database` WHERE  manage_org_name like CONCAT(CONCAT('%', #{manageOrgName}), '%')")
    List<ConfigSensorDatabase> findAllByOrgName(@Param("manageOrgName") String manageOrgName);

    // 根据资产使用状态进行查询
    @Select("SELECT * FROM `config_sensor_database` WHERE  sensor_use_status =#{sensorUseStatus}")
    List<ConfigSensorDatabase> findAllByUseStatus(@Param("sensorUseStatus") String sensorUseStatus);

    @Insert("INSERT INTO `config_sensor_database` (`property_code`, `sensor_code`, `sensor_type_id`, " +
            "`sensor_type_name`, `sensor_use_status`, `sensor_location`, `create_by`, `create_time`, " +
            "`manage_org_id`,`manage_org_name`, `sensor_model_type`, `subordinate_company` ) VALUES (" +
            "#{obj.propertyCode}, " +
            "#{obj.sensorCode}, " +
            "#{obj.sensorTypeId}, " +
            "#{obj.sensorTypeName}, " +
            "#{obj.sensorUseStatus}," +
            "#{obj.sensorLocation}, " +
            "#{obj.createBy}, " +
            "#{obj.createTime}, " +
            "#{obj.manageOrgId}, " +
            "#{obj.manageOrgName}," +
            "#{obj.sensorModelType}," +
            "#{obj.subordinateCompany});")
    int insert(@Param("obj") ConfigSensorDatabase configSensorDatabase);

    @Update("UPDATE `config_sensor_database` SET " +
            "`sensor_code`=#{obj.sensorCode}," +
            "`sensor_type_id`=#{obj.sensorTypeId}," +
            "`sensor_type_name`=#{obj.sensorTypeName}," +
            "`sensor_use_status`=#{obj.sensorUseStatus}," +
            "`sensor_location`=#{obj.sensorLocation}," +
            "`update_by`=#{obj.updateBy}," +
            "`update_time`=#{obj.updateTime}," +
            "`manage_org_id`=#{obj.manageOrgId}," +
            "`manage_org_name`=#{obj.manageOrgName}," +
            "`sensor_model_type`=#{obj.sensorModelType}," +
            "`subordinate_company`=#{obj.subordinateCompany} WHERE `property_code`=#{obj.propertyCode};")
    int update(@Param("obj") ConfigSensorDatabase configSensorDatabase);   // 修改

    // 根据 propertyCode 删除
    @Delete("DELETE FROM `config_sensor_database` WHERE `property_code`=#{propertyCode}")
    int deleteByPropertyCode(@Param("propertyCode") Long propertyCode);


    @Select("select sensor_type_name from config_sensor_database GROUP BY sensor_type_name")
    List<String> getSensorTypeNameList();

    @Select("<script>select * from config_sensor_database " +
            "where sensor_type_name = #{sensorTypeName} " +
            "<if test=\"stationId!=null\"> and station_id = #{stationId} </if>" +
            " GROUP BY sensor_model_type</script>")
    List<ConfigSensorDatabase> getSensorTypeId(@Param("sensorTypeName") String sensorTypeName,@Param("StationId")Integer stationId);
}
