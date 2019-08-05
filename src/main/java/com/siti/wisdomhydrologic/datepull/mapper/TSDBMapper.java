package com.siti.wisdomhydrologic.datepull.mapper;

import com.siti.wisdomhydrologic.datepull.vo.StationVo;
import com.siti.wisdomhydrologic.datepull.vo.TSDBVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by dell on 2019/7/23.
 */
public interface TSDBMapper {

    @Insert("<script>INSERT INTO `history_5min_sensor_data_2019` " +
            "(`sensor_code`,`sensor_data_time`, `sensor_data_value0`,`sensor_data_value1`,`sensor_data_value2`,`sensor_data_value3`,`sensor_data_value4`,`sensor_data_value5`,`sensor_data_value6`,`sensor_data_value7`,`sensor_data_value8`,`sensor_data_value9`,`sensor_data_value10`,`sensor_data_value11`," +
            "`sensor_data_value_status0`,`sensor_data_value_status1`,`sensor_data_value_status2`,`sensor_data_value_status3`,`sensor_data_value_status4`,`sensor_data_value_status5`,`sensor_data_value_status6`,`sensor_data_value_status7`,`sensor_data_value_status8`,`sensor_data_value_status9`,`sensor_data_value_status10`,`sensor_data_value_status11`,`sensor_type_id`,`sensor_type_name`,`station_code`,`station_name`) " +
            "VALUES <foreach collection=\"tsdblist\" item=\"item\" separator=\",\">" +
            "(#{item.SENID},#{item.Time},#{item.V0},#{item.V1},#{item.V2},#{item.V3},#{item.V4},#{item.V5},#{item.V6},#{item.V7},#{item.V8},#{item.V9},#{item.V10},#{item.V11}," +
            "#{item.S0},#{item.S1},#{item.S2},#{item.S3},#{item.S4},#{item.S5},#{item.S6},#{item.S7},#{item.S8},#{item.S9},#{item.S10},#{item.S11}," +
            "#{item.sensorTypeId},#{item.sensorTypeName},#{item.stationId},#{item.stationName})</foreach></script>")
    int insertTSDB(@Param("tsdblist") List<TSDBVo> list);
    @Insert("<script>INSERT INTO `history_5min_sensor_data_2014` " +
            "(`sensor_code`,`sensor_data_time`, `sensor_data_value0`,`sensor_data_value1`,`sensor_data_value2`,`sensor_data_value3`,`sensor_data_value4`,`sensor_data_value5`,`sensor_data_value6`,`sensor_data_value7`,`sensor_data_value8`,`sensor_data_value9`,`sensor_data_value10`,`sensor_data_value11`," +
            "`sensor_data_value_status0`,`sensor_data_value_status1`,`sensor_data_value_status2`,`sensor_data_value_status3`,`sensor_data_value_status4`,`sensor_data_value_status5`,`sensor_data_value_status6`,`sensor_data_value_status7`,`sensor_data_value_status8`,`sensor_data_value_status9`,`sensor_data_value_status10`,`sensor_data_value_status11`,`sensor_type_id`,`sensor_type_name`,`station_code`,`station_name`) " +
            "VALUES <foreach collection=\"tsdblist\" item=\"item\" separator=\",\">" +
            "(#{item.SENID},#{item.Time},#{item.V0},#{item.V1},#{item.V2},#{item.V3},#{item.V4},#{item.V5},#{item.V6},#{item.V7},#{item.V8},#{item.V9},#{item.V10},#{item.V11}," +
            "#{item.S0},#{item.S1},#{item.S2},#{item.S3},#{item.S4},#{item.S5},#{item.S6},#{item.S7},#{item.S8},#{item.S9},#{item.S10},#{item.S11}," +
            "#{item.sensorTypeId},#{item.sensorTypeName},#{item.stationId},#{item.stationName})</foreach></script>")
    int insert2014TSDB(@Param("tsdblist") List<TSDBVo> list);

    @Insert("<script>INSERT INTO `history_5min_sensor_data_2015` " +
            "(`sensor_code`,`sensor_data_time`, `sensor_data_value0`,`sensor_data_value1`,`sensor_data_value2`,`sensor_data_value3`,`sensor_data_value4`,`sensor_data_value5`,`sensor_data_value6`,`sensor_data_value7`,`sensor_data_value8`,`sensor_data_value9`,`sensor_data_value10`,`sensor_data_value11`," +
            "`sensor_data_value_status0`,`sensor_data_value_status1`,`sensor_data_value_status2`,`sensor_data_value_status3`,`sensor_data_value_status4`,`sensor_data_value_status5`,`sensor_data_value_status6`,`sensor_data_value_status7`,`sensor_data_value_status8`,`sensor_data_value_status9`,`sensor_data_value_status10`,`sensor_data_value_status11`,`sensor_type_id`,`sensor_type_name`,`station_code`,`station_name`) " +
            "VALUES <foreach collection=\"tsdblist\" item=\"item\" separator=\",\">" +
            "(#{item.SENID},#{item.Time},#{item.V0},#{item.V1},#{item.V2},#{item.V3},#{item.V4},#{item.V5},#{item.V6},#{item.V7},#{item.V8},#{item.V9},#{item.V10},#{item.V11}," +
            "#{item.S0},#{item.S1},#{item.S2},#{item.S3},#{item.S4},#{item.S5},#{item.S6},#{item.S7},#{item.S8},#{item.S9},#{item.S10},#{item.S11}," +
            "#{item.sensorTypeId},#{item.sensorTypeName},#{item.stationId},#{item.stationName})</foreach></script>")
    int insert2015TSDB(@Param("tsdblist") List<TSDBVo> list);

    @Insert("<script>INSERT INTO `history_5min_sensor_data_2016` " +
            "(`sensor_code`,`sensor_data_time`, `sensor_data_value0`,`sensor_data_value1`,`sensor_data_value2`,`sensor_data_value3`,`sensor_data_value4`,`sensor_data_value5`,`sensor_data_value6`,`sensor_data_value7`,`sensor_data_value8`,`sensor_data_value9`,`sensor_data_value10`,`sensor_data_value11`," +
            "`sensor_data_value_status0`,`sensor_data_value_status1`,`sensor_data_value_status2`,`sensor_data_value_status3`,`sensor_data_value_status4`,`sensor_data_value_status5`,`sensor_data_value_status6`,`sensor_data_value_status7`,`sensor_data_value_status8`,`sensor_data_value_status9`,`sensor_data_value_status10`,`sensor_data_value_status11`,`sensor_type_id`,`sensor_type_name`,`station_code`,`station_name`) " +
            "VALUES <foreach collection=\"tsdblist\" item=\"item\" separator=\",\">" +
            "(#{item.SENID},#{item.Time},#{item.V0},#{item.V1},#{item.V2},#{item.V3},#{item.V4},#{item.V5},#{item.V6},#{item.V7},#{item.V8},#{item.V9},#{item.V10},#{item.V11}," +
            "#{item.S0},#{item.S1},#{item.S2},#{item.S3},#{item.S4},#{item.S5},#{item.S6},#{item.S7},#{item.S8},#{item.S9},#{item.S10},#{item.S11}," +
            "#{item.sensorTypeId},#{item.sensorTypeName},#{item.stationId},#{item.stationName})</foreach></script>")
    int insert2016TSDB(@Param("tsdblist") List<TSDBVo> list);

    @Insert("<script>INSERT INTO `history_5min_sensor_data_2017` " +
            "(`sensor_code`,`sensor_data_time`, `sensor_data_value0`,`sensor_data_value1`,`sensor_data_value2`,`sensor_data_value3`,`sensor_data_value4`,`sensor_data_value5`,`sensor_data_value6`,`sensor_data_value7`,`sensor_data_value8`,`sensor_data_value9`,`sensor_data_value10`,`sensor_data_value11`," +
            "`sensor_data_value_status0`,`sensor_data_value_status1`,`sensor_data_value_status2`,`sensor_data_value_status3`,`sensor_data_value_status4`,`sensor_data_value_status5`,`sensor_data_value_status6`,`sensor_data_value_status7`,`sensor_data_value_status8`,`sensor_data_value_status9`,`sensor_data_value_status10`,`sensor_data_value_status11`,`sensor_type_id`,`sensor_type_name`,`station_code`,`station_name`) " +
            "VALUES <foreach collection=\"tsdblist\" item=\"item\" separator=\",\">" +
            "(#{item.SENID},#{item.Time},#{item.V0},#{item.V1},#{item.V2},#{item.V3},#{item.V4},#{item.V5},#{item.V6},#{item.V7},#{item.V8},#{item.V9},#{item.V10},#{item.V11}," +
            "#{item.S0},#{item.S1},#{item.S2},#{item.S3},#{item.S4},#{item.S5},#{item.S6},#{item.S7},#{item.S8},#{item.S9},#{item.S10},#{item.S11}," +
            "#{item.sensorTypeId},#{item.sensorTypeName},#{item.stationId},#{item.stationName})</foreach></script>")
    int insert2017TSDB(@Param("tsdblist") List<TSDBVo> list);


    @Select("Select * from config_sensor_station_comparison")
    List<StationVo> getStation();

}
