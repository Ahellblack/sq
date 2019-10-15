package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.statistics.entity.BrokenType;
import com.siti.wisdomhydrologic.statistics.mapper.BrokenNumberMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.MonthListUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by dell on 2019/9/23.
 */
@RequestMapping("/brokenNumber")
@RestController
public class BrokenNumberController {

    @Resource
    private BrokenNumberMapper brokenNumberMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;

    @GetMapping("stationBroken")
    public Map<String, Object>  getList(HttpSession session,Integer stationId, Integer dateType, Integer year, Integer quarter, String month){

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        Map<String, Object> map = new HashMap<>();

        if(dateType ==null || "".equals(dateType) || year==null || "".equals(year)){
            map.put("status", -2);
            map.put("message", "参数错误");
            return map;
        }
        try {
            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);
            if(list.size() ==0 ){
                map.put("status", -2);
                map.put("message", "参数错误");
                return map;
            }
            List<BrokenType> dataList = brokenNumberMapper.getList(stationId, year, list,orgList.get(0).getId());
            Integer sum = 0;
            for (int i = 0; i < dataList.size(); i++) {
                sum += dataList.get(i).getNumber();
            }
            if (dataList.size() > 0) {
                map.put("status", 1);
                map.put("message", "查询成功");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("stationBrokenInfo", dataList);
            } else {
                map.put("status", 0);
                map.put("message", "暂无数据");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("stationBrokenInfo", null);
            }
        }catch (Exception e){
            map.put("status", -1);
            map.put("message", "查询错误");
        }
        return map;


    }


}
