package com.siti.wisdomhydrologic.user.mapper;

import com.siti.wisdomhydrologic.user.entity.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by szy on 2017-07-13.
 */
@Mapper
public interface UserRoleMapper {
    @Select("SELECT * FROM sys_user_role_rela WHERE user_id = #{user_id}")
    List<UserRole> getRoleByUser(@Param("user_id") Long user_id);

    /**
     * 根据用户id获取相关角色id
     * */
    @Select("select role_id from sys_user_role_rela where user_id=#{userid}")
    List<Long> getRoleIdByUserId(@Param("userid") Long userid);



    /**
     * 根据用户id删除角色-用户关联
     * */
    @Delete("delete from sys_user_role where uid=#{userId}")
    void deleteUserRoleByUserId(@Param("userId") Integer userId);

    /**
     * 根据角色id删除角色-用户关联
     * */
    @Delete("delete from sys_user_role where role_id=#{roleId}")
    void deleteUserRoleByRoleId(@Param("roleId") Integer roleId);

    /**
     * 保存用户-角色关联
     * */
    @Insert({"<script>",
            "insert into sys_user_role (uid,rid) values " +
            "<foreach item=\"item\" collection=\"userRole\"  index=\"index\" separator=\",\">" +
             "(#{item.uid},#{item.rid})" +
            "</foreach>",
            "</script>"})
    void saveUserRole(@Param("userRole") List<UserRole> userRole);

    /**
     * 更新用户-角色关联
     * */
    @Insert({"<script>",
                "replace into sys_user_role (uid,rid) values" +
                       "<foreach item=\"item\" collection=\"userRole\"  index=\"index\" separator=\",\">" +
                                "(#{item.uid},#{item.rid})" +
                       "</foreach>",
            "</script>"})
    void saveOrupdateBatchUserRole(@Param("userRole") List<UserRole> userRole);

    /***
     * 获取角色用户信息
     * */
    @Select("select * from sys_user_role where rid=#{role_id}")
    List<UserRole> getRoleUser(@Param("role_id") Long role_id);
}
