package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity;
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
    public void beforeProcess(List<RealVo> val, Map<String, Map<Integer, WaterLevelEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {

    }

    @Override
    public void doProcess(Map<Integer, RealVo> val, Map<String, Map<Integer, WaterLevelEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {

    }

    @Override
    public void beforeProcess(List<RealVo> realList, Map<String, Map<Integer, WaterLevelEntity>> configMap) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);

        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.WS)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map, configMap);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval, Map<String, Map<Integer, WaterLevelEntity>> configMap) {
        Map<Integer, WaterLevelEntity> waterFlag = configMap
                .get(ConstantConfig.FLAGW);
        final List[] container = {new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            WaterLevelEntity rainfallEntity = waterFlag.get(e);
            if (rainfallEntity != null) {
                double max = rainfallEntity.getLevelMax();
                double min = rainfallEntity.getLevelMin();
                double realvalue= mapval.get(e).getFACTV();
                AbnormalDetailEntity exception = null;
                if (realvalue < min) {
                    exception = new AbnormalDetailEntity() {{
                        setFiveAbove(0);
                        setFiveBelow(1);
                    }};
                } else if (realvalue > max) {
                    if (exception == null) {
                        exception = new AbnormalDetailEntity() {{
                            setFiveAbove(1);
                            setFiveBelow(0);
                        }};
                    } else {
                        exception.setFiveBelow(0);
                        exception.setFiveAbove(1);
                    }
                }
                //保持时长
                if (exception != null) {
                    exception.setDate(DateTransform.format(mapval.get(e).getTime()));
                    exception.setSensorCode(mapval.get(e).getSenId());
                    exception.setErrorValue(realvalue);
                    container[0].add(exception);
                }
            }
        });
        if (container[0].size() > 0) {
            abnormalDetailMapper.insertWater(container[0]);
            container[0] = null;
        }
    }

}
