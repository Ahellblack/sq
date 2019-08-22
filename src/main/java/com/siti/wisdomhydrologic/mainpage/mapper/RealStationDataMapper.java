package com.siti.wisdomhydrologic.mainpage.mapper;

import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import java.util.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by dell on 20#{stationData.}9/8/20.
 */
public interface RealStationDataMapper {

    @Insert("INSERT INTO `real_station_data`(`time`, `station_id`, `station_name`, " +
            "`water_level`, `rainfall`, `tide_level`, `electric`,`wind_speed`, `wind_direction`, " +
            "`flow_velocity_x`,`flow_velocity_y`, `air_pressure`, `air_temperature`, `status`) " +
            "VALUES (#{stationData.time}, #{stationData.stationId}, #{stationData.stationName}, " +
            "#{stationData.realDataWaterLevel}, #{stationData.realDataRainFall}, #{stationData.realDataTideLevel}, " +
            "#{stationData.realDataElectric},#{stationData.realDataWindSpeed}, #{stationData.realDataWindDirection}," +
            " #{stationData.realDataFlowX}, #{stationData.realDataFlowY}, #{stationData.realDataAirPressure}," +
            " #{stationData.realDataAirTemperature}, #{stationData.status})")
    int insertStationData(@Param("stationData") RealStationVo realStationVo);

    @Update("UPDATE `real_station_data` SET " +
            "`time` = #{stationData.time}, `station_id` = #{stationData.stationId}," +
            "`station_name` = #{stationData.stationName}, " +
            "`water_level` = #{stationData.realDataWaterLevel}, `rainfall` = #{stationData.realDataRainFall}, " +
            "`tide_level` = #{stationData.realDataTideLevel} , `electric` = #{stationData.realDataElectric}," +
            "`wind_speed` = #{stationData.realDataWindSpeed}, `wind_direction` =#{stationData.realDataWindDirection}, " +
            "`flow_velocity_x` = #{stationData.realDataFlowX} ,`flow_velocity_y` = #{stationData.realDataFlowY} , " +
            " `air_pressure` =#{stationData.realDataAirPressure}, `air_temperature`=#{stationData.realDataAirTemperature}, " +
            "`status` = #{stationData.status} WHERE station_id = #{stationData.stationId} ")
    int updateStationData(@Param("stationData") RealStationVo realStationVo);

    @Select("select * from real_station_data where " +
            "station_id = #{stationCode}")
    RealStationData getData(@Param("stationCode")Integer stationCode);

}
