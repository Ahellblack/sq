package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.statistics.entity.BrokenType;
import com.siti.wisdomhydrologic.statistics.entity.DataError;
import com.siti.wisdomhydrologic.statistics.mapper.DataErrorNumberMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.RedisBiz;
import com.siti.wisdomhydrologic.util.MonthListUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/9/26.
 */
@RequestMapping("/dataError")
@RestController
public class DataErrorNumberController {

    @Resource
    private DataErrorNumberMapper dataErrorNumberMapper;
    @Resource
    private RedisBiz redisBiz;
    @Resource
    private UserMapper userMapper;

    @GetMapping("errorList")
    public Map<String, Object> getList(HttpSession session, Integer stationId, Integer dateType, Integer year, Integer quarter, String month, String dataTime) {

        Map<String, Object> map = new HashMap<>();
        User user = (User) redisBiz.get(session.getId());
        List<Org> orgList = userMapper.findOrg(user.getId());

        try {
            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);
            List<DataError> dataList =new ArrayList<>();
            if(list.size() >0) {
                dataList = dataErrorNumberMapper.getList(stationId, year, list, dataTime,orgList.get(0).getId());
            }else{
                dataList = dataErrorNumberMapper.getList(stationId, year, null, dataTime,orgList.get(0).getId());
            }
            Integer sum = 0;
            for (int i = 0; i < dataList.size(); i++) {
                sum += dataList.get(i).getNumber();
            }
            if (dataList.size() > 0) {
                map.put("status", 0);
                map.put("message", "查询成功");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("dataErrorInfo", dataList);
            } else {
                map.put("status", -1);
                map.put("message", "暂无数据");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("dataErrorInfo", null);
            }
        } catch (Exception e) {
            map.put("status", 1);
            map.put("message", "查询失败");
        }
        return map;


    }

}
