package com.siti.wisdomhydrologic.fundconfig.mapper;

import com.siti.wisdomhydrologic.fundconfig.entity.ConfigSensorType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConfigSensorTypeMapper extends Mapper<ConfigSensorType> {
//-- ----------------------------
//        -- Table structure for `config_sensor_type`
//            -- ----------------------------
//    DROP TABLE IF EXISTS `config_sensor_type`;
//    CREATE TABLE `config_sensor_type` (
//            `sensor_type_id` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '自增ID',
//            `sensor_type_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '传感器类型名称',
//            `sensor_data_unit` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '传感器数值计量单位',
//            `other_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '别称',
//            `create_by` int(11) NOT NULL COMMENT '创建人',
//            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
//            `update_by` int(11) NOT NULL COMMENT '修改人',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
//    PRIMARY KEY (`sensor_type_id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

    @Select("select * from config_sensor_type")
    List<ConfigSensorType> getAll();   // 查询全部


    @Select("select sensor_type_name from config_sensor_type where sensor_type_id=#{sensorTypeId}")
    String getSensorTypeNameBySensorTypeID(@Param("sensorTypeId") String sensorTypeId);   // 查询全部
}
