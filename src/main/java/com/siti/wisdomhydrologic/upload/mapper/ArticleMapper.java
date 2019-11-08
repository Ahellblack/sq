package com.siti.wisdomhydrologic.upload.mapper;

import com.siti.wisdomhydrologic.upload.entity.SysManualLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by dell on 2019/10/31.
 */
public interface ArticleMapper {

    @Select("<script>select * from sys_manual_log where create_by = #{userId} " +
            "<if test = \"status != null and status!= ''\">and finish_status = #{status}</if>" +
            "<if test = \"date != null and date!= ''\">and DATE_FORMAT(create_time,'%Y-%m-%d') = #{date}</if>" +
            "<if test = \"content != null and content!= ''\">and message like '%${content}%'</if>" +
            "</script>")
    List<SysManualLog> getAll(@Param("userId")Integer userId ,
                              @Param("status") Integer status,
                              @Param("date")String date,
                              @Param("content")String content);

    @Insert("INSERT INTO `sys_manual_log`(`message`, `create_time`, `create_by`,`finish_status`) " +
            "VALUES (#{entity.message}, #{entity.createTime}, #{entity.createBy},#{entity.status})")
    int createNew(@Param("entity") SysManualLog message);

    @Update("update sys_manual_log set message = #{entity.message}," +
            "finish_status =#{entity.status} " +
            "where id =#{entity.id}")
    int update(@Param("entity") SysManualLog message);


    @Delete("delete from  sys_manual_log " +
            "where id =#{id}")
    int delete(@Param("id") Integer id);
}
