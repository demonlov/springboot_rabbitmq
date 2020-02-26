package com.wuchao.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {

        //创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机默认localhost
        connectionFactory.setHost("localhost");

        //链接端口 默认5672
        connectionFactory.setPort(5672);
        //虚拟主机 默认/
        connectionFactory.setVirtualHost("/wuchao");
        //用户名 默认guest
        connectionFactory.setUsername("wuchao");

        //密码  默认guest
        connectionFactory.setPassword("wuchao");

        //创建链接
        Connection connection = connectionFactory.newConnection();
        return connection;

    }



}
