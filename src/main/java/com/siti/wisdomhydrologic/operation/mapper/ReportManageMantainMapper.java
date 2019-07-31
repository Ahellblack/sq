package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ReportManageMantainMapper extends Mapper<ReportManageMantain> {
    @Select("select * from report_manage_mantain where mantain_month = #{date}")
    List<ReportManageMantain> getByDate(@Param("date") String date);

    @Delete("delete from report_manage_mantain where report_id = #{reportId}")
    int deleteById(@Param("reportId") Integer reportId);
}
