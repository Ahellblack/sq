package com.siti.wisdomhydrologic.task;

import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.operation.service.Impl.ManageMantainServiceImpl;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.PushMsg;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Solarie on 2020/2/26.
 */
@Component
public class DataBreakCheckTask {

    @Resource
    private RealStationDataMapper realStationDataMapper;

    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;

    /**
     * 每五分钟执行校验数据源是否中断
     */
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void checkBreak() throws Exception {

        Integer minTimeStamp = realStationDataMapper.getTimestampDiffWithCurrent();
        //最新数据与当前时间差值超过60分钟，为南北片负责人发送短信
        if (minTimeStamp >= 60) {
            List<String> phoneNumber = reportStationBrokenMapper.getNumberByRegionId(null);
            String numberStr = "";
            for (int i = 0; i < phoneNumber.size(); i++) {
                numberStr += "," + phoneNumber.get(i);
            }
            if (numberStr.length() > 1) {
                numberStr = numberStr.substring(1, numberStr.length());
            }
            if (numberStr == "") {
                System.out.println("配置电话信息错误");
            }
            PushMsg.pushBreakMsg(numberStr);
        }
    }

}
