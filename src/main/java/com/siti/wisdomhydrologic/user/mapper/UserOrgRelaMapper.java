package com.siti.wisdomhydrologic.user.mapper;

import com.siti.wisdomhydrologic.user.entity.UserOrg;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by szy on 2017-07-21.
 */
@Mapper
public interface UserOrgRelaMapper {
    @Select("SELECT * FROM sys_user_org_rela WHERE user_id = #{user_id}")
    List<UserOrg> getOrgByUser(@Param("user_id") Long user_id);

    /**
     * 根据用户id获取相关组织id
     * */
    @Select("select org_id from sys_user_org_rela where user_id=#{userid}")
    List<Long> getOrgIdByUserId(@Param("userid") Long userid);


    /**
     * 根据用户id删除组织-用户关联
     * */
    @Delete("delete from sys_user_org where uid=#{userId}")
    void deleteUserOrgByUserId(@Param("userId") Integer userId);

    /**
     * 根据组织id删除组织-用户关联
     * */
    @Delete("delete from sys_user_org where org_id=#{org_id}")
    void deleteUserOrgByRoleId(@Param("org_id") Integer org_id);

    /**
     * 保存用户-组织关联
     * */
    @Insert({"<script>","insert into sys_user_org (user_id,org_id) values " +
            "<foreach collection=\"userOrg\" item=\"item\" index=\"index\" separator=\",\">" +
                    "(#{item.user_id},#{item.org_id})" +
            "</foreach>",
            "</script>"})
    void saveUserOrg(@Param("userOrg") List<UserOrg> userOrg);

    /**
     * 更新用户-组织关联
     * */
     @Insert({"<script>","replace into sys_user_org (uid,org_id) values " +
                  "<foreach collection=\"userOrg\" item=\"item\" index=\"index\" separator=\",\">" +
                    "(#{item.uid},#{item.orgId})" +
                 "</foreach>",
            "</script>"})
    void saveOrupdateBatchUserOrg(@Param("userOrg") List<UserOrg> userOrg);
}
