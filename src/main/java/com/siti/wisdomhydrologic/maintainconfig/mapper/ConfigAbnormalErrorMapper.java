package com.siti.wisdomhydrologic.maintainconfig.mapper;

import java.util.List;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalError;
import org.apache.ibatis.annotations.*;

/**
 * Created by dell on 2019/9/27.
 */
public interface ConfigAbnormalErrorMapper {
//    -- ----------------------------
//            -- Table structure for `config_abnormal_error`
//            -- ----------------------------
//    DROP TABLE IF EXISTS `config_abnormal_error`;
//CREATE TABLE `config_abnormal_error` (
//            `error_id` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '异常类型、故障类型等id',
//            `error_name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '异常类型、故障类型等名称',
//            `belong_which_table` int(10) DEFAULT NULL COMMENT '表示关联哪张运维表的字典，表二为“数据错误判断依据”，表三为”应用程序或设备名称“，表四为”故障现象“',
//            `table1_relate` enum('15','14','13','12','11','10','9','8','7','6','5','4','3','2','','1') COLLATE utf8_bin DEFAULT NULL COMMENT '关联表一的对应列',
//            `description` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '描述内容',
//    PRIMARY KEY (`error_id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



    @Select("select * from config_abnormal_error ")
    List<ConfigAbnormalError> getErrorNameList();

    @Select("select * from config_abnormal_error where belong_which_table=#{tableIndex}")
    List<ConfigAbnormalError> getAllByTableIndex(@Param("tableIndex") Integer tableIndex);

    @Select("select count(*) from config_abnormal_error where error_name=#{errorName}")
    int getCountByErrorName(@Param("errorName") String errorName);

    @Select("select count(*) from config_abnormal_error where error_id=#{errorId}")
    int getCountByErrorId(@Param("errorId") String errorId);

    @Insert("INSERT INTO `config_abnormal_error` (`error_id`, `error_name`, `belong_which_table`, " +
            "`table1_relate`, `description` ) VALUES (" +
            "#{obj.errorId}, " +
            "#{obj.errorName}, " +
            "#{obj.belongWhichTable}, " +
            "#{obj.table1Relate}, " +
            "#{obj.description});")
    int insert(@Param("obj") ConfigAbnormalError configAbnormalError);


    @Update("UPDATE `config_abnormal_error` SET " +
            "`error_name` = #{obj.errorName}, " +
            "`belong_which_table` = #{obj.belongWhichTable}, " +
            "`table1_relate` = #{obj.table1Relate}, " +
            "`description` = #{obj.description} where(`error_id` = #{obj.errorId});")
    int update(@Param("obj") ConfigAbnormalError configAbnormalError);

    @Delete("DELETE FROM  `config_abnormal_error` where(`error_id` = #{errorId});")
    int delete(@Param("errorId") String errorId);
}
