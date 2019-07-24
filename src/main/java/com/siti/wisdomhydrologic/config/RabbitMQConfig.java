package com.siti.wisdomhydrologic.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

/**
 * Created by DC on 2019/6/12.
 *
 * @data ${DATA}-14:58
 */
@Configuration
public class RabbitMQConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    public static final String WH_EXCHANGE = "exchange_wh";

    public static final String QUEUE_REAL= "QUEUE_REAL";

    public static final String QUEUE_DAY= "QUEUE_DAY";

    public static final String QUEUE_HOUR= "QUEUE_HOUR";

    public static final String QUEUE_TSDB= "QUEUE_TSDB";

    public static final String ROUTINGKEY_REAL = "routingKey_real";

    public static final String ROUTINGKEY_HOUR = "routingKey_hour";

    public static final String ROUTINGKEY_TSDB = "routingKey_tsdb";

    public static final String ROUTINGKEY_DAY = "routingKey_day";
    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public TopicExchange defaultExchange() {
        return new TopicExchange(WH_EXCHANGE,true,false);
    }

    /**
     * 获取队列month
     * @return
     */
    @Bean
    public Queue queuReal() {
        return new Queue(QUEUE_REAL, true); //队列持久
    }

    @Bean
    public Queue queueTSDB() {
        return new Queue(QUEUE_TSDB, true); //队列持久
    }

    @Bean
    public Queue queueHour() {
        return new Queue(QUEUE_HOUR, true); //队列持久
    }
    /**
     * 获取队列day
     * @return
     */
    @Bean
    public Queue queueDay()
    {
        return new Queue(QUEUE_DAY, true); //队列持久
    }

    /**
     * 将实时队列绑定到topic交换机上
     * @return
     */
    @Bean
    public Binding bindingReal() {
        //binding_key为topic.#,模糊匹配
        return BindingBuilder.bind(queuReal()).to(defaultExchange()).with(RabbitMQConfig.ROUTINGKEY_REAL);
    }

    /**
     * 将日队列绑定到topic交换机
     * @return
     */
    @Bean
    public Binding bindingDay() {
        return BindingBuilder.bind(queueDay()).to(defaultExchange()).with(RabbitMQConfig.ROUTINGKEY_DAY);
    }

    /**
     * 将TSDB队列绑定到topic交换机
     * @return
     */
    @Bean
    public Binding bindingTSDB() {
        return BindingBuilder.bind(queueTSDB()).to(defaultExchange()).with(RabbitMQConfig.ROUTINGKEY_TSDB);
    }

    /**
     * 将hour队列绑定到topic交换机
     * @return
     */
    @Bean
    public Binding bindingHour() {
        return BindingBuilder.bind(queueHour()).to(defaultExchange()).with(RabbitMQConfig.ROUTINGKEY_HOUR);
    }


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

/*    @Bean(name = "manualAckContainerFactory")
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> ManualAckContainerFactory(ConnectionFactory
                                                                                                            connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //factory.setMessageConverter(jsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(3);
        return factory;
    }*/
}
