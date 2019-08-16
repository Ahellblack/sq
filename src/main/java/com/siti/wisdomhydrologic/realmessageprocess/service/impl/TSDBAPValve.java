package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.datepull.vo.TSDBVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.APEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.ATEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
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
import java.util.stream.IntStream;

/**
 * Created by DC on 2019/7/18.
 *
 * @data${DATA}-9:54
 */
public class TSDBAPValve implements Valve<TSDBVo, APEntity, AbnormalDetailEntity>, ApplicationContextAware {
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
    public void beforeProcess(List<TSDBVo> realList) {
        abnormalDetailMapper = getBean(AbnormalDetailMapper.class);
        //获取风速度配置表
        Map<Integer, APEntity> ws = Optional.of(abnormalDetailMapper.fetchAllAP())
                .get()
                .stream()
                .collect(Collectors.toMap(APEntity::getSensorCode, b -> b));
        Map<Integer, TSDBVo> map = realList.stream()
                .filter(
                        e -> ((e.getSENID() + "").substring(5)).equals(ConstantConfig.WAP)
                ).collect(Collectors.toMap(TSDBVo::getSENID, a -> a));
        doProcess(map,ws);
    }

    @Override
    public void doProcess(Map<Integer, TSDBVo> mapval, Map<Integer, APEntity> configMap) {

        final List[] exceptionContainer = {new ArrayList<AbnormalDetailEntity>()};
        mapval.keySet().stream().forEach(e -> {
            APEntity config = configMap.get(e);
            if (config != null) {
                final double[] doubles = {66666};
                final int[] timelimit = {0};
                TSDBVo vo = mapval.get(e);
                double[] arrayV = {vo.getV0(), vo.getV1(), vo.getV2(), vo.getV3(), vo.getV4(), vo.getV5(),
                        vo.getV6(), vo.getV7(), vo.getV8(), vo.getV9(), vo.getV10(), vo.getV11()};
                //中断次数
                int limit = config.getInterruptLimit();
                if(limit<13&&limit>0){
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
                                    .dateError(DataError.WS_INTER_AP.getErrorCode())
                                    .build());
                        }
                    });
                }else{
                    logger.error("TSDBAT中断次数不能超过12次");
                }
               String excepVal= config.getExceptionValue();
                IntStream.range(0, arrayV.length).forEach(k -> {
                    //判断设备异常
                    if(excepVal!=null&&!excepVal.equals("")){
                        JSONArray jarray=JSONArray.parseArray(excepVal);
                       IntStream.range(0,jarray.size()).forEach(i->{
                          JSONObject obj= (JSONObject) jarray.get(i);
                           if(obj.get("error_value").toString().equals(arrayV[k]+"")){
                               exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                       .date(LocalDateUtil
                                               .dateToLocalDateTime(vo.getTime())
                                               .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                       .sensorCode(vo.getSENID())
                                        .errorValue(arrayV[k])
                                       .equipmentError(obj.get("error_code").toString())
                                       .build());
                           }
                       });
                       }
                    if (doubles[0] == arrayV[k]) {
                        timelimit[0]++;
                        if (timelimit[0] > config.getDuration() / 5) {
                            exceptionContainer[0].add(new AbnormalDetailEntity.builer()
                                    .date(LocalDateUtil
                                            .dateToLocalDateTime(vo.getTime())
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .sensorCode(vo.getSENID())
                                    .dateError(DataError.WS_DURA_AP.getErrorCode())
                                    .build());
                        }
                    } else {
                        doubles[0] = arrayV[k];
                        timelimit[0] = 1;
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