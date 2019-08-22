package com.siti.wisdomhydrologic.operation.controller;

import com.github.pagehelper.PageInfo;
import com.siti.wisdomhydrologic.datepull.entity.ConfigSensorSectionModule;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.operation.mapper.ManageDataMantainMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageDataMantainServiceImpl;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import com.siti.wisdomhydrologic.util.EasyPoiUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private DayDataMapper dayDataMapper;
    @Resource
    private ManageDataMantainMapper reportManageDataMantainMapper;

    /**
     * 根据修改日期查询
     *
     * @Param createDate
     * 若createDate为空，默认获取当月的数据
     */
    @GetMapping("/getByCreateDate")
    public PageInfo<ReportManageDataMantain> getByCreateDate(int page, int pageSize, String createDate) {
        return reportManageDataMantainService.getByCreateDate(page,pageSize,createDate);
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

    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response, String createDate) {
        //默认查询本月
        if (createDate == null) {
            createDate = DateOrTimeTrans.Date2TimeString3(new Date());
        }
        List<ReportManageDataMantain> list = reportManageDataMantainMapper.getByCreateDate(createDate);
        EasyPoiUtil.exportExcel(list, "数据修正登记表", "数据修正", ReportManageDataMantain.class, "数据修正登记表.xls", response);
    }

    @GetMapping("/insertAbnormal")
    public int insertAbnormalData(String date) {
        return reportManageDataMantainService.insertAbnormalData(date);
    }

    @PostMapping("/getSelect")
    public Map<Integer,String> getSelect(){
        List<ConfigSensorSectionModule> station = dayDataMapper.getStation();
        Map<Integer,String> map = new HashMap<>();
        station.forEach(s->{
            map.put(s.getStationCode(),s.getSectionName());
        });
        return map;
    }



}
