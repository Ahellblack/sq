package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
public interface ReportManageApplicationBrokenMapper extends Mapper<ReportManageApplicationBroken>{

    @Select("select * from report_manage_application_broken")
    List<ReportManageApplicationBroken> getAll();
}
