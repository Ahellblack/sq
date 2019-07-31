package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by dell on 2019/7/31.
 */
public interface ReportStationBrokenMapper extends Mapper<ReportStationBroken>{
    @Delete("delete from report_station_broken where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);
}
