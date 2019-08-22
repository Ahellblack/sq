package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.datepull.vo.TSDBVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.util.LocalDateUtil;
import com.siti.wisdomhydrologic.util.enumbean.DataError;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
@Component
public class TSDBWaterlevelValve implements Valve<TSDBVo, WaterLevelEntity, AbnormalDetailEntity>, ApplicationContextAware {

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
    public void beforeProcess(List<TSDBVo> realList) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        //获取雨量配置表
        Map<Integer, WaterLevelEntity> rainfallMap = Optional.of(abnormalDetailMapper.fetchAllW())
                .get()
                .stream()
                .collect(Collectors.toMap(WaterLevelEntity::getSensorCode, a -> a));
        Map<Integer, TSDBVo> map = realList.stream()
                .filter(
                        e -> ((e.getSENID() + "").substring(5)).equals(ConstantConfig.WS)
                ).collect(Collectors.toMap(TSDBVo::getSENID, a -> a, (value1, value2) -> {
                    return value2;
                }));
        doProcess(map, rainfallMap);
    }

    @Override
    public void doProcess(Map<Integer, TSDBVo> mapval, Map<Integer, WaterLevelEntity> configMap) {
        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            WaterLevelEntity config = configMap.get(e);
            if (config != null) {
                final double[] doubles = {99999};
                final double[] temp = {-99};
                final int[] timelimit = {0};
                TSDBVo vo = mapval.get(e);
                double[] arrayV = {vo.getV0(), vo.getV1(), vo.getV2(), vo.getV3(), vo.getV4(), vo.getV5(),
                        vo.getV6(), vo.getV7(), vo.getV8(), vo.getV9(), vo.getV10(), vo.getV11()};
                //中断次数
                int limit = config.getInterruptLimit();
                IntStream.range(0, 13 - limit).forEach(j -> {
                    final int[] flag = {0};
                    IntStream.range(j, j + limit).forEach(k -> {
                        if (arrayV[k] == -99) {
                            flag[0]++;
                        }
                    });
                    if (flag[0] == limit) {
                        exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                .date(LocalDateUtil
                                        .dateToLocalDateTime(vo.getTime())
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .sensorCode(vo.getSENID())
                                .dateError(DataError.INTENT_WATER.getErrorCode())
                                .build());
                    }
                });
                //数据不变的时长
                //最大上升 最大下降
                IntStream.range(0, arrayV.length).forEach(k -> {
                    String date = LocalDateUtil.dateToLocalDateTime(vo.getTime())
                            .plusHours(-1)
                            .plusMinutes(k * 5)
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    //存在设备异常
                    if (abnormalDetailMapper.selectRealExist(vo.getSENID(), date) > 0) {
                        exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                .date(date)
                                .sensorCode(vo.getSENID())
                                .equipmentError(DataError.EQ_WATER.getErrorCode())
                                .build());
                    }
                    if (temp[0] == arrayV[k]) {
                        timelimit[0]++;
                        if (timelimit[0] > config.getDuration() / 5) {
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(LocalDateUtil
                                            .dateToLocalDateTime(vo.getTime())
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .sensorCode(vo.getSENID())
                                    .dateError(DataError.WS_DURA_WL.getErrorCode())
                                    .build());
                        }
                    } else {
                        temp[0] = arrayV[k];
                        timelimit[0] = 1;
                    }
                    if (doubles[0] == 99999) {
                        doubles[0] = arrayV[k];
                    } else {
                        if (arrayV[k] > doubles[0]) {
                            if ((arrayV[k] - doubles[0]) > config.getUpMax()) {
                                exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                        .date(LocalDateUtil
                                                .dateToLocalDateTime(vo.getTime())
                                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                        .sensorCode(vo.getSENID())
                                        .dateError(DataError.INTENT_WATER_UPMAX.getErrorCode())
                                        .build());
                            }
                        } else if (arrayV[k] < doubles[0]) {
                            if ((doubles[0] - arrayV[k]) > config.getBelowMin()) {
                                exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                        .date(LocalDateUtil
                                                .dateToLocalDateTime(vo.getTime())
                                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                        .sensorCode(vo.getSENID())
                                        .dateError(DataError.CHANGE_SMALL_WL.getErrorCode())
                                        .build());
                            }
                        }
                    }
                });
            }
        });

        if (exceptionContainer[0].size() > 0) {
            abnormalDetailMapper.insertFinal(exceptionContainer[0]);
            exceptionContainer[0] = null;
        }
    }

}
