package com.wuchao.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wuchao.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式的交换机类型为direct
 */
public class Producer {
    //设置订阅使用的交换机
    static final String DIRECT_EXCHANGE = "direct_exchange";
    //队列名称
    static final String DIRECT_QUEUE_INSERT = "direct_queue_insert";
    static final String DIRECT_QUEUE_UPDATE = "direct_queue_update";

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
        channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);


        //声明队列
        /**
         * 参数1  队列名称
         * 参数二  是否持久化队列  消息会持久化在服务器上
         * 参数3  是否独占本链接
         * 参数4 是否在不适用的时候队列自动删除
         * 参数5 其他参数
         */
        channel.queueDeclare(DIRECT_QUEUE_INSERT, true, false, false, null);
        channel.queueDeclare(DIRECT_QUEUE_UPDATE, true, false, false, null);


        //将队列绑定到交换机
        channel.queueBind(DIRECT_QUEUE_INSERT, DIRECT_EXCHANGE, "insert");
        channel.queueBind(DIRECT_QUEUE_UPDATE, DIRECT_EXCHANGE, "update");



            //发送消息
            String message = "新增了商品 路由模式 routingkey 为insert";
            /**
             * 参数1 交换机名称 如果没有则指定空字符串 表示使用默认的交换机
             * 参数2 路由key  简单模式可以使用队列名称
             * 参数3  消息其他属性
             * 参数4  消息内容
             */
            channel.basicPublish(DIRECT_EXCHANGE, "insert", null, message.getBytes());
            System.out.println("已发送消息" + message);


        //发送消息
         message = "新增了商品 路由模式 routingkey 为update";
        /**
         * 参数1 交换机名称 如果没有则指定空字符串 表示使用默认的交换机
         * 参数2 路由key  简单模式可以使用队列名称
         * 参数3  消息其他属性
         * 参数4  消息内容
         */
        channel.basicPublish(DIRECT_EXCHANGE, "update", null, message.getBytes());
        System.out.println("已发送消息" + message);


        //关闭链接
        channel.close();
        connection.close();
    }


}
