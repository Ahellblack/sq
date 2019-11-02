package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.operation.mapper.RecordDeviceReplaceMapper;
import com.siti.wisdomhydrologic.operation.vo.RecordDeviceReplaceVo;
import com.siti.wisdomhydrologic.statistics.entity.DeviceChange;
import com.siti.wisdomhydrologic.statistics.mapper.DeviceMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.siti.wisdomhydrologic.statistics.vo.DeviceStatistics;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.statistics.util.MonthListUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/9/23.
 */
@RequestMapping("/device")
@RestController
public class DeviceController {

    @Resource
    private RecordDeviceReplaceMapper recordDeviceReplaceMapper;

    @Resource
    private DeviceMapper deviceMapper;

    @RequestMapping("/devReplace")
    public Map<String, Object> getList(Integer stationId, Integer dateType, Integer year, Integer quarter, String month) {
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
            List<DeviceChange> dataList = deviceMapper.getList(stationId, year, list);
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
                map.put("devReplaceInfo", dataList);
            } else {
                map.put("status", 0);
                map.put("message", "暂无数据");
                map.put("stationId", stationId);
                map.put("dateType", dateType);
                map.put("year", year);
                map.put("quarter", quarter);
                map.put("month", month);
                map.put("count", sum);
                map.put("devReplaceInfo", null);
            }
        } catch (Exception e) {
            map.put("status", -1);
            map.put("message", "查询失败");
        }
        return map;
    }
    @GetMapping("spareBuy")
    public Map<String, Object> getDevice(Integer stationId, Integer dateType, Integer year, Integer quarter, String month) {
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

            List<RecordDeviceReplaceVo> deviceReplace = recordDeviceReplaceMapper.getDeviceReplace(stationId, year, list);
            if (deviceReplace.size() > 0) {
                Integer sum = deviceReplace.size();
                map.put("count", sum);

                List<DeviceStatistics> Newstatistics = recordDeviceReplaceMapper.getNewStatistics(stationId, year, list);
                map.put("changeMost", Newstatistics.get(0).getOriginDeviceName());
                if(Newstatistics.size()>0){
                    map.put("shouldBuy1", Newstatistics.get(0).getNewDeviceName());
                    map.put("BuyNum1", Newstatistics.get(0).getDisplaytime());
                }
                if(Newstatistics.size()>1){
                    map.put("shouldBuy2", Newstatistics.get(1).getNewDeviceName());
                    map.put("BuyNum2", Newstatistics.get(1).getDisplaytime());
                }
                if(Newstatistics.size()>2){
                    map.put("shouldBuy3", Newstatistics.get(2).getNewDeviceName());
                    map.put("BuyNum3", Newstatistics.get(2).getDisplaytime());
                }
                map.put("status", 1);
                map.put("msg", "查询成功");
            } else {
                map.put("status", 0);
                map.put("count", 0);
                map.put("shouldBuy1","无");
                map.put("BuyNum1", 0);
                map.put("msg", "暂无数据");
            }
        } catch (Exception e) {
            map.put("status", -1);
            map.put("count", 0);
            map.put("shouldBuy1","无");
            map.put("BuyNum1", 0);
            map.put("msg", "查询异常");
        }
        return map;
    }
}
