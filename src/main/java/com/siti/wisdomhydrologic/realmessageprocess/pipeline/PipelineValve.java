package com.siti.wisdomhydrologic.realmessageprocess.pipeline;

import com.siti.wisdomhydrologic.realmessageprocess.mapper.AbnormalDetailMapper;
import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by DC on 2019/7/24.
 *
 * @data ${DATA}-14:56
 */
@Service
public class PipelineValve extends MessageProcessPipeline {

    @Resource
    AbnormalDetailMapper abnormalDetailMapper;

    @Override
    public void setHandler(Valve indecator) {
        super.setHandler(indecator);
    }

    @Override
    public void doInterceptor(List val, Map configMap) {
        IntStream.range(0, handlerChain.size()).forEach(i -> {
            handlerChain.get(i).beforeProcess(val, configMap);
        });
    }
}

