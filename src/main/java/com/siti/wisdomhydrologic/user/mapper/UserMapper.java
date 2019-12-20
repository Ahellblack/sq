package com.siti.wisdomhydrologic.user.mapper;

import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.Permission;
import com.siti.wisdomhydrologic.user.entity.Role;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.vo.UserVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-13:18
 */
public interface UserMapper extends Mapper<User> {

    @Select("<script>select * from sys_user where user_name=#{name}</script>")
    User findByUserName(@Param("name") String name);

    @Select("<script>select * from sys_permission sp where FIND_IN_SET(id,(select permission_id from sys_user su LEFT JOIN sys_user_role sur on su.id=sur.uid \n" +
            "LEFT JOIN sys_role sr on sur.rid=sr.id\n" +
            "LEFT JOIN sys_role_permission srp on srp.role_id=sr.id\n" +
            "where su.user_name=#{name} ))  \n</script>")
    List<Permission> findByPermission(@Param("name") String name);

    @Select("<script>select * from sys_role sr LEFT JOIN sys_user_role sur on sr.id=sur.rid  " +
            "where sur.uid = #{id}</script>")
    List<Role> findRole(@Param("id") int id);

    @Select("<script>select * from sys_org so LEFT JOIN sys_user_org suo on suo.org_id=so.id " +
            "where suo.uid = #{id}</script>")
    List<Org> findOrg(@Param("id") int id);

    @Select("select id from sys_org where find_in_set((select org_id from sys_user_org where uid = #{id}), path) ")
    List<Integer> getOrgIdList(@Param("id") int id);

    /**
     * 只查询角色为运维人员的数据
     * */
    @Select("select su.real_name from sys_user su " +
            "left join sys_user_org suo on su.id = suo.uid " +
            "left join sys_org so on suo.org_id = so.id " +
            "left join sys_user_role sur on su.id = sur.uid " +
            "left join sys_role sr on sur.rid = sr.id " +
            "WHERE (so.id = #{orgId} OR FIND_IN_SET( #{orgId},so.path)) " +
            "and  sur.rid = 3")
    List<String> findMaintainer(@Param("orgId")Integer orgId);


    /**
     * 新增用户
     * */
    @Options(useGeneratedKeys = true, keyProperty = "id")      // 返回主键
    @Insert("insert into sys_user (user_name,password,real_name,update_by,phone_num,sex) values" +
            "(#{user.userName},#{user.password},#{user.realName},#{user.updateBy},#{user.phoneNum},#{user.sex})")
    int saveUser(@Param("user") User user);

    /**
     * 更新用户信息
     * */
    @Update("update sys_user set user_name=#{user.userName},real_name=#{user.realName}," +
            "update_by=#{user.updateBy},sex=#{user.sex},phone_num = #{user.phoneNum} where id=#{user.id}")
    void updateUser(@Param("user")User user);

    /**
     * 修改密码
     * */
    @Update("update sys_user set password=#{user.password} " +
            " where id=#{user.id}")
    void updatePwd(@Param("user")User user);


    /**
     * 根据用户Id，删除用户信息
     * */
    @Delete("delete from sys_user where id=#{id}")
    void deleteUserInfo(@Param("id")Integer id);

    @Select("<script>select su.*,sr.id as rid,sr.descr,suo.is_leader,so.id as oid,so.name,so.path " +
            "from sys_user su " +
            " left join sys_user_org suo on su.id = suo.uid " +
            " left join sys_org so on suo.org_id = so.id " +
            " left join sys_user_role sur on su.id = sur.uid " +
            " left join sys_role sr on sur.rid = sr.id " +
            "where user_name like '%${username}%' </script>")
    List<UserVo> getAll(@Param("username")String username);
}
