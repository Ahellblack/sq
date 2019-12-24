package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.DayData;
import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import com.siti.wisdomhydrologic.operation.vo.ReportStationRainConstrastVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by zyw on 2019/8/1.
 */
public interface StationRainConstrastMapper extends Mapper<ReportStationRainConstrast>{

    @Select("<script>SELECT report_id,station_code,station_name,manage_org_id,manage_org_name,data_year_month,remark," +
            " SUBSTRING_INDEX( s.day1, ',', 1 ) AS day1_auto,\n" + "\tsubstring_index( substring_index( s.day1, ',', 2 ), ',',- 1 ) AS day1_base,\n" + "\tSUBSTRING_INDEX( s.day1, ',',- 1 ) AS day1_diff,\n" + "\tSUBSTRING_INDEX( s.day2, ',', 1 ) AS day2_auto,\n" + "\tsubstring_index( substring_index( s.day2, ',', 2 ), ',',- 1 ) AS day2_base,\n" + "\tSUBSTRING_INDEX( s.day2, ',',- 1 ) AS day2_diff,\n" + "\tSUBSTRING_INDEX( s.day3, ',', 1 ) AS day3_auto,\n" + "\tsubstring_index( substring_index( s.day3, ',', 2 ), ',',- 1 ) AS day3_base,\n" + "\tSUBSTRING_INDEX( s.day3, ',',- 1 ) AS day3_diff,\n" + "\tSUBSTRING_INDEX( s.day4, ',', 1 ) AS day4_auto,\n" + "\tsubstring_index( substring_index( s.day4, ',', 2 ), ',',- 1 ) AS day4_base,\n" + "\tSUBSTRING_INDEX( s.day4, ',',- 1 ) AS day4_diff,\n" + "\tSUBSTRING_INDEX( s.day5, ',', 1 ) AS day5_auto,\n" + "\tsubstring_index( substring_index( s.day5, ',', 2 ), ',',- 1 ) AS day5_base,\n" + "\tSUBSTRING_INDEX( s.day5, ',',- 1 ) AS day5_diff,\n" + "\tSUBSTRING_INDEX( s.day6, ',', 1 ) AS day6_auto,\n" + "\tsubstring_index( substring_index( s.day6, ',', 2 ), ',',- 1 ) AS day6_base,\n" + "\tSUBSTRING_INDEX( s.day6, ',',- 1 ) AS day6_diff,\n" + "\tSUBSTRING_INDEX( s.day7, ',', 1 ) AS day7_auto,\n" + "\tsubstring_index( substring_index( s.day7, ',', 2 ), ',',- 1 ) AS day7_base,\n" + "\tSUBSTRING_INDEX( s.day7, ',',- 1 ) AS day7_diff,\n" + "\tSUBSTRING_INDEX( s.day8, ',', 1 ) AS day8_auto,\n" + "\tsubstring_index( substring_index( s.day8, ',', 2 ), ',',- 1 ) AS day8_base,\n" + "\tSUBSTRING_INDEX( s.day8, ',',- 1 ) AS day8_diff,\n" + "\tSUBSTRING_INDEX( s.day9, ',', 1 ) AS day9_auto,\n" + "\tsubstring_index( substring_index( s.day9, ',', 2 ), ',',- 1 ) AS day9_base,\n" + "\tSUBSTRING_INDEX( s.day9, ',',- 1 ) AS day9_diff,\n" + "\tSUBSTRING_INDEX( s.day10, ',', 1 ) AS day10_auto,\n" + "\tsubstring_index( substring_index( s.day10, ',', 2 ), ',',- 1 ) AS day10_base,\n" + "\tSUBSTRING_INDEX( s.day10, ',',- 1 ) AS day10_diff,\n" + "\tSUBSTRING_INDEX( s.day11, ',', 1 ) AS day11_auto,\n" + "\tsubstring_index( substring_index( s.day11, ',', 2 ), ',',- 1 ) AS day11_base,\n" + "\tSUBSTRING_INDEX( s.day11, ',',- 1 ) AS day11_diff,\n" + "\tSUBSTRING_INDEX( s.day12, ',', 1 ) AS day12_auto,\n" + "\tsubstring_index( substring_index( s.day12, ',', 2 ), ',',- 1 ) AS day12_base,\n" + "\tSUBSTRING_INDEX( s.day12, ',',- 1 ) AS day12_diff,\n" + "\tSUBSTRING_INDEX( s.day13, ',', 1 ) AS day13_auto,\n" + "\tsubstring_index( substring_index( s.day13, ',', 2 ), ',',- 1 ) AS day13_base,\n" + "\tSUBSTRING_INDEX( s.day13, ',',- 1 ) AS day13_diff,\n" + "\tSUBSTRING_INDEX( s.day14, ',', 1 ) AS day14_auto,\n" + "\tsubstring_index( substring_index( s.day14, ',', 2 ), ',',- 1 ) AS day14_base,\n" + "\tSUBSTRING_INDEX( s.day14, ',',- 1 ) AS day14_diff,\n" + "\tSUBSTRING_INDEX( s.day15, ',', 1 ) AS day15_auto,\n" + "\tsubstring_index( substring_index( s.day15, ',', 2 ), ',',- 1 ) AS day15_base,\n" + "\tSUBSTRING_INDEX( s.day15, ',',- 1 ) AS day15_diff,\n" + "\tSUBSTRING_INDEX( s.day16, ',', 1 ) AS day16_auto,\n" + "\tsubstring_index( substring_index( s.day16, ',', 2 ), ',',- 1 ) AS day16_base,\n" + "\tSUBSTRING_INDEX( s.day16, ',',- 1 ) AS day16_diff,\n" + "\tSUBSTRING_INDEX( s.day17, ',', 1 ) AS day17_auto,\n" + "\tsubstring_index( substring_index( s.day17, ',', 2 ), ',',- 1 ) AS day17_base,\n" + "\tSUBSTRING_INDEX( s.day17, ',',- 1 ) AS day17_diff,\n" + "\tSUBSTRING_INDEX( s.day18, ',', 1 ) AS day18_auto,\n" + "\tsubstring_index( substring_index( s.day18, ',', 2 ), ',',- 1 ) AS day18_base,\n" + "\tSUBSTRING_INDEX( s.day18, ',',- 1 ) AS day18_diff,\n" + "\tSUBSTRING_INDEX( s.day19, ',', 1 ) AS day19_auto,\n" + "\tsubstring_index( substring_index( s.day19, ',', 2 ), ',',- 1 ) AS day19_base,\n" + "\tSUBSTRING_INDEX( s.day19, ',',- 1 ) AS day19_diff,\n" + "\tSUBSTRING_INDEX( s.day20, ',', 1 ) AS day20_auto,\n" + "\tsubstring_index( substring_index( s.day20, ',', 2 ), ',',- 1 ) AS day20_base,\n" + "\tSUBSTRING_INDEX( s.day20, ',',- 1 ) AS day20_diff,\n" + "\tSUBSTRING_INDEX( s.day21, ',', 1 ) AS day21_auto,\n" + "\tsubstring_index( substring_index( s.day21, ',', 2 ), ',',- 1 ) AS day21_base,\n" + "\tSUBSTRING_INDEX( s.day21, ',',- 1 ) AS day21_diff,\n" + "\tSUBSTRING_INDEX( s.day22, ',', 1 ) AS day22_auto,\n" + "\tsubstring_index( substring_index( s.day22, ',', 2 ), ',',- 1 ) AS day22_base,\n" + "\tSUBSTRING_INDEX( s.day22, ',',- 1 ) AS day22_diff,\n" + "\tSUBSTRING_INDEX( s.day23, ',', 1 ) AS day23_auto,\n" + "\tsubstring_index( substring_index( s.day23, ',', 2 ), ',',- 1 ) AS day23_base,\n" + "\tSUBSTRING_INDEX( s.day23, ',',- 1 ) AS day23_diff,\n" + "\tSUBSTRING_INDEX( s.day24, ',', 1 ) AS day24_auto,\n" + "\tsubstring_index( substring_index( s.day24, ',', 2 ), ',',- 1 ) AS day24_base,\n" + "\tSUBSTRING_INDEX( s.day24, ',',- 1 ) AS day24_diff,\n" + "\tSUBSTRING_INDEX( s.day25, ',', 1 ) AS day25_auto,\n" + "\tsubstring_index( substring_index( s.day25, ',', 2 ), ',',- 1 ) AS day25_base,\n" + "\tSUBSTRING_INDEX( s.day25, ',',- 1 ) AS day25_diff,\n" + "\tSUBSTRING_INDEX( s.day26, ',', 1 ) AS day26_auto,\n" + "\tsubstring_index( substring_index( s.day26, ',', 2 ), ',',- 1 ) AS day26_base,\n" + "\tSUBSTRING_INDEX( s.day26, ',',- 1 ) AS day26_diff,\n" + "\tSUBSTRING_INDEX( s.day27, ',', 1 ) AS day27_auto,\n" + "\tsubstring_index( substring_index( s.day27, ',', 2 ), ',',- 1 ) AS day27_base,\n" + "\tSUBSTRING_INDEX( s.day27, ',',- 1 ) AS day27_diff,\n" + "\tSUBSTRING_INDEX( s.day28, ',', 1 ) AS day28_auto,\n" + "\tsubstring_index( substring_index( s.day28, ',', 2 ), ',',- 1 ) AS day28_base,\n" + "\tSUBSTRING_INDEX( s.day28, ',',- 1 ) AS day28_diff,\n" + "\tSUBSTRING_INDEX( s.day29, ',', 1 ) AS day29_auto,\n" + "\tsubstring_index( substring_index( s.day29, ',', 2 ), ',',- 1 ) AS day29_base,\n" + "\tSUBSTRING_INDEX( s.day29, ',',- 1 ) AS day29_diff,\n" + "\tSUBSTRING_INDEX( s.day30, ',', 1 ) AS day30_auto,\n" + "\tsubstring_index( substring_index( s.day30, ',', 2 ), ',',- 1 ) AS day30_base,\n" + "\tSUBSTRING_INDEX( s.day30, ',',- 1 ) AS day30_diff,\n" + "\tSUBSTRING_INDEX( s.day31, ',', 1 ) AS day31_auto,\n" + "\tsubstring_index( substring_index( s.day31, ',', 2 ), ',',- 1 ) AS day31_base,\n" + "\tSUBSTRING_INDEX( s.day31, ',',- 1 ) AS day31_diff, " +
            " SUBSTRING_INDEX( s.total, ',', 1 ) AS auto_total,substring_index( substring_index( s.total, ',', 2 ), ',',- 1 ) AS base_total,SUBSTRING_INDEX( s.total, ',',- 1 ) AS diff_total " +
            "from report_station_rain_constrast s <if test=\"date!=null\"> where data_year_month = #{date}</if></script>")
    List<ReportStationRainConstrastVo> getByMonth(@Param("date") String date);

    @Select("<script>select * from report_station_rain_constrast " +
            "<if test=\"date!=null\"> where data_year_month = #{date}</if>" +
            "<if test=\"stationId!=null\"> and station_code = #{stationId}</if>" +
            "</script>")
    List<ReportStationRainConstrast> getAll(@Param("date") String date,@Param("stationId") Integer stationId);

    @Select("<script>select * from report_station_rain_constrast " +
            "<if test=\"dataYearMonth!=null\"> where data_year_month = #{dataYearMonth} </if>" +
            "<if test=\"stationName!=null\"> and station_name = #{stationName}</if></script>")
    ReportStationRainConstrast getData(@Param("stationName") String stationName,@Param("dataYearMonth") String dataYearMonth);

    /**
     *查询每日的测站雨量数据
     * */
    @Select("select total from report_station_rain_constrast " +
            "WHERE `station_code` = #{stationCode} " +
            "AND `data_year_month` = #{yearMonth} ")
    String getTotal(@Param("stationCode") Integer stationCode, @Param("yearMonth") String yearMonth);

    //查询当天的雨量数据
    @Select("select * from ${datebase} " +
            "where SUBSTR(sensor_data_upload_time,1,10) = #{date}" +
            "and sensor_code =#{sensorCode} ")
    List<DayData> getDayData(@Param("sensorCode") String sensorCode, @Param("datebase") String datebase,@Param("date") String date);

    //测试某天的的雨量数据
    @Select("select * from ${datebase} " +
            "where SUBSTR(sensor_data_upload_time,1,10) = #{day} " +  //SUBSTR(sensor_data_upload_time,1,10) = CURDATE()
            "and sensor_code =#{sensorCode} ")
    List<DayData> getChosenDayData(@Param("day")String day,@Param("sensorCode") String sensorCode, @Param("datebase") String datebase);

    @Update("UPDATE `report_station_rain_constrast` " +
            "SET ${dayNumber} = #{value} , total =#{total}" +
            "WHERE `station_code` = #{stationCode} " +
            "AND `data_year_month` = #{yearMonth}")
    int update(@Param("dayNumber") String dayNumber, @Param("value") String value, @Param("stationCode") Integer stationCode, @Param("yearMonth") String yearMonth, @Param("total") String total);

    @Update("UPDATE `report_station_rain_constrast` SET  `station_code` = #{entity.stationCode}," +
            " `station_name` = #{entity.stationName},  " +
            "`data_year_month` = #{entity.dataYearMonth}," +
            " `day1` = #{entity.day1}, `day2` = #{entity.day2}, `day3` = #{entity.day3}, " +
            "`day4` = #{entity.day4}, `day5` = #{entity.day5}, `day6` = #{entity.day6}, " +
            "`day7` = #{entity.day7}, `day8` = #{entity.day8}, `day9` = #{entity.day9}," +
            " `day10` = #{entity.day10}, `day11` = #{entity.day11}, `day12` = #{entity.day12}, " +
            "`day13` = #{entity.day13}, `day14` = #{entity.day14}, `day15` = #{entity.day15}, " +
            "`day16` = #{entity.day16}, `day17` = #{entity.day17}, `day18` = #{entity.day18}," +
            " `day19` = #{entity.day19}, `day20` = #{entity.day20}, `day21` = #{entity.day21}," +
            " `day22` = #{entity.day22}, `day23` = #{entity.day23}, `day24` = #{entity.day24}," +
            " `day25` = #{entity.day25}, `day26` = #{entity.day26}, `day27` = #{entity.day27}, " +
            "`day28` = #{entity.day28}, `day29` = #{entity.day29}, `day30` = #{entity.day30}, " +
            "`day31` = #{entity.day31}, `total` = #{entity.total}, `remark` = #{entity.remark}, " +
            "`create_time` = #{entity.createTime}, " +
            "`create_by` = #{entity.createBy}, " +
            "`update_time` = #{entity.updateTime}," +
            " `update_by` = #{entity.updateBy} " +
            "WHERE `station_name` = #{entity.stationName} " +
            "AND `data_year_month` = #{entity.dataYearMonth}")
    int updateData(@Param("entity") ReportStationRainConstrast entity);

    @Select("<script>SELECT report_id,station_code,station_name,manage_org_id,manage_org_name,data_year_month," +
            " SUBSTRING_INDEX( s.day1, ',', 1 ) AS day1_auto,\n" + "\tsubstring_index( substring_index( s.day1, ',', 2 ), ',',- 1 ) AS day1_base,\n" + "\tSUBSTRING_INDEX( s.day1, ',',- 1 ) AS day1_diff,\n" + "\tSUBSTRING_INDEX( s.day2, ',', 1 ) AS day2_auto,\n" + "\tsubstring_index( substring_index( s.day2, ',', 2 ), ',',- 1 ) AS day2_base,\n" + "\tSUBSTRING_INDEX( s.day2, ',',- 1 ) AS day2_diff,\n" + "\tSUBSTRING_INDEX( s.day3, ',', 1 ) AS day3_auto,\n" + "\tsubstring_index( substring_index( s.day3, ',', 2 ), ',',- 1 ) AS day3_base,\n" + "\tSUBSTRING_INDEX( s.day3, ',',- 1 ) AS day3_diff,\n" + "\tSUBSTRING_INDEX( s.day4, ',', 1 ) AS day4_auto,\n" + "\tsubstring_index( substring_index( s.day4, ',', 2 ), ',',- 1 ) AS day4_base,\n" + "\tSUBSTRING_INDEX( s.day4, ',',- 1 ) AS day4_diff,\n" + "\tSUBSTRING_INDEX( s.day5, ',', 1 ) AS day5_auto,\n" + "\tsubstring_index( substring_index( s.day1, ',', 2 ), ',',- 1 ) AS day5_base,\n" + "\tSUBSTRING_INDEX( s.day5, ',',- 1 ) AS day5_diff,\n" + "\tSUBSTRING_INDEX( s.day6, ',', 1 ) AS day6_auto,\n" + "\tsubstring_index( substring_index( s.day6, ',', 2 ), ',',- 1 ) AS day6_base,\n" + "\tSUBSTRING_INDEX( s.day6, ',',- 1 ) AS day6_diff,\n" + "\tSUBSTRING_INDEX( s.day7, ',', 1 ) AS day7_auto,\n" + "\tsubstring_index( substring_index( s.day7, ',', 2 ), ',',- 1 ) AS day7_base,\n" + "\tSUBSTRING_INDEX( s.day7, ',',- 1 ) AS day7_diff,\n" + "\tSUBSTRING_INDEX( s.day8, ',', 1 ) AS day8_auto,\n" + "\tsubstring_index( substring_index( s.day8, ',', 2 ), ',',- 1 ) AS day8_base,\n" + "\tSUBSTRING_INDEX( s.day8, ',',- 1 ) AS day8_diff,\n" + "\tSUBSTRING_INDEX( s.day9, ',', 1 ) AS day9_auto,\n" + "\tsubstring_index( substring_index( s.day9, ',', 2 ), ',',- 1 ) AS day9_base,\n" + "\tSUBSTRING_INDEX( s.day9, ',',- 1 ) AS day9_diff,\n" + "\tSUBSTRING_INDEX( s.day10, ',', 1 ) AS day10_auto,\n" + "\tsubstring_index( substring_index( s.day10, ',', 2 ), ',',- 1 ) AS day10_base,\n" + "\tSUBSTRING_INDEX( s.day10, ',',- 1 ) AS day10_diff,\n" + "\tSUBSTRING_INDEX( s.day11, ',', 1 ) AS day11_auto,\n" + "\tsubstring_index( substring_index( s.day11, ',', 2 ), ',',- 1 ) AS day11_base,\n" + "\tSUBSTRING_INDEX( s.day11, ',',- 1 ) AS day11_diff,\n" + "\tSUBSTRING_INDEX( s.day12, ',', 1 ) AS day12_auto,\n" + "\tsubstring_index( substring_index( s.day12, ',', 2 ), ',',- 1 ) AS day12_base,\n" + "\tSUBSTRING_INDEX( s.day12, ',',- 1 ) AS day12_diff,\n" + "\tSUBSTRING_INDEX( s.day13, ',', 1 ) AS day13_auto,\n" + "\tsubstring_index( substring_index( s.day13, ',', 2 ), ',',- 1 ) AS day13_base,\n" + "\tSUBSTRING_INDEX( s.day13, ',',- 1 ) AS day13_diff,\n" + "\tSUBSTRING_INDEX( s.day14, ',', 1 ) AS day14_auto,\n" + "\tsubstring_index( substring_index( s.day14, ',', 2 ), ',',- 1 ) AS day14_base,\n" + "\tSUBSTRING_INDEX( s.day14, ',',- 1 ) AS day14_diff,\n" + "\tSUBSTRING_INDEX( s.day15, ',', 1 ) AS day15_auto,\n" + "\tsubstring_index( substring_index( s.day15, ',', 2 ), ',',- 1 ) AS day15_base,\n" + "\tSUBSTRING_INDEX( s.day15, ',',- 1 ) AS day15_diff,\n" + "\tSUBSTRING_INDEX( s.day16, ',', 1 ) AS day16_auto,\n" + "\tsubstring_index( substring_index( s.day16, ',', 2 ), ',',- 1 ) AS day16_base,\n" + "\tSUBSTRING_INDEX( s.day16, ',',- 1 ) AS day16_diff,\n" + "\tSUBSTRING_INDEX( s.day17, ',', 1 ) AS day17_auto,\n" + "\tsubstring_index( substring_index( s.day17, ',', 2 ), ',',- 1 ) AS day17_base,\n" + "\tSUBSTRING_INDEX( s.day17, ',',- 1 ) AS day17_diff,\n" + "\tSUBSTRING_INDEX( s.day18, ',', 1 ) AS day18_auto,\n" + "\tsubstring_index( substring_index( s.day18, ',', 2 ), ',',- 1 ) AS day18_base,\n" + "\tSUBSTRING_INDEX( s.day18, ',',- 1 ) AS day18_diff,\n" + "\tSUBSTRING_INDEX( s.day19, ',', 1 ) AS day19_auto,\n" + "\tsubstring_index( substring_index( s.day19, ',', 2 ), ',',- 1 ) AS day19_base,\n" + "\tSUBSTRING_INDEX( s.day19, ',',- 1 ) AS day19_diff,\n" + "\tSUBSTRING_INDEX( s.day20, ',', 1 ) AS day20_auto,\n" + "\tsubstring_index( substring_index( s.day20, ',', 2 ), ',',- 1 ) AS day20_base,\n" + "\tSUBSTRING_INDEX( s.day20, ',',- 1 ) AS day20_diff,\n" + "\tSUBSTRING_INDEX( s.day21, ',', 1 ) AS day21_auto,\n" + "\tsubstring_index( substring_index( s.day21, ',', 2 ), ',',- 1 ) AS day21_base,\n" + "\tSUBSTRING_INDEX( s.day21, ',',- 1 ) AS day21_diff,\n" + "\tSUBSTRING_INDEX( s.day22, ',', 1 ) AS day22_auto,\n" + "\tsubstring_index( substring_index( s.day22, ',', 2 ), ',',- 1 ) AS day22_base,\n" + "\tSUBSTRING_INDEX( s.day22, ',',- 1 ) AS day22_diff,\n" + "\tSUBSTRING_INDEX( s.day23, ',', 1 ) AS day23_auto,\n" + "\tsubstring_index( substring_index( s.day23, ',', 2 ), ',',- 1 ) AS day23_base,\n" + "\tSUBSTRING_INDEX( s.day23, ',',- 1 ) AS day23_diff,\n" + "\tSUBSTRING_INDEX( s.day24, ',', 1 ) AS day24_auto,\n" + "\tsubstring_index( substring_index( s.day24, ',', 2 ), ',',- 1 ) AS day24_base,\n" + "\tSUBSTRING_INDEX( s.day24, ',',- 1 ) AS day24_diff,\n" + "\tSUBSTRING_INDEX( s.day25, ',', 1 ) AS day25_auto,\n" + "\tsubstring_index( substring_index( s.day25, ',', 2 ), ',',- 1 ) AS day25_base,\n" + "\tSUBSTRING_INDEX( s.day25, ',',- 1 ) AS day25_diff,\n" + "\tSUBSTRING_INDEX( s.day26, ',', 1 ) AS day26_auto,\n" + "\tsubstring_index( substring_index( s.day26, ',', 2 ), ',',- 1 ) AS day26_base,\n" + "\tSUBSTRING_INDEX( s.day26, ',',- 1 ) AS day26_diff,\n" + "\tSUBSTRING_INDEX( s.day27, ',', 1 ) AS day27_auto,\n" + "\tsubstring_index( substring_index( s.day27, ',', 2 ), ',',- 1 ) AS day27_base,\n" + "\tSUBSTRING_INDEX( s.day27, ',',- 1 ) AS day27_diff,\n" + "\tSUBSTRING_INDEX( s.day28, ',', 1 ) AS day28_auto,\n" + "\tsubstring_index( substring_index( s.day28, ',', 2 ), ',',- 1 ) AS day28_base,\n" + "\tSUBSTRING_INDEX( s.day28, ',',- 1 ) AS day28_diff,\n" + "\tSUBSTRING_INDEX( s.day29, ',', 1 ) AS day29_auto,\n" + "\tsubstring_index( substring_index( s.day29, ',', 2 ), ',',- 1 ) AS day29_base,\n" + "\tSUBSTRING_INDEX( s.day29, ',',- 1 ) AS day29_diff,\n" + "\tSUBSTRING_INDEX( s.day30, ',', 1 ) AS day30_auto,\n" + "\tsubstring_index( substring_index( s.day30, ',', 2 ), ',',- 1 ) AS day30_base,\n" + "\tSUBSTRING_INDEX( s.day30, ',',- 1 ) AS day30_diff,\n" + "\tSUBSTRING_INDEX( s.day31, ',', 1 ) AS day31_auto,\n" + "\tsubstring_index( substring_index( s.day31, ',', 2 ), ',',- 1 ) AS day31_base,\n" + "\tSUBSTRING_INDEX( s.day31, ',',- 1 ) AS day31_diff, " +
            " SUBSTRING_INDEX( s.total, ',', 1 ) AS auto_total,substring_index( substring_index( s.total, ',', 2 ), ',',- 1 ) AS base_total,SUBSTRING_INDEX( s.total, ',',- 1 ) AS diff_total " +
            "from report_station_rain_constrast s " +
            "<if test=\"dataYearMonth!=null\"> where data_year_month = #{dataYearMonth} </if>" +
            "<if test=\"stationName!=null\"> and station_name = #{stationName}</if></script>")
    ReportStationRainConstrastVo getStation(@Param("stationName") String stationName,@Param("dataYearMonth") String dataYearMonth);
}
