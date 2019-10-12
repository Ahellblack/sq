package com.siti.wisdomhydrologic.configwarning.controller;

import com.siti.wisdomhydrologic.configwarning.entity.AbnormalRainfall;
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
    private List<AbnormalRainfall> getAll(Integer sensorCode) {
        return warningAbnormalConfigMapper.getRainAll();
    }

    @PostMapping("/addConfig")
    private Map<String, Object> insert(@RequestBody AbnormalRainfall abnormalRainfall) {
        Map<String, Object> map = new HashMap<>();
        try {
            int status = warningAbnormalConfigMapper.addAbnormalRainfall(abnormalRainfall);
            if (status == 1) {
                map.put("status", status);
                map.put("msg", "添加成功");
            } else {
                map.put("status", status);
                map.put("msg", "添加失败");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -1);
            map.put("msg", "出现异常");
        }
        return map;
    }

    @PostMapping("/updateConfig")
    private Map<String, Object> update(@RequestBody AbnormalRainfall abnormalRainfall) {
        Map<String, Object> map = new HashMap<>();
        try {
            int status = warningAbnormalConfigMapper.updateAbnormalRainfall(abnormalRainfall);
            if (status == 1) {
                map.put("status", status);
                map.put("msg", "修改成功");
            } else {
                map.put("status", status);
                map.put("msg", "修改失败");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -1);
            map.put("msg", "出现异常");
        }
        return map;
    }
    @PostMapping("/deleteConfig")
    private Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        try {
            int status = warningAbnormalConfigMapper.deleteAbnormalRainfall(id);
            if (status == 1) {
                map.put("status", status);
                map.put("msg", "删除成功");
            } else {
                map.put("status", status);
                map.put("msg", "删除失败");
            }
            return map;
        } catch (Exception e) {
            map.put("status", -1);
            map.put("msg", "出现异常");
        }
        return map;
    }


}
