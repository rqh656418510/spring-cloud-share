package com.clay.rabbitmq.consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class MQConsumer {

    // 队列名称
    public static final String QUEUE_NAME = "test";

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.2.127");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        // 使用 try-with-resources 自动关闭连接和通道，确保资源释放
        try (
            // 创建连接
            Connection connection = factory.newConnection();
            // 创建信道
            Channel channel = connection.createChannel()
        ) {
            // 消费消息时的回调
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("Successed to consumer message : " + new String(message.getBody()));
            };

            // 取消消费消息时的回调
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println("Failed to consumer message : " + consumerTag);
            };

            // 消费消息
            // 参数说明：
            // queue – 队列的名称
            // autoAck – 如果需要服务器在消息投递后自动确认消息，则为 true；如果需要服务器手动确认消息，则为 false
            // deliverCallback – 消费消息时的回调
            // cancelCallback – 取消消费消息时的回调
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);

            // 让客户端一直运行
            Thread.sleep(Integer.MAX_VALUE);
        }
    }

}
