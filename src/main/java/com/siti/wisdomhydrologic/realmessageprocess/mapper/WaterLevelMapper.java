package com.siti.wisdomhydrologic.realmessageprocess.mapper;

import com.siti.wisdomhydrologic.realmessageprocess.Entity.WaterLevelEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by DC on 2019/7/19.
 *
 * @data ${DATA}-15:16
 */
public interface WaterLevelMapper extends Mapper<WaterLevelEntity> {

    @Select("select * from abnormal_water_level")
    List<WaterLevelEntity> fetchAll();



}
