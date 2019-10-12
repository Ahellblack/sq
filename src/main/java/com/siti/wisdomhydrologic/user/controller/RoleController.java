package com.siti.wisdomhydrologic.user.controller;

import com.siti.wisdomhydrologic.user.entity.Role;
import com.siti.wisdomhydrologic.user.mapper.UserRoleMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/10/12.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private UserRoleMapper userRoleMapper;

    @GetMapping("/getAll")
    public List<Role> getRoleList(){
        return  userRoleMapper.getRoleList();
    }

}
