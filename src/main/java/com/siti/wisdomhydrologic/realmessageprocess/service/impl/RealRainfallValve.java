package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.DateTransform;
import com.siti.wisdomhydrologic.util.LocalDateUtil;
import com.siti.wisdomhydrologic.util.enumbean.DataError;
import com.siti.wisdomhydrologic.util.enumbean.EquimentError;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    @Override
    public void beforeProcess(List<RealVo> realList) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        //获取雨量配置表
        Map<Integer, RainfallEntity> rainfallMap = Optional.of(abnormalDetailMapper.fetchAllR())
                .get()
                .stream()
                .collect(Collectors.toMap(RainfallEntity::getSensorCode, a -> a));
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> ((e.getSenId() + "").substring(5)).equals(ConstantConfig.RS)
                ).collect(Collectors.toMap(RealVo::getSenId, a -> a));
        doProcess(map, rainfallMap);
    }

    @Override
    public void doProcess(Map<Integer, RealVo> mapval,  Map<Integer, RainfallEntity> configMap) {
        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        final String[] time = new String[1];
        mapval.keySet().stream().forEach(e -> {
            RealVo vo = mapval.get(e);
            RainfallEntity rainfallEntity = configMap.get(e);
            if (rainfallEntity != null) {
                time[0] =LocalDateUtil
                        .dateToLocalDateTime(vo.getTime())
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                configMap.remove(e);
                double realvalue = mapval.get(e).getFACTV();
                double max = rainfallEntity.getMaxFiveLevel();
                double min = rainfallEntity.getMinFiveLevel();
                if (realvalue < min) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId())
                            .errorValue(realvalue)
                            .dateError(DataError.FIVE_LESS_R.getErrorCode())
                            .build());
                } else if (realvalue > max) {
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(LocalDateUtil
                                    .dateToLocalDateTime(vo.getTime())
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .sensorCode(vo.getSenId())
                            .errorValue(realvalue)
                            .dateError(DataError.FIVE_MORE_R.getErrorCode())
                            .build());
                }
                if (rainfallEntity.getNearbySendorCode() != null) {
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
                        exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                .date(LocalDateUtil
                                        .dateToLocalDateTime(vo.getTime())
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .sensorCode(vo.getSenId())
                                .dateError(DataError.MORENEAR_R.getErrorCode())
                                .build());
                    }
                }
            }
        });/*
        //存在遗漏数据
        if(configMap.size()>0){
            configMap.keySet().forEach(e->{
                List<RealVo> rvs = abnormalDetailMapper.selectEle(e.toString().substring(0, 5)+"%", time[0]);
                if(rvs.size()==0){
                    //还要查询前5分钟
                    RealVo frvs= abnormalDetailMapper.selectBefore5Ele(e.toString().substring(0, 5)+"89", time[0]);
                    if(frvs==null) {
                        exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                .date(time[0])
                                .sensorCode(e)
                                .build());
                    }else{
                        if(frvs.getFACTV()<=11.2){
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(time[0])
                                    .sensorCode(e)
                                    .build());
                        }else{
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(time[0])
                                    .sensorCode(e)
                                    .build());
                        }
                    }
                }else{
                    exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                            .date(time[0])
                            .sensorCode(e)
                            .build());
                }
            });
        }*/
        if (exceptionContainer[0].size() > 0) {
            abnormalDetailMapper.insertFinal(exceptionContainer[0]);
            exceptionContainer[0] = null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


}
