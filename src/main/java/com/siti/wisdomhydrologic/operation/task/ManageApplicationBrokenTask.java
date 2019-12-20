package com.siti.wisdomhydrologic.operation.task;

import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.StationBrokenServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zyw on 2019/8/12.
 */
@Component
public class ManageApplicationBrokenTask {

    @Resource
    private StationBrokenServiceImpl manageApplicationBrokenService;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public int insertAbnormal() throws Exception {

        int i = manageApplicationBrokenService.insertDataMantain();
        //系统开关门状态录入
        /*manageApplicationBrokenService.updateUnpackStatus();*/
        //异常状态恢复方法
        manageApplicationBrokenService.updateBrokenStatus();
        return i;
    }

}
