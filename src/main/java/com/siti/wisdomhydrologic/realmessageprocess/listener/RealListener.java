package com.siti.wisdomhydrologic.realmessageprocess.listener;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.siti.wisdomhydrologic.config.ColorsExecutor;
import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.config.RabbitMQConfig;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.TideLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.RainFallMapper;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.RealMapper;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.TideLevelMapper;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.WaterLevelMapper;
import com.siti.wisdomhydrologic.realmessageprocess.pipeline.PipelineValve;
import com.siti.wisdomhydrologic.realmessageprocess.service.impl.RealRainfallValve;
import com.siti.wisdomhydrologic.realmessageprocess.service.impl.RealTidelValve;
import com.siti.wisdomhydrologic.realmessageprocess.service.impl.RealWaterlevelValve;
import com.siti.wisdomhydrologic.realmessageprocess.vo.RealVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by DC on 2019/6/12.
 *
 * @data ${DATA}-15:23
 */
@Component
@Transactional
public class RealListener {

    @Resource
    RainFallMapper rainFallMapper;
    @Resource
    TideLevelMapper tideLevelMapper;
    @Resource
    WaterLevelMapper waterLevelMapper;
    @Resource
    RealMapper realMapper;
    @Resource
    PipelineValve valvo;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private AtomicInteger maxBatch = new AtomicInteger(0);
    private AtomicBoolean flag = new AtomicBoolean(false);
    private AtomicInteger sumSize = new AtomicInteger(0);
    private BlockingQueue<List<RealVo>> receiver;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_REAL)
    @RabbitHandler
    public void realProcess(List<RealVo> RealVo, Channel channel, Message message) {
        try {
            if (RealVo.size() > 0) {
                calPackage(RealVo, channel, message);
            } else {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            logger.error(e.getMessage());
        }
    }

    /**
     * 判断是否丢包记录日志
     */
    private void calPackage(List<RealVo> RealVoList, Channel channel, Message message) throws Exception {
        RealVo vo = RealVoList.get(0);
        Thread putThread = new Thread(() -> {
            multiProcess();
        });
        if (flag.compareAndSet(false, true)) {
            receiver = new LinkedBlockingQueue(5);
            maxBatch.set(vo.getMaxBatch());
            sumSize.set(vo.getSumSize());
            valvo.setHandler(new RealRainfallValve());
            valvo.setHandler(new RealTidelValve());
            valvo.setHandler(new RealWaterlevelValve());
            logger.info("receive first packages from day_queue and start put message into queue!");
        }
        int currentsize = vo.getCurrentSize();
        int currentbatch = vo.getCurrentBatch();
        int stastus = vo.getStatus();
        if (stastus == 1) {
            if (sumSize.get() == currentsize && maxBatch.get() == currentbatch) {
                logger.info("real消息成功消费完成无丢包！");
            }
        }
        receiver.put(RealVoList);
        putThread.start();
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        logger.info("real_queue消费者获取day数据...总包数:{},当前包数:{},总条数:{},条数;{},状态:{}", maxBatch.get(),
                currentbatch, sumSize.get(), currentsize, vo.getStatus());
    }

    /**
     * 触发一次消费任务
     */
    private void multiProcess() {
        //获取水位配置表
        Map<Integer, Object> waterLevelMap = Optional.of(waterLevelMapper.fetchAll())
                .get()
                .stream()
                .collect(Collectors.toMap(WaterLevelEntity::getSensorCode, a -> a));
        //获取潮位配置表
        Map<Integer, Object> tideLevelMap = Optional.of(tideLevelMapper.fetchAll())
                .get()
                .stream()
                .collect(Collectors.toMap(TideLevelEntity::getSensorCode, b -> b));
        //获取雨量配置表
        Map<Integer, Object> rainfallMap = Optional.of(rainFallMapper.fetchAll())
                .get()
                .stream()
                .collect(Collectors.toMap(RainfallEntity::getSensorCode, a -> a));
        Map<String, Map<Integer, Object>> configMap = Maps.newHashMap();
        configMap.put(ConstantConfig.FLAGW, waterLevelMap);
        configMap.put(ConstantConfig.FLAGT, tideLevelMap);
        configMap.put(ConstantConfig.FLAGR, rainfallMap);
        ColorsExecutor colors = new ColorsExecutor();
        colors.init();
        ThreadPoolExecutor es = colors.getCustomThreadPoolExecutor();
        Runnable fetchTask = () -> {
            List<RealVo> voList = receiver.poll();
            if (voList != null) {
                splitList(voList, 100);
                valvo.doInterceptor(voList, configMap);
            }
        };
        while (true) {
            if (es.getQueue().size() < 2) {
                es.execute(fetchTask);
            }
            if (receiver.isEmpty()) {
                es.shutdown();
                flag.compareAndSet(true, false);
                break;
            }

        }
    }

    public void splitList(List arrayList, int size) {
        int all = arrayList.size();
        int cycle = all % size == 0 ? all / size : (all / size + 1);
        IntStream.range(0, cycle).forEach(e -> {
            realMapper.insertReal(arrayList.subList(e * size, (e + 1) * size > all ? all : size * (e + 1)));
        });
    }
}




