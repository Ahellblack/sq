package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigSensorDatabase;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
public interface ConfigSensorDatabaseMapper extends Mapper<ConfigSensorDatabase>{

    @Select("SELECT * FROM config_sensor_database")
    List<ConfigSensorDatabase> getAll();
}
