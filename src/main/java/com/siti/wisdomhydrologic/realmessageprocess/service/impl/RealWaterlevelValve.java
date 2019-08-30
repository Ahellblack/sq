package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
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

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
@Component
public class RealWaterlevelValve implements Valve<RealVo, WaterLevelEntity, AbnormalDetailEntity>, ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    AbnormalDetailMapper abnormalDetailMapper = null;

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    @Override
    public void beforeProcess(List<RealVo> realList) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        //获取雨量配置表
        Map<Integer, WaterLevelEntity> rainfallMap = Optional.of(abnormalDetailMapper.fetchAllW())
                .get()
                .stream()
                .collect(Collectors.toMap(WaterLevelEntity::getSensorCode, a -> a));
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.WS)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map, rainfallMap);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval, Map<Integer, WaterLevelEntity> configMap) {
        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        final double[] doubles = {66666};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            WaterLevelEntity config = configMap.get(e);
            if (config != null) {
                RealVo vo = mapval.get(e);
               /* double max = rainfallEntity.getLevelMax();
                double min = rainfallEntity.getLevelMin();*/
                double realvalue = mapval.get(e).getFACTV();
                if (realvalue < config.getLevelMin()) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId())
                            .errorValue(realvalue)
                            .dateError(DataError.LESS_SMALL_WL.getErrorCode())
                            .build());
                } else if (realvalue > config.getLevelMax()) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId())
                            .errorValue(realvalue)
                            .dateError(DataError.MORE_BIG_WL.getErrorCode())
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
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(date)
                                    .errorPeriod(date)
                                    .equipmentError(one.get("error_code").toString())
                                    .build());

                        }
                    }
                }



                //realvalue
                //最大上升 最大下降
                if (doubles[0] == 66666) {
                    doubles[0] = realvalue;
                } else {
                    if (realvalue > doubles[0]) {
                        if ((realvalue - doubles[0]) > config.getUpMax()) {
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(LocalDateUtil
                                            .dateToLocalDateTime(vo.getTime())
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .errorValue(realvalue)
                                    .dateError(DataError.CHANGE_BIG_WL.getErrorCode())
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
                                    .dateError(DataError.CHANGE_SMALL_WL.getErrorCode())
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
