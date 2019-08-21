package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalDictionary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by dell on 2019/8/14.
 */
public interface ConfigAbnormalDictionaryMapper {

    @Select("select * from config_abnormal_dictionary")
    List<ConfigAbnormalDictionary> getList();

    @Select("select error_name from config_abnormal_dictionary group by error_name")
    List<String> getErrorName();


}
