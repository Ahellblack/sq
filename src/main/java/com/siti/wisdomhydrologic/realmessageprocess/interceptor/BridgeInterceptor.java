package com.siti.wisdomhydrologic.realmessageprocess.interceptor;

import com.google.common.collect.Lists;
import com.siti.wisdomhydrologic.realmessageprocess.entity.AbnormalDetailEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Indecators;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * Created by DC on 2019/7/24.
 *
 * @data ${DATA}-14:56
 */
@Service
public class BridgeInterceptor extends MessageProcessBridge {
    @Resource
    AbnormalDetailMapper abnormalDetailMapper;


    @Override
    public void setHandler(Indecators indecator) {
        super.setHandler(indecator);
    }

    @Override
    public void doInterceptor(List val, Map configMap) {
        //List handlerChain=super.handlerChain;
        final BlockingQueue[] cycleQueue = new BlockingQueue[1];
        IntStream.range(0, handlerChain.size()).forEach(i -> {
            cycleQueue[0] = new LinkedBlockingQueue();
            handlerChain.get(i).beforeProcess(val, configMap, cycleQueue[0]);
            List<AbnormalDetailEntity> allList = Lists.newArrayList();
            cycleQueue[0].drainTo(allList);
            abnormalDetailMapper.insertAndUpdate(allList);
        });
    }
}

