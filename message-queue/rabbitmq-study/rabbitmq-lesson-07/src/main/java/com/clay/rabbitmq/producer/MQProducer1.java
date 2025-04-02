package com.clay.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MQProducer1 {

    /**
     * 单个确认发布
     */
    public static void publishMessageIndividually() throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.2.127");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        try (
            // 创建连接
            Connection connection = factory.newConnection();
            // 创建信道
            Channel channel = connection.createChannel()
        ) {
            String queueName = UUID.randomUUID().toString();

            // 声明队列
            channel.queueDeclare(queueName, false, false, false, null);

            // 开启发布确认
            channel.confirmSelect();

            int total = 1000;
            long begin = System.currentTimeMillis();
            for (int i = 0; i < total; i++) {
                // 发布消息
                String message = i + "";
                channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));

                // 单个确认发布，当服务端返回 false 或超时时间内未返回，生产者可以重发消息
                boolean flag = channel.waitForConfirms();
                if (flag) {
                    System.out.println("消息发送成功");
                } else {
                    System.out.println("消息发送失败");
                }
            }

            long end = System.currentTimeMillis();
            System.out.println("发布" + total + "个单独确认消息，耗时" + (end - begin) + "ms");
        }
    }

}