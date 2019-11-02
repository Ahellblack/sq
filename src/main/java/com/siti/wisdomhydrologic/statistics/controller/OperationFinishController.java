package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.statistics.mapper.OperationFinishMapper;
import com.siti.wisdomhydrologic.statistics.util.MonthListUtil;
import com.siti.wisdomhydrologic.statistics.vo.OperationFinish;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/11/1.
 */
@RestController
@RequestMapping("operationFinish")
public class OperationFinishController {

    @Resource
    UserInfoService userInfoService;
    @Resource
    OperationFinishMapper operationFinishMapper;

    @GetMapping("getResult")
    public Map<String, Object> getAll(Integer dateType, Integer year, Integer quarter, String month) {

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
            User user = (User)userInfoService.get();

            List<OperationFinish> returnList = operationFinishMapper.getRegionGroup(user.getOrgList().get(0).getId());
            returnList.forEach(data->{data.setMal4(0);data.setResult4(0);data.setResult5(0);data.setResult6(0);});

            returnList.forEach(data -> {
                List<OperationFinish> result4List = operationFinishMapper.getResult4(year, list);
                if (result4List.size() > 0) {
                    result4List.forEach(result4 -> {
                        if (data.getRegionId().equals(result4.getRegionId())) {
                            int number = result4.getResult4();
                            data.setResult4(number);
                        }
                    });
                } else {
                    data.setResult4(0);
                }
                List<OperationFinish> result5List = operationFinishMapper.getResult5(year, list);
                if (result5List.size() > 0) {
                    result5List.forEach(result5 -> {
                        if (data.getRegionId().equals(result5.getRegionId())) {
                            int number = result5.getResult5();
                            data.setResult5(number);
                        }});
                } else {
                    data.setResult5(0);
                }
                List<OperationFinish> result6List = operationFinishMapper.getResult6(year, list);
                if (result6List.size() > 0) {
                    result6List.forEach(result6 -> {
                        if (data.getRegionId().equals(result6.getRegionId())) {
                            int number = result6.getResult6();
                            data.setResult6(number);
                        }
                    });
                } else {
                    data.setResult6(0);
                }
                List<OperationFinish> mal4List = operationFinishMapper.getMal(year, list);
                if (mal4List.size() > 0) {
                    mal4List.forEach(mal4 -> {
                        if (data.getRegionId().equals(mal4.getRegionId())) {
                            int number = mal4.getMal4();
                            data.setMal4(number);
                        }
                    });
                } else {
                    data.setMal4(0);
                }
            });
            map.put("information", returnList);
        } catch (Exception e) {
            map.put("status", -1);
            map.put("msg", "查询异常");
        }

        return map;
    }


}
