package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.LocalDateUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
@Component
public class RealWaterlevelValve implements Valve<RealVo, WaterLevelEntity, AbnormalDetailEntity>, ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    private static ApplicationContext context = null;

    AbnormalDetailMapper abnormalDetailMapper = null;

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }



    @Override
    public void beforeProcess(List<RealVo> realList) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        //获取雨量配置表
        Map<Integer, WaterLevelEntity> rainfallMap = Optional.of(abnormalDetailMapper.fetchAllW())
                .get()
                .stream()
                .collect(Collectors.toMap(WaterLevelEntity::getSensorCode, a -> a));
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.WS)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map, rainfallMap);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval, Map<Integer, WaterLevelEntity> configMap) {

        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            WaterLevelEntity rainfallEntity = configMap.get(e);
            if (rainfallEntity != null) {
                RealVo vo=mapval.get(e);
                double max = rainfallEntity.getLevelMax();
                double min = rainfallEntity.getLevelMin();
                double realvalue= mapval.get(e).getFACTV();
                AbnormalDetailEntity exception = null;
                if (realvalue < min) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId()).fiveBelow(1)
                            .fiveAbove(0).hourBelow(0).hourAbove(0).dayBelow(0)
                            .dayAbove(0).moreNear(0).lessNear(0).floatingUp(0)
                            .floatingDown(0).keepTime(0).continueInterrupt(0)
                            .errorValue(0).errorPeriod("").equipmentError("")
                            .build());
                } else if (realvalue > max) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId()).fiveBelow(0)
                            .fiveAbove(1).hourBelow(0).hourAbove(0).dayBelow(0)
                            .dayAbove(0).moreNear(0).lessNear(0).floatingUp(0)
                            .floatingDown(0).keepTime(0).continueInterrupt(0)
                            .errorValue(0).errorPeriod("").equipmentError("")
                            .build());
                }
            }
        });
        if (exceptionContainer[0].size() > 0) {
            abnormalDetailMapper.insertFinal(exceptionContainer[0]);
            exceptionContainer[0] = null;
        }
    }

}
