package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.google.common.collect.Maps;
import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.TideLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.LocalDateUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
@Component
public class DayRainfallValve implements ApplicationContextAware,Valve<DayVo,RainfallEntity,AbnormalDetailEntity> {

    private static ApplicationContext context = null;

    AbnormalDetailMapper abnormalDetailMapper=null;


    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

/*
    //获取水位配置表
    Map<Integer, Object> waterLevelMap = Optional.of(waterLevelMapper.fetchAll())
            .get()
            .stream()
            .collect(Collectors.toMap(WaterLevelEntity::getSensorCode, a -> a));
    //获取潮位配置表
    Map<Integer, Object> tideLevelMap = Optional.of(tideLevelMapper.fetchAll())
            .get()
            .stream()
            .collect(Collectors.toMap(TideLevelEntity::getSensorCode, b -> b));
    //获取雨量配置表
    Map<Integer, Object> rainfallMap = Optional.of(rainFallMapper.fetchAll())
            .get()
            .stream()
            .collect(Collectors.toMap(RainfallEntity::getSensorCode, a -> a));

    Map<String, Map<Integer, Object>> configMap = Maps.newHashMap();
        configMap.put(ConstantConfig.FLAGW, waterLevelMap);
        configMap.put(ConstantConfig.FLAGT, tideLevelMap);
        configMap.put(ConstantConfig.FLAGR, rainfallMap);*/
    @Override
    public void beforeProcess(List<DayVo> realList) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        //获取雨量配置表
        Map<Integer, RainfallEntity> rainfallMap = Optional.of(abnormalDetailMapper.fetchAllR())
                .get()
                .stream()
                .collect(Collectors.toMap(RainfallEntity::getSensorCode, a -> a));
        Map<Integer, DayVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.RS)
                ).collect(Collectors.toMap(DayVo::getSenId, a -> a,(v1,v2)->{
                    return v2;
                }));
        doProcess( map, rainfallMap);
    }


    @Override
    public void doProcess(Map<Integer, DayVo> mapval, Map<Integer, RainfallEntity> configMap) {
        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        final String[] time = new String[1];
        mapval.keySet().stream().forEach(e -> {
            RainfallEntity config = configMap.get(e);
            if(config!=null){
            DayVo vo = mapval.get(e);
            //中断次数
            int limit = config.getInterruptLimit();
            //一个小时最大最小值
            double daymax = config.getMaxDayLevel();
            double daymin = config.getMinDayLevel();
            AbnormalDetailEntity entity =null;
            if(daymax>vo.getMaxV()){
                exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                        .date(LocalDateUtil
                                .dateToLocalDateTime(vo.getTime())
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .sensorCode(vo.getSenId()).fiveBelow(0)
                        .fiveAbove(0).hourBelow(0).hourAbove(0).dayBelow(0)
                        .dayAbove(1).moreNear(0).lessNear(0).floatingUp(0)
                        .floatingDown(0).keepTime(0).continueInterrupt(0)
                        .errorValue(0).errorPeriod("").equipmentError("")
                        .build());
            }
            if(daymin<vo.getMinV()){
                exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                        .date(LocalDateUtil
                                .dateToLocalDateTime(vo.getTime())
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .sensorCode(vo.getSenId()).fiveBelow(0)
                        .fiveAbove(0).hourBelow(0).hourAbove(0).dayBelow(1)
                        .dayAbove(0).moreNear(0).lessNear(0).floatingUp(0)
                        .floatingDown(0).keepTime(0).continueInterrupt(0)
                        .errorValue(0).errorPeriod("").equipmentError("")
                        .build());
            }

        }});
        if (exceptionContainer[0].size() > 0) {
            abnormalDetailMapper.insertFinal(exceptionContainer[0]);
            exceptionContainer[0] = null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
