package com.siti.wisdomhydrologic.realmessageprocess.listener;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.siti.wisdomhydrologic.config.ColorsExecutor;
import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.config.RabbitMQConfig;
import com.siti.wisdomhydrologic.datepull.mapper.DayDataMapper;
import com.siti.wisdomhydrologic.datepull.mapper.TSDBMapper;
import com.siti.wisdomhydrologic.datepull.service.impl.DayDataServiceImpl;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.RainFallMapper;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.TideLevelMapper;
import com.siti.wisdomhydrologic.realmessageprocess.mapper.WaterLevelMapper;
import com.siti.wisdomhydrologic.realmessageprocess.pipeline.PipelineValve;
import com.siti.wisdomhydrologic.realmessageprocess.service.impl.DayRainfallValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
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
public class HourListener {
    @Resource
    private TSDBMapper tsdbMapper;
    @Resource
    RainFallMapper rainFallMapper;
    @Resource
    TideLevelMapper tideLevelMapper;
    @Resource
    WaterLevelMapper waterLevelMapper;
    @Resource
    private DayDataMapper dayDataMapper;
    @Resource
    PipelineValve valvo;
    @Resource
    private DayDataServiceImpl dayDataService;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private AtomicInteger maxBatch = new AtomicInteger(0);
    private AtomicBoolean flag = new AtomicBoolean(false);
    private AtomicInteger sumSize = new AtomicInteger(0);
    private BlockingQueue<List<DayVo>> receiver;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_HOUR)
    @RabbitHandler
    public void dayprocess(List<DayVo> vo, Channel channel, Message message) {
        try {
            System.out.println("-------------"+vo.size());
            if (vo.size() > 0) {
                calPackage(vo, channel, message);
            } else {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
          /*  try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
            logger.error(e.getMessage());
        }
    }

    /**
     * 判断是否丢包记录日志
     */
    private void calPackage(List<DayVo> List, Channel channel, Message message) throws Exception {
        DayVo vo = List.get(0);
        /*Thread putThread = new Thread(() -> {
            multiProcess();
        });*/
        if (flag.compareAndSet(false, true)) {
            new Thread(() -> {
                multiProcess();
            }).start();
            receiver = new LinkedBlockingQueue(5);
            maxBatch.set(vo.getMaxBatch());
            sumSize.set(vo.getSumSize());
            valvo.setHandler(new DayRainfallValve());
            logger.info("**********Day*********receive start********");
        }
        int currentsize = vo.getCurrentSize();
        int currentbatch = vo.getCurrentBatch();
        int stastus = vo.getStatus();
        if (stastus == 1) {
            if (sumSize.get() == currentsize && maxBatch.get() == currentbatch) {
                logger.info("hour消息成功消费完成无丢包！");
                logger.info("**********hour*********success end********");
            }
        }
        receiver.put(List);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        logger.info("Hour消费者----总包数:{},当前包数:{},总条数:{},条数;{},状态:{}", maxBatch.get(),
                currentbatch, sumSize.get(), currentsize, vo.getStatus());
    }

    /**
     * 触发一次消费任务
     */
    private void multiProcess() {
      /*  //获取水位配置表
        Map<Integer, Object> waterLevelMap = Optional.of(waterLevelMapper.fetchAll())
                .get()
                .stream()
                .collect(Collectors.toMap(WaterLevelEntity::getSensorCode, a -> a));
        //获取潮位配置表
        Map<Integer, Object> tideLevelMap = Optional.of(tideLevelMapper.fetchAll())
                .get()
                .stream()
                .collect(Collectors.toMap(TideLevelEntity::getSensorCode, b -> b));*/
        //获取雨量配置表
        Map<Integer, Object> rainfallMap = Optional.of(rainFallMapper.fetchAll())
                .get()
                .stream()
                .collect(Collectors.toMap(RainfallEntity::getSensorCode, a -> a));
        Map<String, Map<Integer, Object>> configMap = Maps.newHashMap();
        configMap.put(ConstantConfig.FLAGR, rainfallMap);

        ColorsExecutor colors = new ColorsExecutor();
        colors.init();
        ThreadPoolExecutor es = colors.getCustomThreadPoolExecutor();
        Runnable fetchTask = () -> {
            List<DayVo> voList = receiver.poll();
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
            dayDataService.addHourData(arrayList.subList(e * size, (e + 1) * size > all ? all : size * (e + 1)));
        });
    }
}




