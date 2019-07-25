package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.realmessageprocess.Entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.Entity.WaterLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.service.Indecators;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import com.siti.wisdomhydrologic.util.enumbean.SystemConstant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
public class RealTidelHandler<T> implements Indecators<T> {

    @Override
    public void beforeProcess(List<T> val, Map<String, Map<String, T>> configMap, BlockingQueue<T> cycleQueue) {
        List<RealVo> realList = (List<RealVo>) val;
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> (e.getSendId() + "".substring(5)).equals(SystemConstant.TS)
                ).collect(Collectors.toMap(RealVo::getSendId, a -> a));
        doProcess((Map<Integer, T>) map,configMap,cycleQueue);
    }

    @Override
    public void doProcess(Map<Integer,T> val, Map<String, Map<String, T>> configMap, BlockingQueue<T> cycleQueue) {
        Map<String, WaterLevelEntity> waterFlag = (Map<String, WaterLevelEntity>) configMap
                .get(SystemConstant.FLAGT);
        Map<Integer, RealVo> mapval=(Map<Integer, RealVo>)val;
        final double[] doubles={99999};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            WaterLevelEntity rainfallEntity = waterFlag.get(e);
            double max = rainfallEntity.getLevelMax();
            double min = rainfallEntity.getLevelMin();
            AbnormalDetailEntity exception = null;
            if (mapval.get(e).getFactv() < min) {
                exception = new AbnormalDetailEntity() {{
                    setSensorCode(mapval.get(e).getSendId());
                    setDate(mapval.get(e).getTime().toString());
                    setFiveBelow(1);
                }};
            } else if (mapval.get(e).getFactv() > max) {
                if (exception == null) {
                    exception = new AbnormalDetailEntity() {{
                        setSensorCode(mapval.get(e).getSendId());
                        setDate(mapval.get(e).getTime().toString());
                        setFiveAbove(1);
                    }};
                } else {
                    exception.setFiveAbove(1);
                }
            }
            //最大上升 最大下降
            if(doubles[0]==9999){
                doubles[0]=mapval.get(e).getFactv();
            }else{
                if(mapval.get(e).getFactv()>doubles[0]){
                    if((mapval.get(e).getFactv()-doubles[0])>rainfallEntity.getUpMax()){
                        if (exception == null) {
                            exception = new AbnormalDetailEntity() {{
                                setSensorCode(mapval.get(e).getSendId());
                                setDate(mapval.get(e).getTime().toString());
                                setFloatingUp(1);
                            }};
                        } else {
                            exception.setFloatingUp(1);
                        }
                    }
                }else if(mapval.get(e).getFactv()<doubles[0]){
                    if((doubles[0]-mapval.get(e).getFactv())>rainfallEntity.getBelowMin()){
                        if (exception == null) {
                            exception = new AbnormalDetailEntity() {{
                                setSensorCode(mapval.get(e).getSendId());
                                setDate(mapval.get(e).getTime().toString());
                                setFloatingDown(1);
                            }};
                        } else {
                            exception.setFloatingDown(1);
                        }
                    }
                }
            }
            //保持时长

            if (exception != null) {
                cycleQueue.add((T) exception);
            }
        });
    }
}
