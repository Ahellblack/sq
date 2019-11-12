package com.siti.wisdomhydrologic.user.controller;

import com.google.common.collect.Lists;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.Permission;
import com.siti.wisdomhydrologic.user.entity.Role;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.LoginLogMapper;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.mapper.UserOrgRelaMapper;
import com.siti.wisdomhydrologic.user.mapper.UserRoleMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.user.service.UserService;
import com.siti.wisdomhydrologic.user.vo.UserVo;
import com.siti.wisdomhydrologic.util.BASE64Util;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.ExceptionUtil;
import com.siti.wisdomhydrologic.util.Md5Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.beans.binding.ObjectExpression;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-16:22
 */
@RestController
@Component
public class Usertrol {
    private static final long timeLong = 6000;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;
    @Resource
    UserService userService;

    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserOrgRelaMapper userOrgRelaMapper;

    /**
     * 获取当前登录用户
     *
     * @return
     */
    @PostMapping("login1")
    public Object g(HttpSession session, @Param("username") String username, @Param("password") String password) {
        return null;
    }

    @GetMapping("getUser")
    public User getUsername() {
        try {
            User user = (User) userInfoService.get();
            return user;
        } catch (Exception e) {
            System.out.println("用户信息未登录");
        }
        return null;
    }

    @PostMapping("login")
    public Map<String, Object> getLoginUserInfo(HttpSession session, @Param("username") String username, @Param("password") String password) {
        return null;
    }

    /***
     * 新增用户
     */
    @ApiOperation(value = "查询用户", notes = "")
    @RequestMapping(value = "user/selectUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser(String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<UserVo> list = userMapper.getAll(username);
            map.put("list", list);
            map.put("status", 1);
            map.put("message", "添加成功");
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", e.getLocalizedMessage());
        }
        return map;
    }


    /***
     * 新增用户
     */
    @ApiOperation(value = "新增用户", notes = "")
    @RequestMapping(value = "user/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveUser(@RequestBody User user, @ApiParam(required = true) Integer[] organizationIds, @ApiParam(required = true) Integer[] roleIds) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer isLeader = user.getIsLeader();
            //获取当前用户
            User loginUser = (User) userInfoService.get();

            //判断添加权限
            List<Role> roles = userMapper.findRole(loginUser.getId());
            List<Integer> list = new ArrayList<>();
            roles.forEach(role -> {
                if (role.getId() == 1) {
                    list.add(role.getId());
                }
            });
            //登录用户可修改当前账户的信息。
            if(loginUser.getId() == user.getId()){
                list.add(1);
            }
            if (list.size() == 0) {
                map.put("status", -1);
                map.put("message", "无添加权限");
                return map;
            }
            user.setPassword(user.getPassword());
            //设置添加用户
            user.setUpdateBy(loginUser.getUserName());
            //FLAG为0即添加 1为修改
            userService.saveOrupdateUser(user, organizationIds, roleIds, 0, isLeader);
            map.put("status", 1);
            map.put("message", "添加成功");
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", e.getLocalizedMessage());
        }
        return map;
    }

    /**
     * 更新用户信息,但不更新用户密码
     */
    @ApiOperation(value = "更新用户信息,但不更新用户密码")
    @RequestMapping(value = "user/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody User user, Integer[] organizationIds, Integer[] roleIds) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer isLeader = user.getIsLeader();
            //获取当前用户
            User loginUser = (User) userInfoService.get();
            //判断添加权限
            List<Role> roles = userMapper.findRole(loginUser.getId());
            List<Integer> list = new ArrayList<>();
            roles.forEach(role -> {
                if (role.getId() == 1) {
                    list.add(role.getId());
                }
            });
            //登录用户可修改当前账户的信息。
            if(loginUser.getId() == user.getId()){
                list.add(1);
            }
            if (list.size() == 0) {
                map.put("status", -1);
                map.put("message", "无修改权限");
                return map;
            }
            user.setPassword(user.getPassword());
            //设置添加用户
            user.setUpdateBy(loginUser.getUserName());
            userService.saveOrupdateUser(user, organizationIds, roleIds, 1, isLeader);
            map.put("status", 1);
            map.put("message", "修改成功");
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", e.getLocalizedMessage());
        }
        return map;
    }

    /**
     * 修改密码
     */
    @ApiOperation(value = "修改密码", notes = "xx")
    @RequestMapping(value = "user/modifyPwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyPwd(Integer id,String password) {
        //base64
        String logPwd = BASE64Util.decode(password);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //获取当前用户
            User loginUser = (User) userInfoService.get();
            List<Integer> list = new ArrayList<>();
            List<Role> roles = userMapper.findRole(loginUser.getId());
            roles.forEach(role -> {
                if (role.getId() == 1) {
                    list.add(role.getId());
                }
            });
            //登录用户可修改当前账户的密码。
            if(loginUser.getId() == id){
                list.add(1);
            }
            if (list.size() == 0) {
                map.put("status", -1);
                map.put("message", "无修改权限");
                return map;
            }

            //加密
            password = Md5Utils.encryptString(logPwd);
            User updateUser = new User();
            updateUser.setId(id);
            updateUser.setPassword(password);
            userMapper.updatePwd(updateUser);
            map.put("status", 1);
            map.put("message", "修改成功");
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", e.getLocalizedMessage());
        }
        return map;
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户", notes = "")
    @RequestMapping(value = "user/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUser(Integer userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        //判断添加权限
        User loginUser = (User) userInfoService.get();
        List<Role> roles = userMapper.findRole(loginUser.getId());
        List<Integer> list = new ArrayList<>();
        roles.forEach(role -> {
            if (role.getId() == 1) {
                list.add(role.getId());
            }
        });
        if (list.size() == 0) {
            map.put("status", -1);
            map.put("message", "无删除权限");
            return map;
        }
        try {
            // 删除角色关系
            userRoleMapper.deleteUserRoleByUserId(userid);
            // 删除组织关系
            userOrgRelaMapper.deleteUserOrgByUserId(userid);
            // 删除用户信息
            userMapper.deleteUserInfo(userid);
            map.put("status", 1);
            map.put("message", "删除成功");
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", e.getLocalizedMessage());
        }
        return map;
    }


}
