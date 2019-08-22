package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/8/21.
 */
@RestController
@RequestMapping("/malfunction")
public class MalFunctionController {

    @Resource
    private ManageApplicationBrokenMapper manageApplicationBrokenMapper;

    @GetMapping("/getLatest10")
    public List<ReportManageApplicationBroken> getList() {

        return manageApplicationBrokenMapper.getLatest10();
    }

}
