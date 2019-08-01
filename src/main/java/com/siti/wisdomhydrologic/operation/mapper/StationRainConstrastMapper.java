package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportStationRainConstrast;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/1.
 */
public interface StationRainConstrastMapper {
    @Select("<script>select * from report_station_rain_constrast " +
            "<if test=\"date!=null\"> where data_year_month = #{date}</if></script>")
    List<ReportStationRainConstrast> getByMonth(@Param("date") Date date);
}
