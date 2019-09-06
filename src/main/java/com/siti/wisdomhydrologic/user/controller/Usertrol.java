/*
package com.siti.wisdomhydrologic.user.controller;

import com.google.common.collect.Lists;
import com.siti.wisdomhydrologic.user.entity.Permission;
import com.siti.wisdomhydrologic.user.vo.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

*/
/**
 * Created by DC on 2019/8/21.
 *
 * @data ${DATA}-16:22
 *//*

@RestController
@RequestMapping("login")
public class Usertrol {
    */
/**
     * 获取当前登录用户
     *
     * @return
     *//*

    @PostMapping
    public static UserInfo getLoginUserInfo() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
       UserInfo info= (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Permission> all=info.getMenuList();
        Permission last=new Permission(){{
            setId(1);
            setDescription("WH");
            setSort(0);
            setChild(new ArrayList<Permission>());
        }};
        backToFront(0,last,all);
        List<Permission> p=new ArrayList<>();
        p.add(last);
        info.setMenuList(p);
        return info;
    }

    public static void backToFront(int root, Permission finalP, List<Permission> all) {
        int next = root + 1;
        List<Permission> child= Lists.newArrayList();
        for(Permission one:all){
            String[] str= one.getPath().split(",");
            if(str.length>root){
                if(str[root].equals(finalP.getId() + "")&& one.getSort() == next){
                    child.add(one);
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
*/
