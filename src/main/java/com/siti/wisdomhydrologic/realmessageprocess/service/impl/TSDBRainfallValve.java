package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.TSDBVo;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
@Component
public class TSDBRainfallValve implements Valve<TSDBVo,RainfallEntity,AbnormalDetailEntity>,ApplicationContextAware {

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
    public void beforeProcess(List<TSDBVo> val, Map<String, Map<Integer, RainfallEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {
    }

    @Override
    public void doProcess(Map<Integer, TSDBVo> val, Map<String, Map<Integer, RainfallEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {
    }

    @Override
    public void beforeProcess(List<TSDBVo> realList, Map<String, Map<Integer, RainfallEntity>> configMap) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        Map<Integer, TSDBVo> map = realList.stream()
                .filter(
                        e -> ((e.getSENID() + "").substring(5)).equals(ConstantConfig.RS)
                ).collect(Collectors.toMap(TSDBVo::getSENID, a -> a,(value1,value2)->{
                    return value2;
                }));
        doProcess( map, configMap);
    }

    @Override
    public void doProcess(Map<Integer, TSDBVo> mapval, Map<String, Map<Integer, RainfallEntity>> configMap) {
        Map<Integer, RainfallEntity> rainonfig = configMap.get(ConstantConfig.FLAGR);
        final List[] container = {new ArrayList<AbnormalDetailEntity>()};
        mapval.entrySet().stream().forEach((Map.Entry<Integer, TSDBVo> e) -> {
            RainfallEntity config = rainonfig.get(e);
            if (config != null) {
                TSDBVo vo = mapval.get(e);
                //中断次数
                int limit = config.getInterruptLimit();
                //一个小时最大最小值
                double hourmax = config.getMaxHourLevel();
                double hourmin = config.getMinHourLevel();
                double[] arrayV = {vo.getV0(), vo.getV1(), vo.getV2(), vo.getV3(), vo.getV4(), vo.getV5(),
                        vo.getV6(), vo.getV7(), vo.getV8(), vo.getV9(), vo.getV10(), vo.getV11()};
                List<Double> tsLists = new ArrayList(Arrays.asList(arrayV));
                OptionalDouble min = tsLists.stream().mapToDouble(Double::doubleValue).min();
                OptionalDouble max = tsLists.stream().mapToDouble(Double::doubleValue).max();
                final AbnormalDetailEntity[] entity = new AbnormalDetailEntity[1];
                if (min.getAsDouble() < hourmin) {
                    entity[0] = new AbnormalDetailEntity() {{
                        setHourAbove(0);
                        setHourBelow(1);
                    }};
                } else if (max.getAsDouble() > hourmax) {
                    if (entity[0] == null) {
                        entity[0] = new AbnormalDetailEntity() {{
                            setHourAbove(1);
                            setHourBelow(0);
                        }};
                    } else {
                        entity[0].setHourBelow(0);
                        entity[0].setHourAbove(1);
                    }
                }
                IntStream.range(0, 13 - limit).forEach(j -> {
                    final int[] flag = {0};
                    IntStream.range(j, j + limit).forEach(k -> {
                        if (arrayV[k] == -99) {
                            flag[0]++;
                        }
                    });
                    if (flag[0] == limit) {
                        if (entity[0] == null) {
                            entity[0] = new AbnormalDetailEntity() {{
                                setContinueInterrupt(1);
                            }};
                        } else {
                            entity[0].setContinueInterrupt(1);
                        }
                    }
                });
                if (entity[0] != null) {
                    entity[0].setDate(vo.getTime());
                    entity[0].setSensorCode(vo.getSENID());
                    container[0].add(entity[0]);
                }
            }
        });
        if(container[0].size()>0){
            abnormalDetailMapper.insertTSDBRain(container[0]);
            container[0]=null;
        }

    }


}
