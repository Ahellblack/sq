package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.TideLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.DateTransform;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
@Component
public  class RealTidelValve implements Valve<RealVo,TideLevelEntity,AbnormalDetailEntity>,ApplicationContextAware {

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
    public void beforeProcess(List<RealVo> val, Map<String, Map<Integer, TideLevelEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {

    }

    @Override
    public void doProcess(Map<Integer, RealVo> val, Map<String, Map<Integer, TideLevelEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {

    }

    @Override
    public void beforeProcess(List<RealVo> realList, Map<String, Map<Integer, TideLevelEntity>> configMap) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);

        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.TS)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map,configMap);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval, Map<String, Map<Integer, TideLevelEntity>> configMap) {
        Map<Integer, TideLevelEntity> waterFlag =  configMap
                .get(ConstantConfig.FLAGT);
        final double[] doubles={99999};
        final List[] container={new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            TideLevelEntity rainfallEntity = waterFlag.get(e);
            if(rainfallEntity!=null) {
                double realvalue= mapval.get(e).getFACTV();
                double max = rainfallEntity.getLevelMax();
                double min = rainfallEntity.getLevelMin();
                AbnormalDetailEntity exception = null;
                if (realvalue < min) {
                    exception = new AbnormalDetailEntity() {{
                        setFiveBelow(1);
                        setFiveAbove(0);
                    }};
                } else if (realvalue > max) {
                    if (exception == null) {
                        exception = new AbnormalDetailEntity() {{
                            setFiveBelow(0);
                            setFiveAbove(1);
                        }};
                    } else {
                        exception.setFiveBelow(0);
                        exception.setFiveAbove(1);
                    }
                }
                //最大上升 最大下降
                if (doubles[0] == 9999) {
                    doubles[0] = realvalue;
                } else {
                    if (realvalue> doubles[0]) {
                        if ((realvalue - doubles[0]) > rainfallEntity.getUpMax()) {
                            if (exception == null) {
                                exception = new AbnormalDetailEntity() {{
                                    setFloatingUp(1);
                                    setFloatingDown(0);
                                }};
                            } else {
                                exception.setFloatingDown(0);
                                exception.setFloatingUp(1);
                            }
                        }
                    } else if (realvalue < doubles[0]) {
                        if ((doubles[0] - realvalue) > rainfallEntity.getBelowMin()) {
                            if (exception == null) {
                                exception = new AbnormalDetailEntity() {{
                                    setFloatingDown(1);
                                    setFloatingUp(0);
                                }};
                            } else {
                                exception.setFloatingUp(0);
                                exception.setFloatingDown(1);
                            }
                        }
                    }
                }
                //保持时长
                if (exception != null) {
                    exception.setDate(mapval.get(e).getTime());
                    exception.setSensorCode(mapval.get(e).getSenId());
                    exception.setErrorValue(realvalue);
                    container[0].add(exception);
                }
            }
        });
        if(container[0] .size()>0) {
            abnormalDetailMapper.insertTide(container[0]);
            container[0] = null;
        }
    }
}
