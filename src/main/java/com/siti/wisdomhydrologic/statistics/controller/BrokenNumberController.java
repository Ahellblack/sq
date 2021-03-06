package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.operation.entity.ReportStationBroken;
import com.siti.wisdomhydrologic.statistics.entity.BrokenType;
import com.siti.wisdomhydrologic.statistics.mapper.BrokenNumberMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.CaffeineUtil;
import com.siti.wisdomhydrologic.util.DateDistance;
import com.siti.wisdomhydrologic.statistics.util.MonthListUtil;
import com.siti.wisdomhydrologic.util.RateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zyw on 2019/9/23.
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
    public Map<String, Object> getList(Integer stationId, Integer dateType, Integer year, Integer quarter, String month) {

        User user = (User) userInfoService.get();
        List<Org> orgList = userMapper.findOrg(user.getId());
        Map<String, Object> map = new HashMap<>();

        if (dateType == null || "".equals(dateType) || year == null || "".equals(year)) {
            map.put("status", -2);
            map.put("message", "参数错误");
            return map;
        }
        try {
            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);
            if (list.size() == 0) {
                map.put("status", -2);
                map.put("message", "参数错误");
                return map;
            }
            List<BrokenType> dataList = (List<BrokenType>)CaffeineUtil.build().getValues("stationBroken,"+stationId+","+dateType+","+year+","+quarter+","+month,(x)->{
                return brokenNumberMapper.getList(stationId, year, list, orgList.get(0).getId());
            });
            //List<BrokenType> dataList = brokenNumberMapper.getList(stationId, year, list, orgList.get(0).getId());


            Integer sum = 0;
            Integer maxTime = 0;
            String maxAccording = "";
            for (int i = 0; i < dataList.size(); i++) {
                Integer number = dataList.get(i).getNumber();
                sum += number;
                if (maxTime < number) {
                    maxTime = number;
                    maxAccording = dataList.get(i).getBrokenAccording();
                }
            }
            if (dataList.size() > 0) {
                map.put("status", 1);
                map.put("message", "查询成功");
                map.put("stationId", stationId);
                //故障次数
                map.put("count", sum);
                //故障总类型
                map.put("type", dataList.size());
                //最多故障出现次数
                map.put("maxTime", maxTime);
                map.put("proportion", RateUtils.accuracy(maxTime, sum, 2));
                //最多故障类型
                map.put("maxAccording", maxAccording);

                map.put("stationBrokenInfo", dataList);
            } else {
                map.put("status", 0);
                map.put("message", "暂无数据");
                map.put("stationBrokenInfo", null);
            }
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", "查询错误");
        }
        return map;
    }


    @GetMapping("getRecover")
    public Map<String, Object> getRecover(Integer stationId, Integer dateType, Integer year, Integer quarter, String month) {

        Map<String, Object> map = new HashMap<>();

        if (dateType == null || "".equals(dateType) || year == null || "".equals(year)) {
            map.put("status", -2);
            map.put("message", "参数错误");
            map.put("hour1", 0); map.put("hour1Rate","0%");
            map.put("hour2", 0); map.put("hour2Rate","0%");
            map.put("hour3", 0); map.put("hour3Rate","0%");
            map.put("over4hour", 0);map.put("over4hourRate","0%");
            map.put("count", 0);
            return map;
        }
        try {
            List<String> list = MonthListUtil.monthList(dateType, year, quarter, month);

            User user = (User) userInfoService.get();
            List<Org> orgList = userMapper.findOrg(user.getId());

            List<ReportStationBroken> dataList = (List<ReportStationBroken>)CaffeineUtil.build().getValues("getRecover,"+stationId+","+dateType+","+year+","+quarter+","+month,(x)->{
                return brokenNumberMapper.getRecoverTime(stationId, year, list, orgList.get(0).getId());
            });

            Integer sum = dataList.size();
            Integer hour1 = 0;
            Integer hour2 = 0;
            Integer hour3 = 0;
            Integer over4hour = 0;
            for (int i = 0; i < sum; i++) {
                ReportStationBroken data = dataList.get(i);
                long[] distanceTimes = DateDistance.getDistanceTimes(data.getCreateTime(), data.getBrokenResolveTime());
                long hour = distanceTimes[1];

                if (hour <= 1) {
                    hour1++;
                } else if (hour > 1 && hour <= 2) {
                    hour2++;
                } else if (hour > 2 && hour <= 3) {
                    hour3++;
                } else {
                    over4hour++;
                }
            }
            if (dataList.size() > 0) {
                map.put("status", 1);
                map.put("message", "查询成功");
                map.put("hour1", hour1);map.put("hour1Rate",RateUtils.accuracy(hour1,sum,2));
                map.put("hour2", hour2);map.put("hour2Rate",RateUtils.accuracy(hour2,sum,2));
                map.put("hour3", hour3);map.put("hour3Rate",RateUtils.accuracy(hour3,sum,2));
                map.put("over4hour", over4hour);map.put("over4hourRate",RateUtils.accuracy(over4hour,sum,2));
                //故障次数
                map.put("count", sum);
            } else {
                map.put("status", 0);
                map.put("message", "暂无数据");
                map.put("hour1", 0); map.put("hour1Rate","0%");
                map.put("hour2", 0); map.put("hour2Rate","0%");
                map.put("hour3", 0); map.put("hour3Rate","0%");
                map.put("over4hour", 0);map.put("over4hourRate","0%");
                map.put("count", 0);
            }
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", "查询错误");
            map.put("hour1", 0); map.put("hour1Rate","0%");
            map.put("hour2", 0); map.put("hour2Rate","0%");
            map.put("hour3", 0); map.put("hour3Rate","0%");
            map.put("over4hour", 0);map.put("over4hourRate","0%");
            map.put("count", 0);
        }
        return map;
    }


}
