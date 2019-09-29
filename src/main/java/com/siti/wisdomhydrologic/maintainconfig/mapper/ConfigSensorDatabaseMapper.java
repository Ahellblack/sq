package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorDatabase;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
public interface ConfigSensorDatabaseMapper extends Mapper<ConfigSensorDatabase>{

//    CREATE TABLE `config_sensor_database` (
//            `property_code` varchar(50) NOT NULL COMMENT '毫秒时间戳',
//            `sensor_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '传感器编号',
//            `sensor_type_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '传感器类型ID',
//            `sensor_type_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '传感器类型名称',
//            `manage_org_id` int(11) DEFAULT NULL COMMENT '传感器资产所属测站',
//            `manage_org_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '传感器资产所属测站名称',
//            `sensor_use_status` enum('3','2','1','0') COLLATE utf8_bin DEFAULT '0' COMMENT '传感器目前状态，0为备品，1为已安装，2为维修中，3为已报废',
//            `sensor_model_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '传感器型号',
//            `subordinate_company` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '传感器所属公司',
//            `sensor_location` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '资产目前所处位置',
//            `create_by` int(11) DEFAULT NULL COMMENT '入库人',
//            `create_time` timestamp NULL DEFAULT NULL COMMENT '传感器入库时间',
//            `update_by` int(11) DEFAULT NULL COMMENT '修改人',
//            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
//    PRIMARY KEY (`property_code`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

    @Select("SELECT * FROM config_sensor_database")
    List<ConfigSensorDatabase> getAll();

    // 关联用户表查询出修改人与更新人名称
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id")
    List<ConfigSensorDatabase> getAllAndName();

    // 根据资产ID查询
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id WHERE a.property_code=#{propertyCode}")
    ConfigSensorDatabase findByPropertyCode(@Param("propertyCode") String propertyCode);


    // 根据资产ID查询
    @Select("SELECT * FROM `config_sensor_database` WHERE property_code=#{propertyCode}")
    ConfigSensorDatabase findAllByPropertyCode(@Param("propertyCode") String propertyCode);

    // 根据传感器编号查询
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id WHERE a.sensor_code=#{sensorCode}")
    List<ConfigSensorDatabase> findAllBySensorCode(@Param("sensorCode") String sensorCode);

    // 根据资产类型ID查询
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id WHERE a.sensor_type_id=#{sensorTypeId}")
    List<ConfigSensorDatabase> findAllByTypeID(@Param("sensorTypeId") String sensorTypeId);

    // 根据资产类型名模糊查询
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id WHERE  a.sensor_type_name like CONCAT(CONCAT('%', #{sensorTypeName}), '%')")
    List<ConfigSensorDatabase> findAllByTypeName(@Param("sensorTypeName") String sensorTypeName);

    // 根据所属测站ID查询
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id WHERE a.manage_org_id=#{manageOrgId}")
    List<ConfigSensorDatabase> findAllByOrgID(@Param("manageOrgId") Integer manageOrgId);

    // 根据所属测站名称模糊查询
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id WHERE  a.manage_org_name like CONCAT(CONCAT('%', #{manageOrgName}), '%')")
    List<ConfigSensorDatabase> findAllByOrgName(@Param("manageOrgName") String manageOrgName);

    // 根据资产使用状态进行查询
    @Select("select a.*,b.real_name as create_by_name,c.real_name as update_by_name from config_sensor_database as a \n" +
            "LEFT JOIN sys_user as b  on a.create_by=b.id\n" +
            "LEFT JOIN sys_user as c  on a.update_by=c.id WHERE  a.sensor_use_status =#{sensorUseStatus}")
    List<ConfigSensorDatabase> findAllByUseStatus(@Param("sensorUseStatus") String sensorUseStatus);

    @Insert("INSERT INTO `config_sensor_database` (`property_code`, `sensor_code`, `sensor_type_id`, " +
            "`sensor_type_name`, `sensor_use_status`, `sensor_location`, `create_by`, `create_time`, " +
            "`manage_org_id`,`manage_org_name`, `sensor_model_type`, `subordinate_company`,`remarks` ) VALUES (" +
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
            "#{obj.subordinateCompany}," +
            "#{obj.remarks});")
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
            "`subordinate_company`=#{obj.subordinateCompany}," +
            "`remarks`=#{obj.remarks} WHERE `property_code`=#{obj.propertyCode};")
    int update(@Param("obj") ConfigSensorDatabase configSensorDatabase);   // 修改

    // 根据 propertyCode 删除
    @Delete("DELETE FROM `config_sensor_database` WHERE `property_code`=#{propertyCode}")
    int deleteByPropertyCode(@Param("propertyCode") String propertyCode);

    /**
     * z
     * */
    @Select("<script>select sensor_type_name from config_sensor_database " +
            "<if test=\"stationName!=null\">where manage_org_name =#{stationName} </if> " +
            "and sensor_use_status = 1 " +
            "GROUP BY sensor_type_name</script> ")
    List<String> getSensorTypeNameList(@Param("stationName") String stationName);

    /**
     * z
     * */
    @Select("<script>select manage_org_name from config_sensor_database a " +
            " left join config_river_station b on a.manage_org_id = b.station_id " +
            " where  b.sys_org in ( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            " and a.manage_org_name is not null " +
            " GROUP BY a.manage_org_name </script>")
    List<String> getStationName(@Param("uid") Integer uid);

    /**
     * z
     * */
    @Select("select * from config_sensor_database " +
            "where sensor_type_name =#{originDeviceName} " +
            "and manage_org_name =#{manageOrgName} and sensor_use_status = 1 " )
    List<ConfigSensorDatabase> getData(@Param("originDeviceName") String originDeviceName,
                                       @Param("manageOrgName")String manageOrgName);

    /**
     * z
     * */
    @Select("select * from config_sensor_database " +
            "where sensor_use_status = '0'" )//sensor_type_name =#{senserTypeName} and
    List<ConfigSensorDatabase> getNewData(/*@Param("sensorTypeName") String sensorTypeName*/);

}
