package com.siti.wisdomhydrologic.user.controller;

import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.Role;
import com.siti.wisdomhydrologic.user.mapper.UserOrgRelaMapper;
import com.siti.wisdomhydrologic.user.mapper.UserRoleMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by zyw on 2019/10/12.
 */
@RestController
@RequestMapping("/org")
public class OrgController {

    @Resource
    private UserOrgRelaMapper orgMapper;

    @GetMapping("/getAll")
    public List<Org> getRoleList(){
        return  orgMapper.getRoleList();
    }

}
