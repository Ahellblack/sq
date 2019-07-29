//package com.siti.wisdomhydrologic.realmessageprocess.listener;
//
//import com.google.common.collect.Maps;
//import com.rabbitmq.client.Channel;
//import com.siti.wisdomhydrologic.config.RabbitMQConfig;
//import com.siti.wisdomhydrologic.datepull.vo.DayVo;
//import com.siti.wisdomhydrologic.realmessageprocess.entity.RainfallEntity;
//import com.siti.wisdomhydrologic.realmessageprocess.entity.TideLevelEntity;
//import com.siti.wisdomhydrologic.realmessageprocess.entity.WaterLevelEntity;
//import com.siti.wisdomhydrologic.realmessageprocess.interceptor.BridgeInterceptor;
//import com.siti.wisdomhydrologic.realmessageprocess.mapper.RainFallMapper;
//import com.siti.wisdomhydrologic.realmessageprocess.mapper.TideLevelMapper;
//import com.siti.wisdomhydrologic.realmessageprocess.mapper.WaterLevelMapper;
//import com.siti.wisdomhydrologic.util.ExceptionUtil;
//import com.siti.wisdomhydrologic.util.enumbean.ReturnError;
//import com.siti.wisdomhydrologic.util.enumbean.SystemConstant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.stream.Collectors;
//
///**
// * Created by DC on 2019/6/12.
// *
// * @data ${DATA}-15:23
// */
//@Component
//public class RealConsumer {
//
//    @Resource
//    RainFallMapper rainFallMapper;
//    @Resource
//    TideLevelMapper tideLevelMapper;
//    @Resource
//    WaterLevelMapper waterLevelMapper;
//    @Resource
//    BridgeInterceptor MessageProcessInterceptor;
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private ReentrantLock lock = new ReentrantLock();
//    private AtomicInteger maxBatch = new AtomicInteger(0);
//    private AtomicBoolean flag = new AtomicBoolean(false);
//
//    private AtomicInteger sumSize = new AtomicInteger(0);
//    private ConcurrentHashMap sqlMap;
//    private BlockingQueue receiver ;
//
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_REAL)
//    @RabbitHandler
//    public void dayDataProcess(List<DayVo> DayVo, Channel channel, Message message) throws IOException {
//        try {
//            if (DayVo.size() > 0) {
//                calPackage(DayVo, channel, message);
//            }
//        } catch (Exception e) {
//            ExceptionUtil.throwException(ReturnError.SYSTEM_ERROR);
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//        }
//    }
//
//    /*channel.basicQos(1);
//       告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    代表投递的标识符，唯一标识了当前信道上的投递，通过 deliveryTag ，消费者就可以告诉 RabbitMQ 确认收到了当前消息，见下面的方法
//             message.getMessageProperties().getDeliveryTag()
//            代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
//            channel.basicNack(deliveryTag, false, false);
//             代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
//             channel.basicReject(deliveryTag,false)*/
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_REAL)
//    @RabbitHandler   //可以接收到对象
//    public void dayDataProcessTwo(List<DayVo> DayVo, Channel channel, Message message) throws IOException {
//        try {
//            if (DayVo.size() > 0) {
//                calPackage(DayVo, channel, message);
//            }
//        } catch (Exception e) {
//            ExceptionUtil.throwException(ReturnError.SYSTEM_ERROR);
//        }
//    }
//
//    /**
//     * 判断是否丢包记录日志
//     */
//    private void calPackage(List<DayVo> dayVoList, Channel channel, Message message) {
//        lock.lock();
//        try {
//            DayVo DayVo = dayVoList.get(0);
//            if (flag.compareAndSet(false, true)) {
//                receiver= new LinkedBlockingQueue();
//                sqlMap=new ConcurrentHashMap();
//                maxBatch.set(DayVo.getMaxBatch());
//                sumSize.set(DayVo.getSumSize());
//                multiProcess();
//                logger.info("receive first packages from day_queue and start put message into queue!");
//            }
//            int currentsize = DayVo.getCurrentSize();
//            int currentbatch = DayVo.getCurrentBatch();
//            int stastus = DayVo.getStatus();
//            if (stastus == 1) {
//                if (sumSize.get() == currentsize && maxBatch.get() == currentbatch) {
//                    flag.compareAndSet(true, false);
//                    logger.info("day消息成功消费完成无丢包！");
//                }
//            }
//            receiver.add(dayVoList);
//            //handleMessage
//            logger.info("day_queue消费者获取day数据...总包数:{},当前包数:{},总条数:{},条数;{},状态:{}", maxBatch.get(),
//                    currentbatch, sumSize.get(), currentsize, DayVo.getStatus());
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            ExceptionUtil.throwException(ReturnError.SYSTEM_ERROR);
//            try {
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    /**
//     * 触发一次消费任务
//     */
//    private void multiProcess() {
//        //获取水位配置表
//        Map<String, Object> waterLevelMap = Optional.of(waterLevelMapper.fetchAll())
//                .get()
//                .stream()
//                .collect(Collectors.toMap(WaterLevelEntity::getSensorCode, a -> a));
//        //获取潮位配置表
//        Map<String, Object> tideLevelMap = Optional.of(tideLevelMapper.fetchAll())
//                .get()
//                .stream()
//                .collect(Collectors.toMap(TideLevelEntity::getSensorCode, b -> b));
//        //获取雨量配置表
//        Map<String, Object> rainfallMap = Optional.of(rainFallMapper.fetchAll())
//                .get()
//                .stream()
//                .collect(Collectors.toMap(RainfallEntity::getSensorCode, a -> a));
//        Map<String, Map<String, Object>> configMap = Maps.newHashMap();
//        configMap.put(SystemConstant.FLAGW, waterLevelMap);
//        configMap.put(SystemConstant.FLAGT, tideLevelMap);
//        configMap.put(SystemConstant.FLAGR, rainfallMap);
//        int corNum = Runtime.getRuntime().availableProcessors() >> 2;
//        //没有加拒绝策略
//        ThreadPoolExecutor worker = new ThreadPoolExecutor(corNum, corNum * 2, 120 * 1000,
//                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(corNum * 3));
//        Runnable fetchTask = () -> {
//            List<DayVo> dayVoList = (List) receiver.poll();
//            if (dayVoList.size() > 0) {
//                MessageProcessInterceptor.doInterceptor(dayVoList,configMap);
//            }
//        };
//        while (true) {
//            if (worker.getQueue().size() < corNum * 2) {
//                worker.execute(fetchTask);
//            }
//            if (receiver.isEmpty()) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (receiver.isEmpty()) {
//                    worker.shutdown();
//                    while (true) {
//                        if (worker.isTerminated()) {
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//
//
