package com.siti.wisdomhydrologic.mainpage.mapper;

import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    @Update("UPDATE `real_station_data` SET  " +
            "`time` = #{stationData.time}," +
            "`water_level` = #{stationData.realDataWaterLevel}, `rainfall` = #{stationData.realDataRainFall}, " +
            "`tide_level` = #{stationData.realDataTideLevel} , `electric` = #{stationData.realDataElectric}," +
            "`wind_speed` = #{stationData.realDataWindSpeed}, `wind_direction` =#{stationData.realDataWindDirection}, " +
            "`flow_velocity_x` = #{stationData.realDataFlowX} ,`flow_velocity_y` = #{stationData.realDataFlowY} , " +
            " `air_pressure` =#{stationData.realDataAirPressure}, `air_temperature`=#{stationData.realDataAirTemperature}, " +
            "`status` = #{stationData.status} , `patency_rate`=#{stationData.patencyRate} WHERE station_id = #{stationData.stationCode} ")
    int updateStationData(@Param("stationData") RealStationVo realStationVo);

    @Update("UPDATE `real_station_data` SET " +
            " `patency_rate`=#{stationData.patencyRate} WHERE station_id = #{stationData.stationCode} ")
    int updateStationPatency(@Param("stationData") RealStationVo realStationVo);


    @Select("<script>select * from real_station_data " +
            "<if test=\"stationCode!=null\">where station_id = #{stationCode}</if></script>")
    RealStationData getData(@Param("stationCode") Integer stationCode);

    @Select("<script>select * from real_station_data a left join config_river_station b on a.station_id = b.station_id" +
            "<if test=\"regionName!=null\">where b.region_name = #{regionName}</if></script>")
    List<RealStationData> getDataList(@Param("regionName") String regionName);

    @Select("<script>select a.patency_rate,b.station_name,b.flow_rate from real_station_data a " +
            "left join config_river_station b " +
            "on a.station_id = b.station_id where " +
            "a.patency_rate &lt; b.flow_rate " +
            "and a.patency_rate &gt; 0 " +
            "<if test=\"regionName!=null\"> and b.region_name = #{regionName}</if></script>")
    List<RealStationData> getPatencyDataList(@Param("regionName") String regionName);

    @Select("<script>select * FROM `real` WHERE  date(time) = date_sub(curdate(),interval 1 day) " +
            "<if test=\"sensorCode!=null\">and sensor_code = #{sensorCode} </if></script>\n")
    List<RealVo> getLastDayList(@Param("sensorCode") String sensorCode);

}
