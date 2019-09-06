package com.siti.wisdomhydrologic.mainpage.mapper;

import com.siti.wisdomhydrologic.mainpage.vo.ConfigRiverStationVo;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by dell on 2019/8/15.
 */
public interface StationDataMapper {

    @Select("SELECT * FROM `real` a " +
            "LEFT JOIN (SELECT  sensor_code ,Max( time ) AS LatestTime FROM `real`  GROUP BY sensor_code  ) b "+
            "ON a.sensor_code = b.sensor_code " +
            "LEFT JOIN  config_river_station c on c.station_id = SUBSTR( a.sensor_code, 1, ( LENGTH( a.sensor_code ) - 2 ) ) "+
            "WHERE a.time = b.LatestTime  and c.station_id is not null")
    List<RealStationVo> getStationData();

    @Select("<script>" +
            "select * " +
            "from config_river_station a " +
            "left join real_station_data b " +
            "on a.station_id = b.station_id where a.station_id is not null "+
            "<if test=\"level!=null\"> and station_level = #{level} </if>" +
            "<if test=\"status!=null\"> and b.status = #{status} </if>" +
            "</script>")
    List<ConfigRiverStationVo> getStationLocation(@Param("level") Integer level, @Param("status") Integer status, @Param("time") String time);

    @Select("select station_id from config_river_station where station_id is not null")
    List<Integer> getStationId();
}
