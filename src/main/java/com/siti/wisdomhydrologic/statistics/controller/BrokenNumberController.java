package com.siti.wisdomhydrologic.statistics.controller;

import com.siti.wisdomhydrologic.statistics.entity.BrokenType;
import com.siti.wisdomhydrologic.statistics.mapper.BrokenNumberMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by dell on 2019/9/23.
 */
@RequestMapping("/brokenNumber")
@RestController
public class BrokenNumberController {

    @Resource
    private BrokenNumberMapper brokenNumberMapper;

    @GetMapping("getAll")
    public List<BrokenType> getList(String stationName, String yearMonth){
        return brokenNumberMapper.getList(stationName,yearMonth);
    }


}
