package com.siti.wisdomhydrologic.maintainconfig.mapper;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/9.
 */
public interface ConfigRiverStationMapper extends Mapper<ConfigRiverStation> {

    @Select("select * from config_river_station")
    List<ConfigRiverStation> getAll();

}
