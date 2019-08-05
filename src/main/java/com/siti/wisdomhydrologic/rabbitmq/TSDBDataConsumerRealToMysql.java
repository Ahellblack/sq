package com.siti.wisdomhydrologic.rabbitmq;

import com.rabbitmq.client.Channel;
import com.siti.wisdomhydrologic.config.RabbitMQConfig;
import com.siti.wisdomhydrologic.datepull.service.impl.DayDataServiceImpl;
import com.siti.wisdomhydrologic.datepull.service.impl.TSDBServiceImpl;
import com.siti.wisdomhydrologic.datepull.vo.TSDBVo;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DC on 2019/6/12.
 *
 * @data ${DATA}-15:23
 */
@Component
@Transactional
public class TSDBDataConsumerRealToMysql {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ReentrantLock lock = new ReentrantLock();

    private AtomicInteger maxBatch = new AtomicInteger(0);

    private AtomicBoolean flag = new AtomicBoolean(false);

    private AtomicInteger sumSize = new AtomicInteger(0);
    @Resource
    private DayDataServiceImpl dayDataService;
    @Resource
    private TSDBServiceImpl tsdbService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TSDB)
    @RabbitHandler
    public void TSDBDataProcess(List<TSDBVo> TSDBVo, Channel channel, Message message) throws IOException {

        try {
            if (TSDBVo.size() > 1) {
                //消费完成后直接添加数据
                calPackage(TSDBVo, channel, message);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /*//channel.basicQos(1);
    //   告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    代表投递的标识符，唯一标识了当前信道上的投递，通过 deliveryTag ，消费者就可以告诉 RabbitMQ 确认收到了当前消息，见下面的方法
             message.getMessageProperties().getDeliveryTag()
            代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
            channel.basicNack(deliveryTag, false, false);
             代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
             channel.basicReject(deliveryTag,false)*/
    @RabbitListener(queues = RabbitMQConfig.QUEUE_TSDB)
    @RabbitHandler   //可以接收到对象
    public void TSDBDataProcessTwo(List<TSDBVo> TSDBVo, Channel channel, Message message) throws IOException {

        try {
            if (TSDBVo.size() > 1) {
                //消费完成后直接添加数据
                calPackage(TSDBVo, channel, message);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 判断是否丢包记录日志
     *
     * @param TSDBList
     */
    private void calPackage(List<TSDBVo> TSDBList, Channel channel, Message message) {
        lock.lock();
        int i = insertTSDB(TSDBList);
        TSDBVo TSDBVo=TSDBList.get(0);
        logger.info("TSDB数据插入本地库{}条", i);
        try {
            if (flag.compareAndSet(false, true)) {
                maxBatch.set(TSDBVo.getMaxBatch());
                sumSize.set(TSDBVo.getSumSize());
                logger.info("TSDB_queue first packages...");
            }
            int currentsize = TSDBVo.getCurrentSize();
            int currentbatch = TSDBVo.getCurrentBatch();
            int stastus = TSDBVo.getStatus();
            if (stastus == 1) {
                if (sumSize.get() == currentsize && maxBatch.get() == currentbatch) {
                    logger.info("TSDB_queue last package...");
                    flag.compareAndSet(true, false);
                    logger.info("TSDB消息成功消费完成无丢包！");
                }
            }

            logger.info("history_tsdb_queue消费者获取tsdb数据...总包数:{},当前包数:{},总条数:{},条数;{},状态:{}", maxBatch.get(), currentbatch, sumSize.get(), currentsize, TSDBVo.getStatus());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            /*try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
        } finally {
            lock.unlock();
        }
    }

    public int insertTSDB(List<TSDBVo> tsdblist) {
        return tsdbService.insertTSDB(tsdblist);
    }


}
