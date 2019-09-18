package com.siti.wisdomhydrologic.operation.mapper;

import com.siti.wisdomhydrologic.operation.entity.RecordDeviceReplace;
import com.siti.wisdomhydrologic.operation.vo.RecordDeviceReplaceVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by dell on 2019/8/23.
 */
public interface RecordDeviceReplaceMapper  extends Mapper<RecordDeviceReplace>{

    @Select("<script>select * from record_device_replace " +
            "<if test=\"createDate!=null\"> where DATE_FORMAT(create_time,'%Y-%m') = #{createDate} </if>" +
            "<if test=\"stationName!=null\"> and station_name like '%${stationName}%'  </if></script>")
    List<RecordDeviceReplaceVo> getAll(@Param("stationName") String stationName, @Param("createDate")String createDate);

    @Delete("delete from record_device_replace where report_id = #{reportId}")
    int deleteData(@Param("reportId") Integer reportId);
}
