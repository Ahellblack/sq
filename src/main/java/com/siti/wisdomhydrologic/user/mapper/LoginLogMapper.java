package com.siti.wisdomhydrologic.user.mapper;

import com.siti.wisdomhydrologic.user.entity.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by szy on 2017-07-14.
 */
@Mapper
public interface LoginLogMapper {

    /**
     * 根据userid，获取所有登录记录
     * */
    @Select("select * from sys_login_log where userid=#{userid} order by logintime desc")
    List<LoginLog> getAllById(@Param("userid") Long userid);

    @Insert("insert into sys_login_log (userid,username,ip_address,browser_type,login_time)" +
            " values(#{userid},#{username},#{ipaddress},#{browsertype},#{logintime})")
    int saveLoginLog(@Param("userid") Integer userid, @Param("username") String username, @Param("ipaddress") String ipaddress, @Param("browsertype") String browsertype, @Param("logintime") String logintime);
}
