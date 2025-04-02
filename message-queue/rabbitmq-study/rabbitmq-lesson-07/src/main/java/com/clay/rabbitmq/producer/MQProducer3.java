package com.clay.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MQProducer3 {

    /**
     * 异步确认发布
     */
    public static void publishMessageAsync() throws Exception {
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

            /**
             * 线程安全的一个有序哈希表，适用于高并发场景
             * 1. 轻松地将消息序列号与消息进行关联
             * 2. 轻松地批量删除条目，只要传入消息序列号
             * 3. 支持并发访问
             */
            ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

            /**
             * 收到确认消息的回调
             * 1. deliveryTag 表示消息序列号
             * 2. multiple = true，表示可以确认小于等于当前消息序列号的消息
             * 3. multiple = false，表示仅确认当前消息序列号的消息
             */
            ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
                if (multiple) {
                    // 返回的是小于等于当前消息序列号的未确认消息，是一个 Map
                    ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag, true);
                    // 清除该部分未确认的消息
                    confirmed.clear();
                } else {
                    // 只清除当前消息序列号的消息
                    outstandingConfirms.remove(deliveryTag);
                }
            };

            /**
             * 未收到确认消息的回调
             * 1. deliveryTag 表示消息序列号
             * 2. multiple = true，表示可以确认小于等于当前消息序列号的消息
             * 3. multiple = false，表示仅确认当前消息序列号的消息
             */
            ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
                // 异步处理未确认的消息，比如重新发送消息
                String message = outstandingConfirms.get(deliveryTag);
                System.out.println("发布的消息" + message + "未被确认，消息序列号" + deliveryTag);
            };

            /**
             * 添加一个异步确认的监听器
             * 1. 监听哪些消息发送成功
             * 2. 监听哪些消息发送失败
             */
            channel.addConfirmListener(ackCallback, nackCallback);

            int total = 1000;
            long begin = System.currentTimeMillis();
            for (int i = 0; i < total; i++) {
                // 发布消息（支持持久化）
                String message = "消息" + i;
                // channel.getNextPublishSeqNo() 获取下一个消息的消息序列号
                // 使用 Map 存储全部未确认的消息体，通过消息序列号与消息体进行关联
                outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
                channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            }

            long end = System.currentTimeMillis();
            System.out.println("发布" + total + "个异步确认消息，耗时" + (end - begin) + "ms");
        }
    }

    public static void main(String[] args) throws Exception {
        publishMessageAsync();
    }

}