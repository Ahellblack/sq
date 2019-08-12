package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
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
import java.util.stream.IntStream;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
@Component
public class RealRainfallValve implements Valve<RealVo, RainfallEntity, AbnormalDetailEntity>, ApplicationContextAware {

    private static ApplicationContext context = null;

    AbnormalDetailMapper abnormalDetailMapper = null;

    @Override
    public void beforeProcess(List<RealVo> val, Map<String, Map<Integer, RainfallEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {
    }

    @Override
    public void doProcess(Map<Integer, RealVo> val, Map<String, Map<Integer, RainfallEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {

    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    @Override
    public void beforeProcess(List<RealVo> realList, Map<String, Map<Integer, RainfallEntity>> configMap) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);

        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.RS)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map, configMap);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval, Map<String, Map<Integer, RainfallEntity>> configMap) {
        Map<Integer, RainfallEntity> rainonfig = configMap.get(ConstantConfig.FLAGR);
        final List[] container = {new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            RainfallEntity rainfallEntity = rainonfig.get(e);
            if (rainfallEntity != null) {
               double realvalue= mapval.get(e).getFACTV();
                double max = rainfallEntity.getMaxFiveLevel();
                double min = rainfallEntity.getMinFiveLevel();
                AbnormalDetailEntity exception =null;
                if (realvalue < min) {
                    exception = new AbnormalDetailEntity() {{
                        setFiveBelow(1);
                    }};
                } else if (realvalue > max) {
                    if (exception == null) {
                        exception = new AbnormalDetailEntity() {{
                            setFiveAbove(1);
                        }};
                    } else {
                        exception.setFiveAbove(1);
                    }
                }
                if(rainfallEntity.getNearbySendorCode()!=null){
                    //附近三个点位
                    String[] sendorcodeArr = rainfallEntity.getNearbySendorCode().split(",");
                    final double[] calval = {0};
                    IntStream.range(0, sendorcodeArr.length).forEach(i -> {
                        if (mapval.containsKey(sendorcodeArr[i])) {
                            calval[0] = calval[0] + mapval.get(sendorcodeArr[i]).getFACTV();
                        }
                    });
                    double avgRate = (calval[0] / sendorcodeArr.length);
                    if ((realvalue - avgRate) / avgRate > rainfallEntity.getNearbyRate()) {
                        if (exception == null) {
                            exception = new AbnormalDetailEntity() {{
                                setMoreNear(1);
                            }};
                        } else {
                            exception.setMoreNear(1);
                        }
                    }
                }
                if (exception != null) {
                    exception.setSensorCode(e);
                    exception.setDate(mapval.get(e).getTime());
                    exception.setErrorValue(mapval.get(e).getFACTV());
                    container[0].add(exception);
                }
            }
        });
        if (container[0].size() > 0) {
            abnormalDetailMapper.insertRain(container[0]);
            container[0] = null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
