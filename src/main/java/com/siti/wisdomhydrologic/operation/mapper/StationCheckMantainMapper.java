package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportStationCheckMantain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by dell on 2019/7/31.
 */
public interface StationCheckMantainMapper extends Mapper<ReportStationCheckMantain>{

    @Delete("delete from report_station_check_mantain where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);
}