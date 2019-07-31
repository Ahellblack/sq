package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.service.Impl.ReportManageApplicationBrokenServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@RestController
@RequestMapping("/applicationBroken")
public class ReportManageApplicationBrokenController {

    @Resource
    private ReportManageApplicationBrokenServiceImpl manageApplicationBrokenService;

    @GetMapping("/getAll")
    public List<ReportManageApplicationBroken> selectAll(){
        return manageApplicationBrokenService.getAll();
    }
    @PostMapping("/insert")
    public int insert(ReportManageApplicationBroken reportManageApplicationBroken){
        return manageApplicationBrokenService.insert(reportManageApplicationBroken);
    }
    @PostMapping("/update")
    public int update(ReportManageApplicationBroken reportManageApplicationBroken){
        return manageApplicationBrokenService.update(reportManageApplicationBroken);
    }
    @GetMapping("/delete")
    public int delete(Integer reportId){
        return manageApplicationBrokenService.delete(reportId);
    }

}
