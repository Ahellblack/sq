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

    @Select("select * from sys_manual_log where create_by = #{userId}")
    List<SysManualLog> getAll(@Param("userId")Integer userId );

    @Insert("INSERT INTO `sys_manual_log`(`message`, `create_time`, `create_by`) " +
            "VALUES (#{entity.message}, #{entity.createTime}, #{entity.createBy})")
    int createNew(@Param("entity") SysManualLog message);

    @Update("update sys_manual_log set message = #{entity.message} " +
            "where id =#{entity.id}")
    int update(@Param("entity") SysManualLog message);


    @Update("delete from  sys_manual_log " +
            "where id =#{entity.id}")
    int delete(@Param("id") Integer id);
}
