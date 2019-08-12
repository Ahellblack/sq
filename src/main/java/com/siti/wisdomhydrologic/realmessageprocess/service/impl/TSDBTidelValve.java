package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.TideLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.TSDBVo;
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
public class TSDBTidelValve implements Valve<TSDBVo,TideLevelEntity,AbnormalDetailEntity>,ApplicationContextAware {

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
    public void beforeProcess(List<TSDBVo> val, Map<String, Map<Integer, TideLevelEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {

    }

    @Override
    public void doProcess(Map<Integer, TSDBVo> val, Map<String, Map<Integer, TideLevelEntity>> configMap, BlockingQueue<AbnormalDetailEntity> cycleQueue) {

    }

    @Override
    public void beforeProcess(List<TSDBVo> realList, Map<String, Map<Integer, TideLevelEntity>> configMap) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);

        Map<Integer, TSDBVo> map = realList.stream()
                .filter(
                    e -> ((e.getSENID() + "").substring(5)).equals(ConstantConfig.TS)
                ).collect(Collectors.toMap(TSDBVo::getSENID, a -> a,(value1,value2)->{
            return value2;
        }));
        doProcess(map, configMap);
    }

    @Override
    public void doProcess(Map<Integer,TSDBVo> val, Map<String, Map<Integer, TideLevelEntity>> configMap) {
        Map<Integer, TideLevelEntity> waterFlag =  configMap
                .get(ConstantConfig.FLAGT);
        Map<Integer, TSDBVo> mapval=val;
        final List[] container={new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            TideLevelEntity config = waterFlag.get(e);
            if(config!=null){
            final double[] doubles={99999};
            final double[] temp={-99};
            final int[] timelimit={0};
            TSDBVo vo=mapval.get(e);
            double[] arrayV = {vo.getV0(), vo.getV1(), vo.getV2(), vo.getV3(), vo.getV4(), vo.getV5(),
                    vo.getV6(), vo.getV7(), vo.getV8(), vo.getV9(), vo.getV10(), vo.getV11()};
            //中断次数
            int limit = config.getInterruptLimit();
            final AbnormalDetailEntity[] entity = new AbnormalDetailEntity[1];
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
                    }else{
                        entity[0].setContinueInterrupt(1);
                    }
                }
            });
            //数据不变的时长
            //最大上升 最大下降
            IntStream.range(0,arrayV.length).forEach(k-> {
                if(temp[0]==arrayV[k]){
                    timelimit[0]++;
                    if(timelimit[0]>config.getDuration()/5){
                        if( entity[0]==null) {
                            entity[0] = new AbnormalDetailEntity() {{
                                setKeepTime(1);
                            }};
                        }else{
                            entity[0].setKeepTime(1);
                        }
                    }
                }else{
                    temp[0]=arrayV[k];
                    timelimit[0]=1;
                }
                if (doubles[0] == 99999) {
                    doubles[0] = arrayV[k];
                } else {
                    if ( arrayV[k] > doubles[0]) {
                        if ((arrayV[k] - doubles[0]) > config.getUpMax()) {
                            if (entity[0] == null) {
                                entity[0] = new AbnormalDetailEntity() {{
                                    setFloatingUp(1);
                                }};
                            } else {
                                entity[0].setFloatingUp(1);
                            }
                        }
                    } else if ( arrayV[k] < doubles[0]) {
                        if ((doubles[0] - arrayV[k]) > config.getBelowMin()) {
                            if (entity[0] == null) {
                                entity[0] = new com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity() {{
                                    setFloatingDown(1);
                                }};
                            } else {
                                entity[0].setFloatingDown(1);
                            }
                        }
                    }
                }
            });
            if (entity[0] != null) {
                entity[0].setSensorCode(mapval.get(e).getSENID());
                entity[0].setDate(mapval.get(e).getTime());
                container[0].add(entity[0]);
            }
        }});
        if(container[0].size()>0){
            abnormalDetailMapper.insertTSDBTide(container[0]);
            container[0]=null;
        }
    }

}
