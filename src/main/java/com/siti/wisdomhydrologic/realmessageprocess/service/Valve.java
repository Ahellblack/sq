package com.siti.wisdomhydrologic.realmessageprocess.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Created by DC on 2019/7/18.
 *
 * @data ${DATA}-14:47
 */
public interface Valve<T,F,G> {

    void beforeProcess(List<T> val);

    void doProcess(Map<Integer, T> val,  Map<Integer, F> configMap);

}
