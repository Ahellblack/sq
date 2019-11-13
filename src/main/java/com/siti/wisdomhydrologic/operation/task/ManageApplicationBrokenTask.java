package com.siti.wisdomhydrologic.operation.task;

import com.siti.wisdomhydrologic.configmaintain.mapper.ConfigAbnormalDictionaryMapper;
import com.siti.wisdomhydrologic.operation.mapper.ManageApplicationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageApplicationBrokenServiceImpl;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zyw on 2019/8/12.
 */
@Component
public class ManageApplicationBrokenTask {

    @Resource
    private ManageApplicationBrokenServiceImpl manageApplicationBrokenService;
    @Resource
    private ConfigAbnormalDictionaryMapper configAbnormalDictionaryMapper;
    @Resource
    private AbnormalDetailMapper abnormalDetailMapper;
    @Resource
    private ManageApplicationBrokenMapper reportManageApplicationBrokenMapper;

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
