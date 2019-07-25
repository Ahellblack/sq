package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.realmessageprocess.Entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.Entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.service.Indecators;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.enumbean.SystemConstant;

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
public class RealRainfallHandler<T> implements Indecators<T> {

    @Override
    public void beforeProcess(List<T> val, Map<String, Map<String, T>> configMap, BlockingQueue<T> cycleQueue) {
        List<RealVo> realList = (List<RealVo>) val;
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> (e.getSendId() + "".substring(5)).equals(SystemConstant.WS)
                ).collect(Collectors.toMap(RealVo::getSendId, a -> a));
        doProcess((Map<Integer, T>) map,configMap,cycleQueue);
    }

    @Override
    public void doProcess(Map<Integer, T> val, Map<String, Map<String, T>> configMap, BlockingQueue<T> cycleQueue) {
        Map<String, RainfallEntity> rainonfig = (Map<String, RainfallEntity>) configMap.get(SystemConstant.FLAGR);
        Map<Integer, RealVo> mapval=(Map<Integer, RealVo>)val;
        mapval.entrySet().stream().forEach(e -> {
            RainfallEntity rainfallEntity = rainonfig.get(e.getKey());
            double max = rainfallEntity.getMaxFiveLevel();
            double min = rainfallEntity.getMinFiveLevel();
            AbnormalDetailEntity exception = null;
            if (e.getValue().getFactv() < min) {
                exception = new AbnormalDetailEntity() {{
                    setSensorCode(e.getKey());
                    setDate(e.getValue().getTime().toString());
                    setFiveBelow(1);
                }};
            } else if (e.getValue().getFactv() > max) {
                if (exception == null) {
                    exception = new AbnormalDetailEntity() {{
                        setSensorCode(e.getKey());
                        setDate(e.getValue().getTime().toString());
                        setFiveAbove(1);
                    }};
                } else {
                    exception.setFiveAbove(1);
                }
            }
            //附近三个点位
            String[] sendorcodeArr = rainfallEntity.getNearbySendorCode().split(",");
            final double[] calval = {0};
            IntStream.range(0, sendorcodeArr.length).forEach(i -> {
                if (mapval.containsKey(sendorcodeArr[i])) {
                    calval[0] = calval[0] + mapval.get(sendorcodeArr[i]).getFactv().doubleValue();
                }
            });
            double avgRate = (calval[0] / sendorcodeArr.length);
            if ((e.getValue().getFactv() - avgRate) / avgRate > rainfallEntity.getNearbyRate()) {
                if (exception == null) {
                    exception = new AbnormalDetailEntity() {{
                        setSensorCode(e.getKey());
                        setDate(e.getValue().getTime().toString());
                        setMoreNear(1);
                    }};
                } else {
                    exception.setFiveAbove(1);
                }
            }
            if (exception != null) {
                cycleQueue.add((T) exception);
            }
        });
    }

}
