package com.siti.wisdomhydrologic.realmessageprocess.service.impl;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.realmessageprocess.service.Indecators;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-9:54
 */
public class RealWaterlevelHandler<T> implements Indecators<T> {

    @Override
    public void beforeProcess(List<T> val, Map<String, Map<String, T>> configMap, BlockingQueue<T> cycleQueue) {
        List<RealVo> realList = (List<RealVo>) val;
        Map<Integer, RealVo> map = realList.stream()
                .filter(
                        e -> (e.getSendId() + "".substring(5)).equals(ConstantConfig.WS)
                ).collect(Collectors.toMap(RealVo::getSendId, a -> a));
        doProcess((Map<Integer, T>) map,configMap,cycleQueue);
    }

    @Override
    public void doProcess(Map<Integer,T> val, Map<String, Map<String, T>> configMap, BlockingQueue<T> cycleQueue) {
        Map<String, com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity> waterFlag = (Map<String, com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity>) configMap
                .get(ConstantConfig.FLAGW);
        Map<Integer, RealVo> mapval=(Map<Integer, RealVo>)val;
        final double[] doubles={99999};
        mapval.keySet().stream().forEach(e -> {
            //        最大值最小值比较
            com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity rainfallEntity = waterFlag.get(e);
            double max = rainfallEntity.getLevelMax();
            double min = rainfallEntity.getLevelMin();
            com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity exception = null;
            if (mapval.get(e).getFactv() < min) {
                exception = new com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity() {{
                    setSensorCode(mapval.get(e).getSendId());
                    setDate(mapval.get(e).getTime().toString());
                    setFiveBelow(1);
                }};
            } else if (mapval.get(e).getFactv() > max) {
                if (exception == null) {
                    exception = new com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity() {{
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
                            exception = new com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity() {{
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
                            exception = new com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity() {{
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
