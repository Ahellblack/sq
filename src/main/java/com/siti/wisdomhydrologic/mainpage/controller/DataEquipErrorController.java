package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.mainpage.vo.DataEquipErrorVo;
import com.siti.wisdomhydrologic.operation.entity.ReportManageApplicationBroken;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateOrTimeTrans;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2019/8/23.
 */
@RequestMapping("/dataEquip")
@RestController
@Api(value = "首页数据与设备异常controller", tags = {"首页数据异常"})
public class DataEquipErrorController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private ManageApplicationBrokenMapper reportManageApplicationBrokenMapper;

    @GetMapping("/get")
    @ApiOperation(value = "首页数据异常接口", httpMethod = "GET", notes = "数据异常接口," + "dataErrorNumber年数据异常数" + "equipErrorNumber年设备异常数" + "dataAnalystNumber年数据分析发现异常数" + "equipAnalystNumber年设备分析发现异常数" + "modelNumber模型发现异常数量" + "typicalValueNumber典型值发现异常数" + "dataErrorNumberMonth月数据异常数" + "equipErrorNumberMonth月设备异常数")
    public DataEquipErrorVo getDataEquipErrorVo(Integer date) { // date = 1时 查询日数据 date = 2时 查询月数据 date = 3 查询年数据

        if (date == null){
            return null;
        }
        DataEquipErrorVo vo = new DataEquipErrorVo(
                0,
                0,
                0,
                0,
                0,
                0
        );
        //默认查询本月

        List<ReportManageApplicationBroken> list = new ArrayList<>();
        if(date==1){
            String createDate = DateOrTimeTrans.Date2TimeString(new Date());
            list = reportManageApplicationBrokenMapper.getAllDay(createDate,null,1000,null,1);
        }
        if(date==2){
            String createDate = DateOrTimeTrans.Date2TimeString3(new Date());
            list = reportManageApplicationBrokenMapper.getAllMonth(createDate,null,1000,null,1);
        }
        if(date==3){
            String createDate = DateOrTimeTrans.Year2String(new Date());
            list = reportManageApplicationBrokenMapper.getAllYear(createDate,null,1000,null,1);
        }
        list.forEach(data -> {
            if (data.getBrokenAccordingId() != null) {
                String[] splitStr = data.getBrokenAccordingId().split("_");
                String type = splitStr[0];
                if ("eq".equals(type)) {//设备 数据分析
                    vo.setEquipAnalystNumber(vo.getEquipAnalystNumber() + 1);
                }
                if ("data".equals(type)) {//数据 数据分析
                    vo.setDataAnalystNumber(vo.getDataAnalystNumber() + 1);
                }
                if ("md".equals(type)) {//模型
                    vo.setModelNumber(vo.getModelNumber() + 1);
                }
                if ("ty".equals(type)) {//典型值
                    vo.setTypicalValueNumber(vo.getTypicalValueNumber() + 1);
                }
            }
        });
        vo.setEquipErrorNumber(vo.getEquipAnalystNumber() + vo.getTypicalValueNumber());
        vo.setDataErrorNumber(vo.getDataAnalystNumber() + vo.getModelNumber());

        return vo;
    }


}
