package com.siti.wisdomhydrologic.mainpage.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import java.util.List;

import com.siti.wisdomhydrologic.maintainconfig.entity.ConfigRiverStation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by dell on 2019/8/15.
 */
public interface StationDataMapper {

    @Select("select * from `real` a left join config_river_station b " +
            "on SUBSTR(a.sensor_code,1,(LENGTH(a.sensor_code)-2)) = b.station_id  where " +
            "SUBSTR(sensor_code,1,(LENGTH(sensor_code)-2)) = #{station_code} " +
            "AND TIME = #{time}")
    List<RealStationVo> getStationData(@Param("station_code") Integer stationCode, @Param("time") String realtime);

    @Select("select station_id,station_gaode_longitude,station_gaode_latitude from config_river_station where station_id is not null")
    List<ConfigRiverStation> getStationLocation();
}
