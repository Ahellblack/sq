package com.siti.wisdomhydrologic.mainpage.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
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

    @Select("<script>" +
            "select * " +
            "from config_river_station a " +
            "left join real_station_data b " +
            "on a.station_id = b.station_id where a.station_id is not null "+
            "<if test=\"level!=null\"> and station_level = #{level} </if>" +
            "<if test=\"status!=null\"> and b.status = #{status} </if>" +
            "</script>")
    List<ConfigRiverStationVo> getStationLocation(@Param("level") Integer level, @Param("status")Integer status, @Param("time") String time);

    @Select("select station_id from config_river_station where station_id is not null")
    List<Integer> getStationId();
}
