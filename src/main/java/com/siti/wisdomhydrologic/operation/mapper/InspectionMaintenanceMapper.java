package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportInspectionMaintenance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
public interface InspectionMaintenanceMapper {

    @Select("<script> select * from report_inspection_maintenance " +
            "<if test=\"stationId!=null\"> where station_code = #{stationId} </if>")
    List<ReportInspectionMaintenance> getByStationId(@Param("stationId") String stationId);
}
