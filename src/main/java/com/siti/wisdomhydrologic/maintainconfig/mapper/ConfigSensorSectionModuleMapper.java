package com.siti.wisdomhydrologic.maintainconfig.mapper;


import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorSectionModule;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/15.
 */
public interface ConfigSensorSectionModuleMapper extends Mapper<ConfigSensorSectionModule>{

    @Select("Select * from config_sensor_section_module")
    List<ConfigSensorSectionModule> getStation();
}
