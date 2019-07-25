package com.siti.wisdomhydrologic.realmessageprocess.interceptor;

import com.siti.wisdomhydrologic.realmessageprocess.service.Indecators;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-11:49
 */

public abstract class MessageProcessBridge {

    public List<Indecators> handlerChain = new ArrayList<>(100);

    /**
     * 添加handler
     */
    public void setHandler(Indecators indecator) {
        handlerChain.add(indecator);
    }

    /**
     * 处理方法
     */
    public abstract void doInterceptor(List<T> val, Map<String, Map<String, T>> configMap) ;

}
