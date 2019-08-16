package com.siti.wisdomhydrologic.operation.controller;

import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageApplicationBrokenServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 */
@RestController
@RequestMapping("/applicationBroken")
public class ManageApplicationBrokenController {

    @Resource
    private ManageApplicationBrokenServiceImpl manageApplicationBrokenService;

    @GetMapping("/getAll")
    public PageInfo<ReportManageApplicationBroken> selectAll(int page, int pageSize, String createDate) {
        return manageApplicationBrokenService.getAll(page,pageSize,createDate);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportManageApplicationBroken reportManageApplicationBroken) {
        return manageApplicationBrokenService.insert(reportManageApplicationBroken);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageApplicationBroken reportManageApplicationBroken) {
        return manageApplicationBrokenService.update(reportManageApplicationBroken);
    }

    @GetMapping("/delete")
    public int delete(Integer reportId) {
        return manageApplicationBrokenService.delete(reportId);
    }

    @GetMapping("/insertDataMantain")
    public int insertDataMantain(String date){
        return manageApplicationBrokenService.insertDataMantain(date);
    }

}
