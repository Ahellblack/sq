package com.siti.wisdomhydrologic.configfund.mapper;

import com.siti.wisdomhydrologic.configfund.entity.ConfigRiver;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ConfigRiverMapper extends Mapper<ConfigRiver> {

//            -- Table structure for `config_river`
//            -- ----------------------------
//    DROP TABLE IF EXISTS `config_river`;
//    CREATE TABLE `config_river` (
//            `river_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '河道ID',
//            `river_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '河道名称',
//            `org_ids` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '经流镇、街道ID英文半角逗号分隔',
//            `org_names` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '经流镇、街道名称：英文半角逗号分隔',
//            `river_gaode_polygeom` geometry NOT NULL COMMENT '河道边界',
//            `river_center_gaode_longitude` decimal(20,5) NOT NULL COMMENT '河道中心经度',
//            `river_center_gaode_latitude` decimal(20,5) NOT NULL COMMENT '河道中心纬度',
//            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
//            `create_by` int(11) NOT NULL COMMENT '创建人',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
//            `update_by` int(11) NOT NULL COMMENT '更新人',
//    PRIMARY KEY (`river_id`)
//) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


    @Insert("INSERT INTO `config_river` (`river_id`, `river_name`, `river_gaode_polygeom`, " +
            "`river_center_gaode_longitude`, `river_center_gaode_latitude`, `create_by`, `create_time`, " +
            "`org_ids`, `org_names`) VALUES (" +
            "#{obj.riverId}, " +
            "#{obj.riverName}, " +
            "#{obj.riverGaodePolygeom}, " +
            "#{obj.riverCenterGaodeLongitude}, " +
            "#{obj.riverCenterGaodeLatitude}, " +
            "#{obj.createBy}, " +
            "#{obj.createTime}, " +
            "#{obj.orgIds}," +
            "#{obj.orgNames});")
    int insert(@Param("obj") ConfigRiver configRiver);   // 插入

    @Delete("DELETE FROM `config_river` WHERE (`river_id`=#{riverId})")
    int deleteByStreetID(@Param("riverId") Integer riverId);   // 删除

    @Update("UPDATE `config_river` SET `river_name`=#{obj.riverName}," +
            "`river_gaode_polygeom`=#{obj.riverGaodePolygeom}," +
            "`river_center_gaode_longitude`=#{obj.riverCenterGaodeLongitude}," +
            "`river_center_gaode_latitude`=#{obj.riverCenterGaodeLatitude}  WHERE (`river_id`=#{obj.riverId});")
    int update(@Param("obj") ConfigRiver configRiver);   // 修改

    @Select("select * from config_river")
    List<ConfigRiver> getAll();   // 查询全部

    @Select("select * from config_river where river_id=#{riverId}")
    List<ConfigRiver> getByRiverID(@Param("riverId") Integer riverId);  // 根据主键查询

    @Select("select * from config_river where river_name like CONCAT(CONCAT('%', #{riverName}), '%')")
    List<ConfigRiver> getByRiverName(@Param("riverName") String riverName);  // 根据名称模糊查询

}
