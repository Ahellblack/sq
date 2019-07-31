package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.mapper.ReportHyetometerMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ReportHyetometerServiceImpl;
import com.siti.wisdomhydrologic.util.EasyPoiUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by dell on 2019/7/26.
 */
@RequestMapping("/hyetometer")
@RestController
public class ReportHyetometerController {

    @Resource
    private ReportHyetometerServiceImpl reportHyetometerService;

    @Resource
    private ReportHyetometerMapper reportHyetometerMapper;

    @RequestMapping("/getAll")
    public List<ReportHyetometerTest> getAll(){
        return reportHyetometerService.getAll();
    }

    @GetMapping("/deleteBy")
    public int deleteBy(Integer reportId){
        return reportHyetometerService.delByReportId(reportId);
    }

    @GetMapping("/deleteChosen")
    public int deleteByList(List<Integer> reportIdList){
        return reportHyetometerService.delByReportIdList(reportIdList);
    }
    @PostMapping("/insert")
    public int insert(ReportHyetometerTest reportHyetometer){
        return reportHyetometerService.insert(reportHyetometer);
    }
    @PostMapping("/update")
    public int update(ReportHyetometerTest reportHyetometer){
        return reportHyetometerService.update(reportHyetometer);
    }

    @PostMapping("/getExcel")
    public void getExcel(HttpServletResponse response) {
        List<ReportHyetometerTest> list = reportHyetometerService.getAll();
        EasyPoiUtil.exportExcel(list, "雨量计滴水实验记录表", "雨量计滴水", ReportHyetometerTest.class, "雨量计滴水实验记录表.xls", response);
    }

}
