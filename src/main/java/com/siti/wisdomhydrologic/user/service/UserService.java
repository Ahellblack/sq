package com.siti.wisdomhydrologic.user.service;

import com.google.common.collect.Lists;
import com.siti.wisdomhydrologic.user.entity.*;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.mapper.UserOrgRelaMapper;
import com.siti.wisdomhydrologic.user.mapper.UserRoleMapper;
import java.util.List;

import com.siti.wisdomhydrologic.util.BASE64Util;
import com.siti.wisdomhydrologic.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by dell on 2019/10/11.
 */
@Component
public class UserService implements UserDetailsService{


    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserOrgRelaMapper userOrgRelaMapper;

    private final String password =  Md5Utils.encryptString("123456");

    public void saveOrupdateUser(User user, Integer[] orgIds, Integer [] roleIds, int flag,Integer isLeader){
        if (flag == 0) {
            user.setPassword(password);
            userMapper.saveUser(user);
        }else{
            //查到用户id
            user.setId(userMapper.findByUserName(user.getUserName()).getId());
            userMapper.updateUser(user);
            userRoleMapper.deleteUserRoleByUserId(user.getId());
            userOrgRelaMapper.deleteUserOrgByUserId(user.getId());
        }

        //查到用户id
        user.setId(userMapper.findByUserName(user.getUserName()).getId());
        //设置
        if(roleIds.length>0){
            List<UserRole> list = new ArrayList<UserRole>();
            for(int i=0;i<roleIds.length;i++){
                list.add(new UserRole(user.getId(),roleIds[i]));
            }
            userRoleMapper.saveOrupdateBatchUserRole(list);
        }
        if(orgIds.length>0){
            List<UserOrg> list = new ArrayList<UserOrg>();
            for(int i=0;i<orgIds.length;i++){
                list.add(new UserOrg(user.getId(),orgIds[i]));
            }
            userOrgRelaMapper.saveOrupdateBatchUserOrg(list);
            if (isLeader != null){
                userOrgRelaMapper.updateIsLeader(user.getId(),isLeader);
            }
        }


    }

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
                List<Permission> last = menuList.stream().filter(e -> e.getSort() == 0).collect(Collectors.toList());
                backToFront(0, last.get(0), menuList);
                user.setMenuList(last);
                user.setOrgList(orgList);
                user.setRoles(roles);
            } catch (Exception e) {
                throw new UsernameNotFoundException("登录失败，用户角色异常！");
            }
        }
        return user;
    }

    public static void backToFront(int root, Permission finalP, List<Permission> all) {
        int next = root + 1;
        List<Permission> child = Lists.newArrayList();
        for (Permission one : all) {
            if (one.getSort() != 0) {
                String[] str = one.getPath().split(",");
                if (str.length > root) {
                    if (str[root].equals(finalP.getId() + "") && one.getSort() == next) {
                        child.add(one);
                    }
                }
            }
        }
        if (child == null || child.size() < 1) {
            return;
        }
        finalP.setChild(child);
        child.stream().forEach(e -> {
            backToFront(next, e, all);
        });
    }
}
