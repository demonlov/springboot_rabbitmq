package com.wuchao.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {
    /**
     * 监听某个队列的消息
     *
     * @Param message 接收到的消息
     */

  /*  @RabbitListener(queues = "ITEM_QUEUE")
    public void myListener(String message) {
        System.out.println("消费方 接收到的消息是"+message);
    }*/


    @RabbitListener(queues = "ITEM_QUEUE1")
    public void myListener1(String message) {
        System.out.println("消费方 接收到的消息是"+message);
    }
}
