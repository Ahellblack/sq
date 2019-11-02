package com.siti.wisdomhydrologic.upload.controller;

import com.siti.wisdomhydrologic.upload.entity.SysManualLog;
import com.siti.wisdomhydrologic.upload.mapper.ArticleMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/10/31.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    UserInfoService userInfoService;

    @GetMapping("findMine")
    public List<SysManualLog> getSysManualLog() {

        User user = (User) userInfoService.get();
        articleMapper.getAll(user.getId()).forEach(data->{data.setCreateBy(user.getRealName());});
        return articleMapper.getAll(user.getId());
    }

    @PostMapping("createMsg")
    public Map<String, Object> create(@RequestBody SysManualLog message) {

        Map<String, Object> map = new HashMap<>();
        try {
            message.setCreateTime(new Date());
            User user = (User) userInfoService.get();
            message.setCreateBy(user.getId()+"");

            int i = articleMapper.createNew(message);
            if (i != 0) {
                map.put("status", 1);
                map.put("msg", "保存成功");

            } else {
                map.put("status", -1);
                map.put("msg", "保存失败");
            }
            return map;
        } catch (Exception e) {

            map.put("status", -1);
            map.put("msg", "保存失败");
        }
        return map;
    }

    @PostMapping("updateMsg")
    public Map<String, Object> update(@RequestBody SysManualLog message) {

        Map<String, Object> map = new HashMap<>();
        try {
            int i = articleMapper.update(message);
            if (i != 0) {
                map.put("status", 1);
                map.put("msg", "修改成功");

            } else {
                map.put("status", -1);
                map.put("msg", "修改失败");
            }
            return map;
        } catch (Exception e) {

            map.put("status", -1);
            map.put("msg", "修改失败");
        }
        return map;
    }


    @GetMapping("deleteMsg")
    public Map<String, Object> update(Integer id) {

        Map<String, Object> map = new HashMap<>();
        try {
            int i = articleMapper.delete(id);
            if (i != 0) {
                map.put("status", 1);
                map.put("msg", "删除成功");

            } else {
                map.put("status", -1);
                map.put("msg", "删除失败");
            }
            return map;
        } catch (Exception e) {

            map.put("status", -1);
            map.put("msg", "删除失败");
        }
        return map;
    }



}
