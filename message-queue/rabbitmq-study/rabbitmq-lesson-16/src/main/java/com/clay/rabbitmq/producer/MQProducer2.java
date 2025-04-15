package com.clay.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MQProducer2 {

    /**
     * 批量确认发布
     */
    public static void publishMessageBatch() throws Exception {
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

            // 声明队列（支持持久化）
            channel.queueDeclare(queueName, true, false, false, null);

            // 开启发布确认
            channel.confirmSelect();

            // 批量确认消息的数量
            int batchSize = 100;

            // 未确认消息的数量
            int outstandingMessageCount = 0;

            int total = 1000;
            long begin = System.currentTimeMillis();
            for (int i = 0; i < total; i++) {
                // 发布消息（支持持久化）
                String message = "消息" + i;
                channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));

                // 批量确认发布（比如每发送 100 条消息就批量确认一次）
                outstandingMessageCount++;
                if (outstandingMessageCount == batchSize) {
                    channel.waitForConfirms();
                    outstandingMessageCount = 0;
                }
            }

            // 为了确保没有剩余的未确认消息，再次确认发布
            if (outstandingMessageCount > 0) {
                channel.waitForConfirms();
            }

            long end = System.currentTimeMillis();
            System.out.println("发布" + total + "个批量确认消息，耗时" + (end - begin) + "ms");
        }
    }

    public static void main(String[] args) throws Exception {
        publishMessageBatch();
    }

}