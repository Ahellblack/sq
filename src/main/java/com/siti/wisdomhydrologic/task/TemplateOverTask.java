package com.siti.wisdomhydrologic.task;

import com.siti.wisdomhydrologic.mainpage.mapper.DeviceTemMapper;
import com.siti.wisdomhydrologic.mainpage.mapper.RealStationDataMapper;
import com.siti.wisdomhydrologic.mainpage.vo.DeviceVo;
import com.siti.wisdomhydrologic.operation.mapper.ReportStationBrokenMapper;
import com.siti.wisdomhydrologic.util.PushMsg;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Solarie on 2020/3/5.
 */
@Component
public class TemplateOverTask {

    @Resource
    DeviceTemMapper deviceTemMapper;

    @Resource
    private ReportStationBrokenMapper reportStationBrokenMapper;
    /**
     * 每五分钟执行校验数据源是否中断
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void checkBreak() throws Exception {

        try {
            DeviceVo deviceVo = deviceTemMapper.getTemperature();
            //最新实时机房温度超过26度，为南北片负责人发送短信
            if (Double.parseDouble(deviceVo.getTemperature())>26) {
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
                PushMsg.pushBreakMsg(numberStr,"机房目前温度过高，为"+deviceVo.getTemperature()+"度");
            }
        } catch (Exception e) {
            System.out.println("获取机房温湿度失败");
        }

    }
}
