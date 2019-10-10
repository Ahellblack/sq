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
            "LEFT JOIN (SELECT  sensor_code ,Max( time ) AS LatestTime FROM `real`  " +
            " GROUP BY sensor_code ) b " +
            " ON a.sensor_code = b.sensor_code " +
            " LEFT JOIN  config_sensor_section_module c on c.section_code = a.sensor_code \n" +
            " WHERE a.time = b.LatestTime  and  c.sensor_code is not null  order by time desc")
    List<RealStationVo> getStationData();

    @Select("select * from `real` where sensor_code = #{sensorCode} and time = #{time} ")
    List<RealStationVo> getLatest5minData(@Param("sensorCode") String sensorCode,@Param("time") String time);


    @Select("<script>" +
            "select * " +
            "from config_river_station a " +
            "left join real_station_data b " +
            "on a.station_id = b.station_id " +
            " where  a.sys_org in (SELECT id FROM sys_org so WHERE id = #{orgId} OR FIND_IN_SET( #{orgId}, path ) ) " +
            " and a.station_id is not null "+
            "<if test=\"level!=null\"> and station_level = #{level} </if>" +
            "<if test=\"status!=null\"> and b.status = #{status} </if>" +
            "<if test=\"snId!=null\"> and a.region_id = #{snId} </if>" +
            "</script>")
    List<ConfigRiverStationVo> getStationLocation(@Param("level") Integer level, @Param("status") Integer status, @Param("time") String time,@Param("snId") Integer snId,@Param("orgId") Integer orgId);



    @Select("select station_id from config_river_station where station_id is not null")
    List<Integer> getStationId();
}
