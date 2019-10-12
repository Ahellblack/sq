package com.siti.wisdomhydrologic.configmaintain.mapper;

import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalDictionary;
import com.siti.wisdomhydrologic.configmaintain.entity.ConfigAbnormalError;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by dell on 2019/8/14.
 */
public interface ConfigAbnormalDictionaryMapper {

    @Select("select * from config_abnormal_dictionary")
    List<ConfigAbnormalDictionary> getList();


    @Select("select * from config_abnormal_dictionary where table1_relate is not null")
    List<ConfigAbnormalDictionary> getTableList();

    @Select("select * from config_abnormal_error ")
    List<ConfigAbnormalError> getErrorName();

    @Select("select * from config_abnormal_dictionary " +
            "where broken_according_id like " +
            "'data%' or broken_according_id like 'md%'")
    List<ConfigAbnormalDictionary> getDataErrorNameList();

    @Select("select * from config_abnormal_dictionary " +
            "where broken_according_id like " +
            "'eq%' or broken_according_id like 'ty%'")
    List<ConfigAbnormalDictionary> getEqErrorNameList();


    @Select("<script>select * from config_abnormal_error " +
            "where belong_which_table in (<foreach collection=\"tableNumber\" item=\"item\" separator=\",\">#{item}</foreach>)</script>")
    List<ConfigAbnormalError> getErrorNameList(@Param("tableNumber") List<Integer> tableNumber);


    @Select("select * from config_abnormal_dictionary where broken_according = #{according}")
    ConfigAbnormalDictionary getOneByAccording(@Param("according") String according);
}
