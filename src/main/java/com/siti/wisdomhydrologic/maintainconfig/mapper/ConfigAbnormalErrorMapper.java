package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigAbnormalError;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/9/27.
 */
public interface ConfigAbnormalErrorMapper {

    @Select("select * from config_abnormal_error ")
    List<ConfigAbnormalError> getErrorNameList();

}
