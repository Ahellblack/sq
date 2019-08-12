package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

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

    @Override
    public void beforeProcess(List<DayVo> val, Map<String, Map<Integer, RainfallEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {
    }

    @Override
    public void doProcess(Map<Integer, DayVo> val, Map<String, Map<Integer, RainfallEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    @Override
    public void beforeProcess(List<DayVo> realList, Map<String, Map<Integer, RainfallEntity>> configMap) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        Map<Integer, DayVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.RS)
                ).collect(Collectors.toMap(DayVo::getSenId, a -> a,(v1,v2)->{
                    return v2;
                }));
        doProcess( map, configMap);
    }

    @Override
    public void doProcess(Map<Integer, DayVo> mapval, Map<String, Map<Integer, RainfallEntity>> configMap) {
        Map<Integer, RainfallEntity> rainonfig = configMap.get(ConstantConfig.FLAGR);
        final List[] container = {new ArrayList<AbnormalDetailEntity>()};
        mapval.entrySet().stream().forEach(e -> {
            RainfallEntity config = rainonfig.get(e);
            if(config!=null){
            DayVo vo = mapval.get(e);
            //中断次数
            int limit = config.getInterruptLimit();
            //一个小时最大最小值
            double daymax = config.getMaxDayLevel();
            double daymin = config.getMinDayLevel();
            AbnormalDetailEntity entity = new AbnormalDetailEntity();
            if(daymax>vo.getMaxV()){
                if(entity==null){
                    entity = new AbnormalDetailEntity() {{
                        setDate(DateTransform.format(e.getValue().getTime()));
                        setSensorCode(vo.getSenId());
                        setDayAbove(1);
                        setDayBelow(0);
                    }};
                }else{
                    entity.setDayAbove(1);
                    entity.setDayBelow(0);
                }
            }
            if(daymin<vo.getMinV()){
                if(entity==null){
                    entity = new AbnormalDetailEntity() {{
                        setDate(DateTransform.format(e.getValue().getTime()));
                        setSensorCode(vo.getSenId());
                        setDayAbove(0);
                        setDayBelow(1);
                    }};
                }else{
                    entity.setDayBelow(1);
                    entity.setDayAbove(0);
                }
            }
            if (entity != null) {
                container[0].add(entity);
            }
        }});
        if(container[0].size()>0){
            abnormalDetailMapper.insertTSDVBWater(container[0]);
            container[0]=null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
