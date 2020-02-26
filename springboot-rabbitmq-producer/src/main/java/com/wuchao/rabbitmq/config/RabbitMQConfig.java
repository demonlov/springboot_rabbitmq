package com.wuchao.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //交换机名称
    public static final String ITEM_TOP_EXCHANGE = "item_top_exchange";
    //队列名称
    public static final String ITEM_QUEUE = "ITEM_QUEUE";
    public static final String ITEM_QUEUE1 = "ITEM_QUEUE1";

    //声明交换机
    @Bean("itemTopExchange")
    public Exchange topiceExchange() {
        return ExchangeBuilder.topicExchange(ITEM_TOP_EXCHANGE).durable(true).build();

    }

    //声明队列
    @Bean("itemQueue")
    public Queue itemQueue() {
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    //声明队列
    @Bean("itemQueue1")
    public Queue itemQueue1() {
        return QueueBuilder.durable(ITEM_QUEUE1).build();
    }
    //绑定队列和交换机
    @Bean
    public Binding itemQueueExchange(@Qualifier("itemTopExchange") Exchange exchange,
                                     @Qualifier("itemQueue") Queue queue
    ) {
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();

    }

    //绑定队列和交换机
    @Bean
    public Binding QueueExchange(@Qualifier("itemTopExchange") Exchange exchange,
                                     @Qualifier("itemQueue1") Queue queue
    ) {
        return BindingBuilder.bind(queue).to(exchange).with("item.insert").noargs();

    }
}

