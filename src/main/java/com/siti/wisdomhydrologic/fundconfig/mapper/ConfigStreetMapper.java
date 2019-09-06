package com.siti.wisdomhydrologic.fundconfig.mapper;

import com.siti.wisdomhydrologic.fundconfig.entity.ConfigStreet;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConfigStreetMapper extends Mapper<ConfigStreet>  {

//        -- Table structure for `config_street`
//            -- ----------------------------
//    DROP TABLE IF EXISTS `config_street`;
//    CREATE TABLE `config_street` (
//            `street_id` int(20) NOT NULL COMMENT '街镇（行政区）ID',
//            `street_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
//            `street_polygeom` geometry DEFAULT NULL COMMENT '街镇边界',
//            `street_center_gaode_longitude` decimal(11,6) DEFAULT NULL COMMENT '街镇经度',
//            `street_center_gaode_latitude` decimal(11,6) DEFAULT NULL COMMENT '街镇纬度',
//            `create_by` int(11) DEFAULT NULL COMMENT '创建人',
//            `create_time` date DEFAULT NULL COMMENT '创建时间',
//            `update_by` int(11) DEFAULT NULL COMMENT '更新人',
//            `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
//            `sys_org_id` int(10) DEFAULT NULL COMMENT '街镇所属行政区ID',
//            `sys_org_name` varchar(20) DEFAULT NULL COMMENT '街镇所属行政区名称',
//    PRIMARY KEY (`street_id`) USING BTREE
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


    @Insert("INSERT INTO `config_street` (`street_id`, `street_name`, `street_polygeom`, " +
            "`street_center_gaode_longitude`, `street_center_gaode_latitude`, `create_by`, `create_time`, " +
            "`sys_org_id`, `sys_org_name`) VALUES (" +
            "#{obj.streetId}, " +
            "#{obj.streetName}, " +
            "#{obj.streetPolygeom}, " +
            "#{obj.streetCenterGaodeLongitude}, " +
            "#{obj.streetCenterGaodeLatitude}, " +
            "#{obj.createBy}, " +
            "#{obj.createTime}, " +
            "#{obj.sysOrgId}," +
            "#{obj.sysOrgName});")
    int insert(@Param("obj") ConfigStreet configStreet);   // 插入

    @Delete("DELETE FROM `config_street` WHERE (`street_id`=#{streetId})")
    int deleteByStreetID(@Param("streetId") Integer streetId);   // 删除

    @Update("UPDATE `config_street` SET `street_name`=#{obj.streetName}," +
            "`street_polygeom`=#{obj.streetPolygeom}," +
            "`street_center_gaode_longitude`=#{obj.streetCenterGaodeLongitude}," +
            "`street_center_gaode_latitude`=#{obj.streetCenterGaodeLatitude}  WHERE (`street_id`=#{obj.streetId});")
    int update(@Param("obj") ConfigStreet configStreet);   // 修改

    @Select("select * from config_street")
    List<ConfigStreet> getAll();   // 查询全部

    @Select("select * from config_street where street_id=#{streetId}")
    List<ConfigStreet> getByStreetID(@Param("streetId") Integer streetId);  // 根据主键查询

    @Select("select * from config_street where street_name like CONCAT(CONCAT('%', #{streetName}), '%')")
    List<ConfigStreet> getByStreetName(@Param("streetName") String streetName);  // 根据名称模糊查询

    @Select("select * from config_street where sys_org_id=#{sysOrgId}")
    List<ConfigStreet> getBySysOrg(@Param("sysOrgId") Integer sysOrgId);  // 根据行政区查询
}
