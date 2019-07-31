package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportInspectionMaintenance;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
public interface InspectionMaintenanceMapper extends Mapper<ReportInspectionMaintenance>{

    @Select("<script> select * from report_inspection_maintenance " +
            "<if test=\"stationId!=null\"> where station_code = #{stationId}</if></script>")
    List<ReportInspectionMaintenance> getByStationId(@Param("stationId") String stationId);

    @Delete("delete from report_inspection_maintenance where report_id = #{ReportId}")
    int deleteByReportId(@Param("ReportId") Integer ReportId);
}
