package com.siti.wisdomhydrologic.user.service;

import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.entity.UserOrg;
import com.siti.wisdomhydrologic.user.entity.UserRole;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.mapper.UserOrgRelaMapper;
import com.siti.wisdomhydrologic.user.mapper.UserRoleMapper;
import java.util.List;

import com.siti.wisdomhydrologic.util.BASE64Util;
import com.siti.wisdomhydrologic.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

/**
 * Created by dell on 2019/10/11.
 */
@Component
public class UserService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserOrgRelaMapper userOrgRelaMapper;


    public void saveOrupdateUser(User user, Integer[] orgIds, Integer [] roleIds, int flag){
        if (flag == 0) {
            String password = user.getPassword();
            //String logPwd = BASE64Util.decode(password);
            password = Md5Utils.encryptString(password);
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
        }
    }

}
