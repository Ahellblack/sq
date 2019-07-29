package com.siti.wisdomhydrologic.rabbitmq;

import com.rabbitmq.client.Channel;
import com.siti.wisdomhydrologic.config.RabbitMQConfig;
import com.siti.wisdomhydrologic.datepull.service.impl.DayDataServiceImpl;
import com.siti.wisdomhydrologic.datepull.vo.DayVo;
import com.siti.wisdomhydrologic.util.ExceptionUtil;
import com.siti.wisdomhydrologic.util.enumbean.ReturnError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


@Component
public class DayDataConsumerToMysql {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ReentrantLock lock = new ReentrantLock();

    private AtomicInteger maxBatch = new AtomicInteger(0);

    private AtomicBoolean flag = new AtomicBoolean(false);

    private AtomicInteger sumSize = new AtomicInteger(0);
    @Resource
    private DayDataServiceImpl dayDataService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DAY)
    @RabbitHandler
    public void dayDataProcess(List<DayVo> DayVo, Channel channel, Message message) throws IOException {
        try {
            if (DayVo.size() > 1) {
                //消费完成后直接添加数据
                int i = insertDayData(DayVo);
                logger.info("day数据插入本地库{}条", i);
                calPackage(DayVo.get(0), channel, message);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            logger.error(e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    /*
    /channel.basicQos(1);
        //   告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        代表投递的标识符，唯一标识了当前信道上的投递，通过 deliveryTag ，消费者就可以告诉 RabbitMQ 确认收到了当前消息，见下面的方法
                 message.getMessageProperties().getDeliveryTag()
                代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
                channel.basicNack(deliveryTag, false, false);
                 代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
                 channel.basicReject(deliveryTag,false)
    */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_DAY)
    @RabbitHandler   //可以接收到对象
    public void dayDataProcessTwo(List<DayVo> DayVo, Channel channel, Message message) throws IOException {
        try {
            if (DayVo.size() > 1) {
                //消费完成后直接添加数据
                int i = insertDayData(DayVo);
                logger.info("day数据插入本地库{}条", i);
                calPackage(DayVo.get(0), channel, message);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            //ExceptionUtil.throwException(ReturnError.SYSTEM_ERROR);
            logger.error(e.getMessage());

        }
    }


    private void calPackage(DayVo DayVo, Channel channel, Message message) {
        lock.lock();
        try {
            if (flag.compareAndSet(false, true)) {
                maxBatch.set(DayVo.getMaxBatch());
                sumSize.set(DayVo.getSumSize());
                logger.info("day_queue first packages...");
            }
            int currentsize = DayVo.getCurrentSize();
            int currentbatch = DayVo.getCurrentBatch();
            int stastus = DayVo.getStatus();
            if (stastus == 1) {
                if (sumSize.get() == currentsize && maxBatch.get() == currentbatch) {
                    logger.info("day_queue last package...");
                    flag.compareAndSet(true, false);
                    logger.info("day消息成功消费完成无丢包！");
                }
            }

            logger.info("day_queue消费者获取day数据...总包数:{},当前包数:{},总条数:{},条数;{},状态:{}", maxBatch.get(), currentbatch, sumSize.get(), currentsize, DayVo.getStatus());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            logger.error(e.getMessage());

            //ExceptionUtil.throwException(ReturnError.SYSTEM_ERROR);
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public int insertDayData(List<DayVo> daylist) {

        return dayDataService.addDayData(daylist);
    }


}
