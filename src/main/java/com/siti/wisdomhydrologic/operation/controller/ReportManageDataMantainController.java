package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.service.Impl.ReportManageDataMantainServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
@RequestMapping("/manageDataMantain")
@RestController
public class ReportManageDataMantainController {

    @Resource
    private ReportManageDataMantainServiceImpl reportManageDataMantainService;

    @GetMapping("/getByCreateDate")
    public List<ReportManageDataMantain> getByCreateDate(Date createDate) {
        return reportManageDataMantainService.getByCreateDate(createDate);
    }
    @GetMapping("/delete")
    public int delete(Integer reportId){
        return reportManageDataMantainService.delete(reportId);
    }
    @PostMapping("/update")
    public int update(ReportManageDataMantain reportManageDataMantain){
        return reportManageDataMantainService.update(reportManageDataMantain);
    }
    @PostMapping("/insert")
    public int insert(ReportManageDataMantain reportManageDataMantain){
        return reportManageDataMantainService.insert(reportManageDataMantain);
    }


}
