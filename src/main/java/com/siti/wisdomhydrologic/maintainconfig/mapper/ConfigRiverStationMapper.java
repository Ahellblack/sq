package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
public interface ConfigRiverStationMapper extends Mapper<ConfigRiverStation> {

    @Select("select * from config_river_station")
    List<ConfigRiverStation> getAll();

    @Select("select * from config_river_station where sys_org<>0 group by sys_org ")
    List<ConfigRiverStation> getBySysOrg();

    @Select("select * from config_river_station where station_id = #{stationId}")
    ConfigRiverStation getAllByCode(@Param("stationId") Integer stationId);

    @Select("select * from config_river_station where station_name =#{stationname}")
    ConfigRiverStation getByName(@Param("stationname") String name);

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

    // 根据测站名称模糊进行查询
    @Select("select * from config_river_station where station_name like CONCAT(CONCAT('%', #{stationName}), '%')")
    List<ConfigRiverStation> getAllByStationName(@Param("stationName") String stationName);
}
