package com.siti.wisdomhydrologic.mainpage.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.DeviceVo;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zyw on 2019/10/25.
 */
public interface DeviceTemMapper {

    @Select("select * from device_real_status drs left join config_dev_station cds on drs.devid = cds.devid where devtype =2 ")
    DeviceVo getTemperature();
}
