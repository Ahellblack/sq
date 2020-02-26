package com.siti.wisdomhydrologic.mainpage.mapper;

import com.siti.wisdomhydrologic.mainpage.entity.RealStationData;
import com.siti.wisdomhydrologic.mainpage.vo.RealStationVo;
import com.siti.wisdomhydrologic.mainpage.vo.RealVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zyw on 20#{stationData.}9/8/20.
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

    @Update("<script>" +
            "<foreach item ='item' index = 'key' collection='stationData' separator = ';'>" +
            " UPDATE `real_station_data` SET  " +
            "`time` = #{item.time}," +
            "`water_level` = #{item.waterLevel}, `rainfall` = #{item.rainfall}, " +
            "`tide_level` = #{item.tideLevel} , `electric` = #{item.electric}," +
            "`wind_speed` = #{item.windSpeed}, `wind_direction` =#{item.windDirection}, " +
            "`flow_velocity_x` = #{item.flowVelocityX} ,`flow_velocity_y` = #{item.flowVelocityY} , " +
            " `air_pressure` =#{item.airPressure}, `air_temperature`=#{item.airTemperature}, " +
            "`status` = #{item.status}  WHERE station_id = #{item.stationId}</foreach>" +
            "</script>")
    int updateStationData(@Param("stationData") List<RealStationData> realStationVo);

    @Update("UPDATE `real_station_data` SET " +
            " `patency_rate`=#{stationData.patencyRate} WHERE station_id = #{stationData.stationId} ")
    int updateStationPatency(@Param("stationData") RealStationData realStationVo);


    @Select("<script>select * from real_station_data " +
            "<if test=\"stationCode!=null\">where station_id = #{stationCode}</if></script>")
    RealStationData getData(@Param("stationCode") Integer stationCode);

    @Select("select Max(time) as time from `real` ")
    String getStationLatestData();

    @Select("SELECT MIN(timestampdiff(MINUTE, time,CURRENT_TIME))  FROM `real`  ")
    Integer getTimestampDiffWithCurrent();

    @Select("select * from real_station_data ")
    RealStationData getAllData();


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


    /**
     * 首页畅通率
     * */
    @Select("<script>select count(*) FROM `real` " +
            " WHERE time &gt;= #{startTime} and time &lt; #{endTime} " +
            " and sensor_code = #{sensorCode}</script> ")
    Integer getLastDayList(@Param("sensorCode") String sensorCode, @Param("startTime") String startTime, @Param("endTime") String endTime);

}
