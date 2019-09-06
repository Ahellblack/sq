/*
package com.siti.wisdomhydrologic.user.service;


import com.siti.wisdomhydrologic.user.entity.*;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.vo.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by DC on 2019/8/19.
 *
 * @data ${DATA}-14:29
 *//*

@Service
public class UserDetailService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user;
        try {
            user = userMapper.findByUserName(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("服务器异常，登录失败！");
        }
        if (user == null || user.getStatus() == 0) {
            throw new UsernameNotFoundException("用户不存在！");
        } else {
            try {
                List<Role> roles = userMapper.findRole(user.getId());
                List<Permission> menuList = userMapper.findByPermission(userName);   //  获取角色的目录权限
                List<Org> orgList = userMapper.findOrg(user.getId());
                return new UserInfo(user, menuList, roles,orgList);
            } catch (Exception e) {
                throw new UsernameNotFoundException("登录失败，用户角色异常！");
            }
        }
    }


}

*/
