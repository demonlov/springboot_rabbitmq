package com.wuchao.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wuchao.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 原生
 * 简单模式 发送消息
 */
public class Producer {
    static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        Channel channel = connection.createChannel();

        //声明队列
        /**
         * 参数1  队列名称
         * 参数二  是否持久化队列  消息会持久化在服务器上
         * 参数3  是否独占本链接
         * 参数4 是否在不适用的时候队列自动删除
         * 参数5 其他参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //发送消息
        String message = "你好小兔子";
        /**
         * 参数1 交换机名称 如果没有则指定空字符串 表示使用默认的交换机
         * 参数2 路由key  简单模式可以使用队列名称
         * 参数3  消息其他属性
         * 参数4  消息内容
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("已发送消息" + message);
        //关闭链接
        channel.close();
        connection.close();
    }


}
