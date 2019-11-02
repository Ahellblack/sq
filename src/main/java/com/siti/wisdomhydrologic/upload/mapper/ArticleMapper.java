package com.siti.wisdomhydrologic.upload.mapper;

import com.siti.wisdomhydrologic.upload.entity.SysManualLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by dell on 2019/10/31.
 */
public interface ArticleMapper {

    @Select("<script>select * from sys_manual_log where create_by = #{userId} " +
            "<if test = \'status != null\'>and finish_status = #{status}</if></script>")
    List<SysManualLog> getAll(@Param("userId")Integer userId ,@Param("status") Integer status);

    @Insert("INSERT INTO `sys_manual_log`(`message`, `create_time`, `create_by`,`finish_status`) " +
            "VALUES (#{entity.message}, #{entity.createTime}, #{entity.createBy},#{entity.finishStatus})")
    int createNew(@Param("entity") SysManualLog message);

    @Update("update sys_manual_log set message = #{entity.message}," +
            "finish_status =#{entity.finishStatus} " +
            "where id =#{entity.id}")
    int update(@Param("entity") SysManualLog message);


    @Update("delete from  sys_manual_log " +
            "where id =#{entity.id}")
    int delete(@Param("id") Integer id);
}
