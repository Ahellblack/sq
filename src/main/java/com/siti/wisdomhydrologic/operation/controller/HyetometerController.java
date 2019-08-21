package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.mapper.HyetometerMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.HyetometerServiceImpl;
import com.siti.wisdomhydrologic.util.EasyPoiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by dell on 2019/7/26.
 */
@RequestMapping("/hyetometer")
@RestController
public class HyetometerController {



    @Resource
    private HyetometerServiceImpl reportHyetometerService;

    @Resource
    private HyetometerMapper reportHyetometerMapper;

    @RequestMapping("/getAll")
    public List<ReportHyetometerTest> getAll(){
        return reportHyetometerService.getAll();
    }

    @GetMapping("/deleteBy")
    public int deleteBy(@RequestBody Integer reportId){
        return reportHyetometerService.delByReportId(reportId);
    }

    @GetMapping("/deleteChosen")
    public int deleteByList(@RequestBody List<Integer> reportIdList){
        return reportHyetometerService.delByReportIdList(reportIdList);
    }
    @PostMapping("/insert")
    public int insert(@RequestBody ReportHyetometerTest reportHyetometer){
        return reportHyetometerService.insert(reportHyetometer);
    }
    @PostMapping("/update")
    public int update(@RequestBody ReportHyetometerTest reportHyetometer){
        return reportHyetometerService.update(reportHyetometer);
    }

    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) {
        List<ReportHyetometerTest> list = reportHyetometerService.getAll();
        EasyPoiUtil.exportExcel(list, "雨量计滴水实验记录表", "雨量计滴水", ReportHyetometerTest.class, "雨量计滴水实验记录表.xls", response);
    }

}
