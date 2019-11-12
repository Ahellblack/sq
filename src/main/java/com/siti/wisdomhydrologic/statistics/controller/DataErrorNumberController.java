package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportManageDataMantain;
import com.siti.wisdomhydrologic.statistics.entity.DataError;
import com.siti.wisdomhydrologic.statistics.mapper.DataErrorNumberMapper;
import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.statistics.util.MonthListUtil;

import java.util.ArrayList;
import java.util.List;

import com.siti.wisdomhydrologic.util.RateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyw on 2019/9/26.
 */
@RequestMapping("/dataError")
@RestController
public class DataErrorNumberController {

    @Resource
    private DataErrorNumberMapper dataErrorNumberMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserMapper userMapper;

    @GetMapping("errorList")
    public Map<String, Object> getList(Integer stationId, Integer dateType, Integer year, Integer quarter, String month, String dataTime) {

        Map<String, Object> map = new HashMap<>();
        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());

        try {
            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);
            List<DataError> dataList =new ArrayList<>();
            if(list.size() >0) {
                dataList = dataErrorNumberMapper.getList(stationId, year, list, null,orgList.get(0).getId());
            }else{
                // list为空,且dateTime不为空时
                if(dateType == 4 && dataTime != null) {
                    dataList = dataErrorNumberMapper.getList(stationId, year, null, dataTime, orgList.get(0).getId());
                }else{
                    // monthlist无值且dateTime为空时
                    map.put("status", -2);
                    map.put("message", "参数错误");
                    return map;
                }

            }
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
                map.put("dataErrorInfo", dataList);
            } else {
                map.put("status", 0);
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
            map.put("status", -1);
            map.put("message", "查询失败");
        }
        return map;
    }



    @GetMapping("getEasierTimeSpace")
    public Map<String, Object> getTimeList(Integer stationId, Integer dateType, Integer year, Integer quarter, String month, String dataTime) {


        Map<String, Object> map = new HashMap<>();

        if (dateType == null || "".equals(dateType) || year == null || "".equals(year)) {
            map.put("status", -2);
            map.put("message", "参数错误");
            map.put("space1to4",0);map.put("space5to8",0);map.put("space9to12",0);map.put("space13to16",0);map.put("space17to20",0);map.put("space21to24",0);
            map.put("proport1to4", "0%");map.put("proport5to8", "0%");map.put("proport9to12", "0%");map.put("proport13to16", "0%");map.put("proport17to20","0%");map.put("proport21to24", "0%");

            return map;
        }

        try {
            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);

            User user = (User) userInfoService.get();
            List<Org> orgList = userMapper.findOrg(user.getId());
            List<ReportManageDataMantain> dataList =new ArrayList<>();
            if(list.size() >0) {
                dataList = dataErrorNumberMapper.getGroupByTime(stationId, year, list, null,orgList.get(0).getId());
            }else{
                // list为空,且dateTime不为空时
                if(dateType == 4 && dataTime != null) {
                    dataList = dataErrorNumberMapper.getGroupByTime(stationId, year, null, dataTime, orgList.get(0).getId());
                }else{
                    // monthlist无值且dateTime为空时
                    map.put("status", -2);
                    map.put("message", "参数错误");
                    map.put("space1to4",0);map.put("space5to8",0);map.put("space9to12",0);map.put("space13to16",0);map.put("space17to20",0);map.put("space21to24",0);
                    map.put("proport1to4", "0%");map.put("proport5to8", "0%");map.put("proport9to12", "0%");map.put("proport13to16", "0%");map.put("proport17to20","0%");map.put("proport21to24", "0%");

                    return map;
                }
            }
            Integer sum = dataList.size();
            Integer space1to4 = 0;
            Integer space5to8 = 0;
            Integer space9to12 = 0;
            Integer space13to16 = 0;
            Integer space17to20 = 0;
            Integer space21to24 = 0;
            for (int i = 0; i < sum; i++) {
                Integer time = Integer.parseInt(dataList.get(i).getCreateTime().substring(11, 13));
                if (time >= 0 && time <= 4) {
                    space1to4++;
                }
                if (time >= 5 && time <= 8) {
                    space5to8++;
                }
                if (time >= 9 && time <= 12) {
                    space9to12++;
                }
                if (time >= 13 && time <= 16) {
                    space13to16++;
                }
                if (time >= 17 && time <= 20) {
                    space17to20++;
                }
                if (time >= 21 && time <= 24) {
                    space21to24++;
                }
            }
            if (dataList.size() > 0) {
                map.put("status", 1);
                map.put("message", "查询成功");
                map.put("space1to4",space1to4);map.put("space5to8",space5to8);map.put("space9to12",space9to12);map.put("space13to16",space13to16);map.put("space17to20",space17to20);map.put("space21to24",space21to24);
                map.put("proport1to4", RateUtils.accuracy(space1to4,sum,2));map.put("proport5to8", RateUtils.accuracy(space5to8,sum,2));map.put("proport9to12", RateUtils.accuracy(space9to12,sum,2));map.put("proport13to16", RateUtils.accuracy(space13to16,sum,2));map.put("proport17to20", RateUtils.accuracy(space17to20,sum,2));map.put("proport21to24", RateUtils.accuracy(space21to24,sum,2));
                //故障次数
                map.put("count", sum);
            } else {
                map.put("status", 0);
                map.put("message", "暂无数据");
                map.put("space1to4",0);map.put("space5to8",0);map.put("space9to12",0);map.put("space13to16",0);map.put("space17to20",0);map.put("space21to24",0);
                map.put("proport1to4", "0%");map.put("proport5to8", "0%");map.put("proport9to12", "0%");map.put("proport13to16", "0%");map.put("proport17to20","0%");map.put("proport21to24", "0%");
                //故障次数
                map.put("count", 0);

                map.put("stationBrokenInfo", null);
            }
        } catch (Exception e) {
            map.put("status", -1);
            map.put("space1to4",0);map.put("space5to8",0);map.put("space9to12",0);map.put("space13to16",0);map.put("space17to20",0);map.put("space21to24",0);
            map.put("proport1to4", "0%");map.put("proport5to8", "0%");map.put("proport9to12", "0%");map.put("proport13to16", "0%");map.put("proport17to20","0%");map.put("proport21to24", "0%");
            //故障次数
            map.put("count", 0);
            map.put("message", "查询错误");
        }
        return map;
    }

}
