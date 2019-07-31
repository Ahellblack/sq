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
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public List<ReportHyetometerTest> getAll(){
        return reportHyetometerService.getAll();
    }

    @GetMapping("/deleteBy")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public int deleteBy(@RequestBody Integer reportId){
        return reportHyetometerService.delByReportId(reportId);
    }

    @GetMapping("/deleteChosen")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public int deleteByList(@RequestBody List<Integer> reportIdList){
        return reportHyetometerService.delByReportIdList(reportIdList);
    }
    @PostMapping("/insert")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public int insert(@RequestBody ReportHyetometerTest reportHyetometer){
        return reportHyetometerService.insert(reportHyetometer);
    }
    @PostMapping("/update")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public int update(@RequestBody ReportHyetometerTest reportHyetometer){
        return reportHyetometerService.update(reportHyetometer);
    }

    @PostMapping("/getExcel")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public void getExcel(@RequestBody HttpServletResponse response) {
        List<ReportHyetometerTest> list = reportHyetometerService.getAll();
        EasyPoiUtil.exportExcel(list, "雨量计滴水实验记录表", "雨量计滴水", ReportHyetometerTest.class, "雨量计滴水实验记录表.xls", response);
    }

}
