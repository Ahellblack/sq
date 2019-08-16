package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ManageApplicationBrokenMapper extends Mapper<ReportManageApplicationBroken>{

    @Select("<script>select * from report_manage_application_broken" +
            "<if test=\"createDate!=null\">where DATE_FORMAT(create_time,'%Y-%m') = #{createDate}</if> </script>")
    List<ReportManageApplicationBroken> getAll(@Param("createDate") String createDate);

    @Delete("DELETE from report_manage_application_broken where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);

    @Insert("<script>INSERT INTO `wisdomhydrologic`.`report_manage_application_broken`" +
            "(`station_id`, `station_name`, `broken_name`, `broken_according_id`, " +
            "`broken_according`, `broken_response_time`, `create_time`,`resolve_method`, `resolve_user_id`, `remark`)" +
            " VALUES <foreach collection=\"brokenList\" item=\"item\" separator=\",\">" +
            "(#{item.stationId},#{item.stationName}, #{item.brokenName}, #{item.brokenAccordingId}," +
            " #{item.brokenAccording}, #{item.brokenResponseTime}, #{item.createTime},#{item.resolveMethod}, #{item.resolveUserId}, #{item.remark})</foreach></script>")
    int insertDataMantain(@Param("brokenList") List<ReportManageApplicationBroken> brokenList);
}
