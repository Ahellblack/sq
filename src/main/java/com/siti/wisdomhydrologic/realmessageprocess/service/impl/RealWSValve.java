package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.WSEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.LocalDateUtil;
import com.siti.wisdomhydrologic.util.enumbean.DataError;
import org.slf4j.LoggerFactory;
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
public  class RealWSValve implements Valve<RealVo,WSEntity,AbnormalDetailEntity>,ApplicationContextAware {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

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
        //获取风速度配置表
        Map<Integer, WSEntity> ws = Optional.of(abnormalDetailMapper.fetchWS())
                .get()
                .stream()
                .collect(Collectors.toMap(WSEntity::getSensorCode, b -> b));
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.WSS)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map,ws);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval, Map<Integer, WSEntity> configMap) {
        final double[] doubles={99999};
        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            WSEntity config = configMap.get(e);
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
                            .dateError(DataError.LESS_SMALL.getErrorCode())
                            .build());
                } else if (realvalue > max) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId())
                            .errorValue(realvalue)
                            .dateError(DataError.MORE_BIG.getErrorCode())
                            .build());
                }

                String JsonConfig = config.getExceptionValue();
                if (!JsonConfig.equals("") && JsonConfig != null) {
                    JSONArray array = JSONArray.parseArray(JsonConfig);
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject one = (JSONObject) array.get(i);
                        if (one.get("error_value").equals(realvalue + "")) {
                            String date = LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            //TODO
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(date)
                                    .errorPeriod(date)
                                    .dateError(date)
                                    .equipmentError(one.get("error_code").toString())
                                    .build());

                        }
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
                                    .sensorCode(vo.getSenId())
                                    .dateError(DataError.CHANGE_BIG.getErrorCode())
                                    .errorValue(realvalue)
                                    .build());
                        }
                    } else if (realvalue < doubles[0]) {
                        if ((doubles[0] - realvalue) > config.getBelowMin()) {

                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(LocalDateUtil
                                            .dateToLocalDateTime(vo.getTime())
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .sensorCode(vo.getSenId())
                                    .errorValue(realvalue)
                                    .dateError(DataError.CHANGE_SMALL.getErrorCode())
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
