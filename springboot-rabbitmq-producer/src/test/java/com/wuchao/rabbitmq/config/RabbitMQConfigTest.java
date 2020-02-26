package com.wuchao.rabbitmq.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQConfigTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
     public void test() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOP_EXCHANGE, "item.insert", "商品新增 routingKey 为 insert");
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOP_EXCHANGE, "item.update", "商品新增 routingKey 为 update");
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOP_EXCHANGE, "item.delete", "商品新增 routingKey 为 delete");


    }





}
