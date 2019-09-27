package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
public interface ConfigRiverStationMapper extends Mapper<ConfigRiverStation> {

//    -- ----------------------------
//            -- Table structure for `config_river_station`
//            -- ----------------------------
//    DROP TABLE IF EXISTS `config_river_station`;
//    CREATE TABLE `config_river_station` (
//            `station_code` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '测站编号',
//            `station_telemetry_code` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '默认同station_code',
//            `station_id` int(15) DEFAULT NULL,
//  `station_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '测站名称',
//            `org_id` int(11) DEFAULT NULL COMMENT '所属镇、街道ID',
//            `org_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '所属镇、街道名称',
//            `river_id` int(11) DEFAULT NULL COMMENT '所属河道ID',
//            `river_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '所属河道名称',
//            `region_id` int(11) DEFAULT NULL COMMENT '测站所属片区ID',
//            `region_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '测站所属片区名称',
//            `station_wiski_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '测站码(wiski)',
//            `station_level` tinyint(4) DEFAULT '2' COMMENT '0 基本站；1国家站；2一般站',
//            `station_gaode_longitude` decimal(11,6) DEFAULT NULL COMMENT '测站经度',
//            `station_gaode_latitude` decimal(11,6) DEFAULT NULL COMMENT '测站纬度',
//            `is_sluice_gate` tinyint(1) DEFAULT '0' COMMENT '0 无闸门；1有闸门',
//            `station_address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '测站地址',
//            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '建站时间',
//            `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
//            `flow_rate` decimal(10,2) NOT NULL DEFAULT '95.00' COMMENT '测站畅通率',
//            `sys_org` int(11) NOT NULL COMMENT '所属分中心站。（区分测站所属组织）',
//    PRIMARY KEY (`station_code`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

    @Select("select * from config_river_station  " +
            "where FIND_IN_SET(region_id,(SELECT user_role from sys_user so WHERE id = #{uid}))")
    List<ConfigRiverStation> getAll(@Param("uid") Integer uid);

    @Select("select * from config_river_station ")
    List<ConfigRiverStation> getAllstation();



    /*@Select("")
    List<ConfigRiverStation> getCheckAll();*/

    @Select("select * from config_river_station where station_id = #{stationId}")
    ConfigRiverStation getAllByCode(@Param("stationId") Integer stationId);

    // 修改为模糊查询
    @Select("select * from config_river_station where station_name like CONCAT(CONCAT('%', #{stationName}), '%')")
    ConfigRiverStation getByName(@Param("stationName") String name);

    // 修改为模糊查询
    @Select("select * from config_river_station where station_name = #{stationName}")
    ConfigRiverStation getByAllName(@Param("stationName") String name);


    @Select("select * from config_river_station where sys_org<>0 group by sys_org ")
    List<ConfigRiverStation> getBySysOrg();

    @Insert("INSERT INTO `config_river_station` (`station_code`, `station_telemetry_code`, `station_id`, " +
            "`station_name`, `org_id`, `org_name`, `river_id`, `river_name`, `region_id`,`region_name`, " +
            "`station_wiski_code`, `station_level`, `station_gaode_longitude`,`station_gaode_latitude`, " +
            "`is_sluice_gate`, `station_address`, `create_time`, `flow_rate`, `sys_org` ) VALUES (" +
            "#{obj.stationCode}, " +
            "#{obj.stationTelemetryCode}, " +
            "#{obj.stationId}, " +
            "#{obj.stationName}, " +
            "#{obj.orgId}," +
            "#{obj.orgName}, " +
            "#{obj.riverId}, " +
            "#{obj.riverName}, " +
            "#{obj.regionId}, " +
            "#{obj.regionName}," +
            "#{obj.stationWiskiCode}, " +
            "#{obj.stationLevel}, " +
            "#{obj.stationGaodeLongitude}, " +
            "#{obj.stationGaodeLatitude}," +
            "#{obj.isSluiceGate}," +
            "#{obj.stationAddress}, " +
            "#{obj.createTime}, " +
            "#{obj.flowRate}, " +
            "#{obj.sysOrg});")
    int insert(@Param("obj") ConfigRiverStation configRiverStation);

    // 更新数据，部分字段不更新
    @Update("UPDATE `config_river_station` SET " +
            "`station_name`=#{obj.stationName}," +
            "`org_id`=#{obj.orgId}," +
            "`org_name`=#{obj.orgName}," +
            "`river_id`=#{obj.riverId}," +
            "`river_name`=#{obj.riverName}," +
            "`region_id`=#{obj.regionId}," +
            "`region_name`=#{obj.regionName}," +
            "`station_wiski_code`=#{obj.stationWiskiCode}," +
            "`station_level`=#{obj.stationLevel}," +
            "`station_gaode_longitude`=#{obj.stationGaodeLongitude}," +
            "`station_gaode_latitude`=#{obj.stationGaodeLatitude}," +
            "`is_sluice_gate`=#{obj.isSluiceGate}," +
            "`station_address`=#{obj.stationAddress}," +
            "`flow_rate`=#{obj.flowRate}, WHERE (`station_code`=#{obj.stationCode});")
    int update(@Param("obj") ConfigRiverStation configRiverStation);

    // 根据stationCode删除
    @Delete("DELETE FROM `config_river_station` WHERE (`station_code`=#{station_code})")
    int deleteByStationCode(@Param("station_code") String station_code);   // 根据stationCode删除

    // 根据stationId删除
    @Delete("DELETE FROM `config_river_station` WHERE (`station_id`=#{station_id})")
    int deleteByStationId(@Param("station_id") Integer station_id);

    // 根据 sysOrg 查询
    @Select("select * from config_river_station where sys_org =#{sysOrg}")
    List<ConfigRiverStation> getAllStationBySysOrg(@Param("sysOrg") Integer sysOrg);

    // 根据测站级别进行查询
    @Select("select * from config_river_station where station_level = #{stationLevel}")
    List<ConfigRiverStation> getAllByStationLevel(@Param("stationLevel") Integer stationLevel);

    // 根据测站级别进行查询
    @Select("select * from config_river_station where station_id = #{stationID}")
    ConfigRiverStation getByStationID(@Param("stationID") Integer stationID);

    @Select("select station_name from config_river_station where station_id = #{stationID}")
    String getStationNameByStationID(@Param("stationID") Integer stationID);

    // 根据测站名称模糊进行查询
    @Select("<script>select * from config_river_station " +
            "<if test=\'stationName != null\'>where station_name like CONCAT(CONCAT('%', #{stationName}), '%')</if></script>")
    List<ConfigRiverStation> getAllByStationName(@Param("stationName") String stationName);
}
