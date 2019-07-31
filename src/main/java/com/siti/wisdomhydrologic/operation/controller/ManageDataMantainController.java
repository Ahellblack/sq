package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageDataMantainServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class ManageDataMantainController {

    @Resource
    private ManageDataMantainServiceImpl reportManageDataMantainService;

    @GetMapping("/getByCreateDate")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public List<ReportManageDataMantain> getByCreateDate(Date createDate) {
        return reportManageDataMantainService.getByCreateDate(createDate);
    }

    @GetMapping("/delete")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public int delete(Integer reportId) {
        return reportManageDataMantainService.delete(reportId);
    }

    @PostMapping("/update")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public int update(ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainService.update(reportManageDataMantain);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public int insert(ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainService.insert(reportManageDataMantain);
    }


}
