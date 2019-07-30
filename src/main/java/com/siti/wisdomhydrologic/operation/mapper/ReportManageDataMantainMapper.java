package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
public interface ReportManageDataMantainMapper extends Mapper<ReportManageDataMantain>{
    @Select("<script>Select * from report_manage_data_mantain" +
            "<if test=\"createDate!=null\"> where alter_date = #{createDate} </if>" +
            "</script>")
    List<ReportManageDataMantain> getByCreateDate(@Param("createDate") Date createDate);
}
