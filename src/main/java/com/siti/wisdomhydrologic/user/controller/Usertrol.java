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
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import com.siti.wisdomhydrologic.user.service.UserService;
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
    private RedisBiz redisBiz;
    @Resource
    private UserMapper userMapper;
    @Autowired
    LoginLogMapper loginLogMapper;
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
    public User getUsername (HttpSession session){
        User user = (User)redisBiz.get(session.getId());
        return user;
    }

    @PostMapping("login")
    public Map<String,Object> getLoginUserInfo(HttpSession session, @Param("username") String username, @Param("password") String password) {
        RequestAttributes ra=RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=((ServletRequestAttributes)ra).getRequest();
        request.getSession(true).setAttribute("keytest","testvalue");

        System.out.println(session.getId());
        try {
            if(password==""||"".equals(password)){
                Map<String,Object> map = new HashMap();
                map.put("msg","密码错误");
                map.put("status",0);
                return map;
            }
            //redisBiz.get(session.getId());
            String logPwd = BASE64Util.decode(password);
            password = Md5Utils.encryptString(logPwd);
            if (logPwd == null) {
                Map<String,Object> map = new HashMap();
                map.put("msg","密码错误");
                map.put("status",0);
                return map;
            }
            User user = userMapper.findByUserName(username);
            if (user.getPassword().equals(password)) {
                List<Role> roles = userMapper.findRole(user.getId());
                List<Permission> menuList = userMapper.findByPermission(username);   //  获取角色的目录权限
                List<Org> orgList = userMapper.findOrg(user.getId());
                List<Permission> last = menuList.stream().filter(e -> e.getSort() == 0).collect(Collectors.toList());
                backToFront(0, last.get(0), menuList);
                user.setMenuList(last);
                user.setOrgList(orgList);
                user.setRoles(roles);
                if (session.getId() != "" && !"".equals(session.getId())) {
                    redisBiz.set(session.getId(), user, timeLong);
                    //System.out.println(redisBiz.get(session.getId()));
                    Map<String,Object> map = new HashMap();
                    map.put("user",user);
                    map.put("status",1);
                    String ip_address = getIpAddress(request);
                    String userAgent = request.getHeader("User-Agent"); // 浏览器信息
                    loginLogMapper.saveLoginLog(user.getId(),user.getUserName(),ip_address,userAgent,(DateOrTimeTrans.nowTimetoString()));
                    return map;
                }
            }
        } catch (Exception e) {
            Map<String,Object> map = new HashMap();
            map.put("msg","输入的账户或密码有误");
            map.put("status",0);
            return map;
        }
        Map<String,Object> map = new HashMap();
        map.put("msg","输入的账户或密码有误");
        map.put("status",0);
        return map;
    }

    /***
     * 新增用户
     */
    @ApiOperation(value = "新增用户",notes = "")
    @RequestMapping(value="user/saveUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveUser(@RequestBody User user, @ApiParam(required = true)Integer[] organizationIds, @ApiParam(required = true)Integer [] roleIds,HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            //获取当前用户
            User loginUser = (User)redisBiz.get(session.getId());
            user.setPassword(user.getPassword());
            //设置添加用户
            user.setUpdateBy(loginUser.getUserName());
            //FLAG为0即添加 1为修改
            userService.saveOrupdateUser(user,organizationIds,roleIds,0);
            map.put("status",0);
            map.put("message","添加成功");
        }catch (Exception e){
            map.put("status",-1);
            map.put("message",e.getLocalizedMessage());
        }
        return map;
    }

    /**
     * 更新用户信息,但不更新用户密码
     * */
    @ApiOperation(value = "更新用户信息,但不更新用户密码")
    @RequestMapping(value = "user/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUser(@RequestBody User user,Integer[] organizationIds,Integer [] roleIds,HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            //获取当前用户
            User loginUser = (User)redisBiz.get(session.getId());
            user.setPassword(user.getPassword());
            //设置添加用户
            user.setUpdateBy(loginUser.getUserName());
            userService.saveOrupdateUser(user,organizationIds,roleIds,1);
            map.put("status",0);
            map.put("message","修改成功");
        }catch (Exception e){
            map.put("status",-1);
            map.put("message",e.getLocalizedMessage());
        }
        return map;
    }
    /**
     * 修改密码
     * */
    @ApiOperation(value = "修改密码",notes = "xx")
    @RequestMapping(value="user/modifyPwd",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyPwd(Integer id, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            User loginUser = (User)redisBiz.get(session.getId());
            String updateBy = loginUser.getUserName();

            User user = new User();
            user.setId(id);
            //加密
            password = Md5Utils.encryptString(password);
            user.setPassword(password);
            user.setUpdateBy(updateBy);
            userMapper.updatePwd(user);
            map.put("status",0);
            map.put("message","添加成功");
        }catch (Exception e){
            map.put("status",-1);
            map.put("message",e.getLocalizedMessage());
        }
        return map;
    }
    /**
     * 删除用户
     * */
    @ApiOperation(value = "删除用户",notes = "")
    @RequestMapping(value="user/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteUser(Integer userid){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            // 删除角色关系
            userRoleMapper.deleteUserRoleByUserId(userid);
            // 删除组织关系
            userOrgRelaMapper.deleteUserOrgByUserId(userid);
            // 删除用户信息
            userMapper.deleteUserInfo(userid);
            map.put("status",0);
            map.put("message","删除成功");
        }catch (Exception e){
            map.put("status",-1);
            map.put("message",e.getLocalizedMessage());
        }
        return map;
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
    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }



}
