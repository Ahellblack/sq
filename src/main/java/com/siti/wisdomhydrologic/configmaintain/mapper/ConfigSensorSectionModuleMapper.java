package com.siti.wisdomhydrologic.configmaintain.mapper;


import com.siti.wisdomhydrologic.configmaintain.entity.ConfigSensorSectionModule;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/15.
 */
public interface ConfigSensorSectionModuleMapper extends Mapper<ConfigSensorSectionModule> {

    @Select("Select * from config_sensor_section_module ")
    List<ConfigSensorSectionModule> getStation();

    @Select("<script>Select * from config_sensor_section_module cssm " +
            "left join config_river_station crs " +
            "on cssm.station_code = crs.station_id  " +
            "where crs.sys_org in " +
            "( SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            "<if test= \'stationCode != null\'> and crs.station_id = #{stationCode}</if></script>")
    List<ConfigSensorSectionModule> getStationByCode(@Param("orgId")Integer orgId,@Param("stationCode")Integer stationCode);


    @Select("Select section_code from config_sensor_section_module")
    List<String> getNidStation();

    // 根据测站ID查询下属所有元素
    @Select("Select * from config_sensor_section_module where station_code =#{station_code}")
    List<ConfigSensorSectionModule> findModuleByStationCode(@Param("station_code") Integer station_code);

    // 根据测站名称模糊查询下属所有元素
    @Select("Select * from config_sensor_section_module where station_name like CONCAT(CONCAT('%', #{station_name}), '%')")
    List<ConfigSensorSectionModule> findModuleByStationName(@Param("station_name") String station_name);

    // 根据测站编号列表查询所有元素
    @Select("select * from \n" +
            "(select station_id,sys_org from config_river_station where sys_org=#{sysOrg}) as a JOIN\n" +
            "(select * from config_sensor_section_module) as b \n" +
            "on a.station_id = b.station_code")
    List<ConfigSensorSectionModule> findModuleBySysOrg(@Param("sysOrg") Integer sysOrg);


    @Insert("INSERT INTO `config_sensor_section_module` (`section_code`, `section_name`, `sensor_code`, " +
            "`sensor_name`, `station_code`, `station_name`, `section_status`, `create_time` ) VALUES (" +
            "#{obj.sectionCode}, " +
            "#{obj.sectionName}, " +
            "#{obj.sensorCode}, " +
            "#{obj.sensorName}, " +
            "#{obj.stationCode}," +
            "#{obj.stationName}, " +
            "#{obj.sectionStatus}, " +
            "#{obj.createTime});")
    int insert(@Param("obj") ConfigSensorSectionModule configSensorSectionModule);

    @Update("UPDATE `config_sensor_section_module` SET " +
            "`section_name`=#{obj.sectionName}," +
            "`sensor_code`=#{obj.sensorCode}," +
            "`sensor_name`=#{obj.sensorName}," +
            "`station_code`=#{obj.stationCode}," +
            "`station_name`=#{obj.stationName}," +
            "`section_status`=#{obj.sectionStatus} WHERE `section_code`=#{obj.sectionCode};")
    int update(@Param("obj") ConfigSensorSectionModule configSensorSectionModule);   // 修改

    // 根据 sectionCode 删除
    @Delete("DELETE FROM `config_sensor_section_module` WHERE `section_code`=#{sectionCode}")
    int deleteBySectionCode(@Param("sectionCode") Integer sectionCode);

    // 根据测站ID查询下属所有元素
    @Select("Select * from config_sensor_section_module where section_code =#{sectionCode}")
    ConfigSensorSectionModule findModuleBySectionCode(@Param("sectionCode") Integer sectionCode);

    // 根据测站名称模糊查询下属所有元素
    @Select("Select * from config_sensor_section_module where section_name = #{station_name}")
    ConfigSensorSectionModule findOneModuleBySectionName(@Param("station_name") String station_name);

}
