package com.siti.wisdomhydrologic.user.controller;

import com.google.common.collect.Lists;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.Permission;
import com.siti.wisdomhydrologic.user.entity.Role;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import com.siti.wisdomhydrologic.util.BASE64Util;
import com.siti.wisdomhydrologic.util.ExceptionUtil;
import com.siti.wisdomhydrologic.util.Md5Utils;
import javafx.beans.binding.ObjectExpression;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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


  /*  *
     * 获取当前登录用户
     *
     * @return

    @PostMapping
    public static User getLoginUserInfo(HttpServletRequest request, HttpServletResponse response) {

        request.getSession(true).getId();
        return (LoginUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }*/
}
