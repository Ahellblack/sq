package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportManageMantain;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageMantainServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2019/7/31.
 **/


@RequestMapping("/manageMantain")
@RestController
public class ManageMantainController {
    @Resource
    private ManageMantainServiceImpl reportManageMantainService;

    /**
     * @Param date xxxx年xx月 格式YYYY-MM
     */
    @GetMapping("/selectByDate")
    public List<ReportManageMantain> getAll(String date) {
        return reportManageMantainService.getAll(date);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ReportManageMantain reportManageMantain) {
        return reportManageMantainService.insert(reportManageMantain);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageMantain reportManageMantain) {
        return reportManageMantainService.update(reportManageMantain);
    }
    @GetMapping("/delete")
    public int delete(Integer ReportId){
        return reportManageMantainService.delete(ReportId);
    }

}
