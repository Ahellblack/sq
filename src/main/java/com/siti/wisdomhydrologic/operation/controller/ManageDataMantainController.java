package com.siti.wisdomhydrologic.operation.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportHyetometerTest;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageDataMantainServiceImpl;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.EasyPoiUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/7/30.
 */
@RequestMapping("/manageDataMantain")
@RestController
public class ManageDataMantainController {
    @Resource
    private ManageDataMantainMapper manageDataMantainMapper;
    @Resource
    private ManageDataMantainServiceImpl reportManageDataMantainService;

    /**
     * 根据修改日期查询 @Param createDate
     * */
    @GetMapping("/getByCreateDate")
    public List<ReportManageDataMantain> getByCreateDate(Date createDate) {
        return reportManageDataMantainService.getByCreateDate(createDate);
    }
    @GetMapping("/delete")
    public int delete(Integer reportId) {
        return reportManageDataMantainService.delete(reportId);
    }

    @PostMapping("/update")
    public int update(@RequestBody ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainService.update(reportManageDataMantain);
    }
    @PostMapping("/insert")
    public int insert(@RequestBody ReportManageDataMantain reportManageDataMantain) {
        return reportManageDataMantainService.insert(reportManageDataMantain);
    }
    @PostMapping("/getExcel")
    public void getExcel(HttpServletResponse response, Date createDate) {
        List<ReportManageDataMantain> list = reportManageDataMantainService.getByCreateDate(createDate);
        EasyPoiUtil.exportExcel(list, "数据修正登记表", "数据修正", ReportManageDataMantain.class, "数据修正登记表.xls", response);
    }


}
