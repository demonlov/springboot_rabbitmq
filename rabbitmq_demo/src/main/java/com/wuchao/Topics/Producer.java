package com.wuchao.Topics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wuchao.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 通配符topic的交换机类型为topic
 */
public class Producer {
    //设置订阅使用的交换机
    static final String TOPIC_EXCHANGE = "topic_exchange";
    //队列名称
    static final String TOPIC_QUEUE1 = "topic_queue1";
    static final String TOPIC_QUEUE2 = "topic_queue2";

    public static void main(String[] args) throws IOException, TimeoutException {
//创建链接
        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        Channel channel = connection.createChannel();


        /**
         * 声明交换机
         * 参数 1 交换机名称
         * 参数2 交换机类型 fanout topic direct header
         */
        channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);



            //发送消息
            String message = "新增了商品 路由模式 top 为item.insert";
            /**
             * 参数1 交换机名称 如果没有则指定空字符串 表示使用默认的交换机
             * 参数2 路由key  简单模式可以使用队列名称
             * 参数3  消息其他属性
             * 参数4  消息内容
             */
            channel.basicPublish(TOPIC_EXCHANGE, "item.insert", null, message.getBytes());
            System.out.println("已发送消息" + message);


        //发送消息
         message = "新增了商品 路由模式 top为update";
        /**
         * 参数1 交换机名称 如果没有则指定空字符串 表示使用默认的交换机
         * 参数2 路由key  简单模式可以使用队列名称
         * 参数3  消息其他属性
         * 参数4  消息内容
         */
        channel.basicPublish(TOPIC_EXCHANGE, "item.update", null, message.getBytes());
        System.out.println("已发送消息" + message);


        //发送消息
        message = "新增了商品 路由模式 top为delete";
        /**
         * 参数1 交换机名称 如果没有则指定空字符串 表示使用默认的交换机
         * 参数2 路由key  简单模式可以使用队列名称
         * 参数3  消息其他属性
         * 参数4  消息内容
         */
        channel.basicPublish(TOPIC_EXCHANGE, "item.delete", null, message.getBytes());
        System.out.println("已发送消息" + message);




        //关闭链接
        channel.close();
        connection.close();
    }


}
