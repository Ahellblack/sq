package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.ATEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.LocalDateUtil;
import com.siti.wisdomhydrologic.util.enumbean.DataError;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */

public  class RealATValve implements Valve<RealVo,ATEntity,AbnormalDetailEntity>,ApplicationContextAware {

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
        //获取at
        Map<Integer, ATEntity> config = Optional.of(abnormalDetailMapper.fetchAllAT())
                .get()
                .stream()
                .collect(Collectors.toMap(ATEntity::getSensorCode, b -> b));
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.WAT)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map,config);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval, Map<Integer, ATEntity> configMap) {

        final double[] doubles={99999};
        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            ATEntity config = configMap.get(e);
            if(config!=null) {
                RealVo vo=mapval.get(e);
                double realvalue= mapval.get(e).getFACTV();
                double max = config.getLevelMax();
                double min = config.getLevelMin();
                if (realvalue < min) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId())
                            .errorValue(realvalue)
                            .dateError(DataError.LESS_SMALL_AT.getErrorCode())
                            .build());
                } else if (realvalue > max) {
                    if ((doubles[0]-realvalue ) > config.getBelowMin()) {
                        exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                .date(LocalDateUtil
                                        .dateToLocalDateTime(vo.getTime())
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .sensorCode(vo.getSenId())
                                .errorValue(realvalue)
                                .dateError(DataError.MORE_BIG_AT.getErrorCode())
                                .build());
                    }
                }
                //最大上升 最大下降
                if (doubles[0] == 9999) {
                    doubles[0] = realvalue;
                } else {
                    if (realvalue> doubles[0]) {
                        if ((realvalue - doubles[0]) > config.getUpMax()) {
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(LocalDateUtil
                                            .dateToLocalDateTime(vo.getTime())
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .errorValue(realvalue)
                                    .dateError(DataError.CHANGE_BIG_AT.getErrorCode())
                                    .build());
                        }
                    } else if (realvalue < doubles[0]) {
                        if ((doubles[0]-realvalue ) > config.getBelowMin()) {
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(LocalDateUtil
                                            .dateToLocalDateTime(vo.getTime())
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .sensorCode(vo.getSenId())
                                    .errorValue(realvalue)
                                    .dateError(DataError.CHANGE_SMALL_AT.getErrorCode())
                                    .build());
                        }
                    }
                }
            }
        });
        if (exceptionContainer[0].size() > 0) {
            abnormalDetailMapper.insertFinal(exceptionContainer[0]);
            exceptionContainer[0] = null;
        }
    }
}
