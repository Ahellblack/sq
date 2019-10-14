package com.siti.wisdomhydrologic.configfund.mapper;

import com.siti.wisdomhydrologic.configfund.entity.ConfigRegion;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/10/14.
 */
public interface ConfigRegionMapper {


    @Select("select region_id,region_name from config_region")
    List<ConfigRegion> getAll();
}
