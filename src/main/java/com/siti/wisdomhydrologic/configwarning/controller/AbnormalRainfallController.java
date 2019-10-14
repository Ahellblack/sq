package com.siti.wisdomhydrologic.configwarning.controller;

import com.siti.wisdomhydrologic.configwarning.entity.AbnormalRainfall;
import com.siti.wisdomhydrologic.configwarning.entity.UnrainAbnormal;
import com.siti.wisdomhydrologic.configwarning.mapper.WarningAbnormalConfigMapper;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2019/10/12.
 */
@RestController
@RequestMapping("/abnormalRain")
public class AbnormalRainfallController {


    @Resource
    private WarningAbnormalConfigMapper warningAbnormalConfigMapper;

    @GetMapping("/getConfig")
    private  Map<String, Object> getAll(String sensorCode) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<AbnormalRainfall> list = warningAbnormalConfigMapper.getRainAll(sensorCode);
            if (list.size() > 0) {
                    map.put("list", list);
                    map.put("status", 1);
                    map.put("msg", "查询成功");
                } else {
                    map.put("status", 0);
                    map.put("msg", "暂无数据");
                }
            return map;
        } catch (Exception e) {
            map.put("status", -2);
            map.put("msg", "异常");
        }
        return map;
    }

    @PostMapping("/addConfig")
    private Map<String, Object> insert(@RequestBody AbnormalRainfall abnormalRainfall) {
        Map<String, Object> map = new HashMap<>();
        try {
            AbnormalRainfall rainfall = warningAbnormalConfigMapper.getOneAbnormal(abnormalRainfall.getSensorCode());
            if(rainfall!=null){
                map.put("status", -1);
                map.put("msg", "sensorCode重复");
                return map;
            }
            int status = warningAbnormalConfigMapper.addAbnormalRainfall(abnormalRainfall);
            if (status != 0) {
                map.put("status", 1);
                map.put("msg", "添加成功");
            } else {
                map.put("status", 0);
                map.put("msg", "添加失败");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -2);
            map.put("msg", "出现异常");
        }
        return map;
    }

    @PostMapping("/updateConfig")
    private Map<String, Object> update(@RequestBody AbnormalRainfall abnormalRainfall) {
        Map<String, Object> map = new HashMap<>();
        try {
            int status = warningAbnormalConfigMapper.updateAbnormalRainfall(abnormalRainfall);
            if (status != 0) {
                map.put("status", 1);
                map.put("msg", "修改成功");
            } else {
                map.put("status", 0);
                map.put("msg", "修改失败");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -2);
            map.put("msg", "出现异常");
        }
        return map;
    }
    @PostMapping("/deleteConfig")
    private Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int status = warningAbnormalConfigMapper.deleteAbnormalRainfall(id);
            if (status != 0) {
                map.put("status", 1);
                map.put("msg", "删除成功");
            } else {
                map.put("status", 0);
                map.put("msg", "删除失败");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -2);
            map.put("msg", "出现异常");
        }
        return map;
    }


}
