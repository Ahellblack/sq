package com.siti.wisdomhydrologic.realmessageprocess.pipeline;

import com.siti.wisdomhydrologic.realmessageprocess.service.Valve;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-11:49
 */
//抽象和实现分离
public abstract class MessageProcessPipeline {

    public List<Valve> handlerChain = new ArrayList<>(20);

    /**
     * 添加handler
     */
    public void setHandler(Valve indecator) {
        handlerChain.add(indecator);
    }

    /**
     * 处理方法
     */
    public abstract void doInterceptor(List<T> val, Map<String, Map<String, T>> configMap) ;



}
