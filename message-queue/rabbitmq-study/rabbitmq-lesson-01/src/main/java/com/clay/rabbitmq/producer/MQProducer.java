package com.clay.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

public class MQProducer {

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
            // 声明队列
            // 参数说明：
            // queue – 队列的名称
            // durable – 如果需要声明一个持久队列，则为 true（队列将在服务器重启后继续存在）
            // exclusive – 如果需要声明一个独占队列（仅限于此连接使用，连接关闭后队列自动删除），则为 true。
            // autoDelete – 如果需要声明 autoDelete 队列，则为 true（服务器将在最后一个消费者断开连接以后，自动删除该队列）
            // arguments – 队列的其他属性（构造参数）
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // 发送消息
            // 参数说明：
            // exchange – 要将消息发布到的交换机，空字符串表示默认交换机
            // routingKey – 路由 Key
            // props – 消息的其他属性，比如：使用 MessageProperties.PERSISTENT_TEXT_PLAIN 属性确保消息持久化，配合持久化队列可避免服务器重启导致消息丢失
            // body – 消息内容
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes(StandardCharsets.UTF_8));
        }
    }

}