package com.wuchao.work;

import com.rabbitmq.client.*;
import com.wuchao.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者接收消息
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {

        //创建链接工厂
        Connection connection = ConnectionUtil.getConnection();


        //创建频道
        final Channel channel = connection.createChannel();

        channel.queueDeclare(Producer.QUEUE_NAME, true, false, false, null);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

//                try {
                    //路由key
                    System.out.println("路由key为" + envelope.getRoutingKey());
                    //交换机
                    System.out.println("交换机为" + envelope.getExchange());
                    //消息id
                    System.out.println("消息id为" + envelope.getDeliveryTag());
                    //收到的消息
                    System.out.println("消费者2接收到的消息为" + new String(body, "utf-8"));
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }


            }
        };
        //监听消息
        /**
         * 参数1 队列名称
         * 参数2 是否自动确认 是指true 为表示消息接收到自动向mq回复接收到了
         * mq接收到回复就删除消息  设置为false 则需要手动确认
         * 参数3 消息收到后回调
         *
         */
        channel.basicConsume(Producer.QUEUE_NAME, true, consumer);
        //不关闭资源需要一直监听



    }
}
