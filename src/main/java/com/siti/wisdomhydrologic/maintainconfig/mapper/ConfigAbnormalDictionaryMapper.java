package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
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

    @Select("select error_name from config_abnormal_dictionary group by error_name")
    List<String> getErrorName();

    @Select("select * from config_abnormal_dictionary " +
            "where broken_according_id like " +
            "'data%' or broken_according_id like 'md%'")
    List<ConfigAbnormalDictionary> getDataErrorNameList();

    @Select("select * from config_abnormal_dictionary " +
            "where broken_according_id like " +
            "'eq%' or broken_according_id like 'ty%'")
    List<ConfigAbnormalDictionary> getEqErrorNameList();

    @Select("select * from config_abnormal_dictionary " +
            "where broken_according_id like 'se%'")
    List<ConfigAbnormalDictionary> getSeErrorNameList();

    @Select("select * from config_abnormal_dictionary where broken_according = #{according}")
    ConfigAbnormalDictionary getOneByAccording(@Param("according") String according);
}
