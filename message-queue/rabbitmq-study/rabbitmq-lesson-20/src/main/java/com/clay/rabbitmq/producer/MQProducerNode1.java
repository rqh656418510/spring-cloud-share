package com.clay.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * RabbitMQ 独立节点一的生产者
 */
public class MQProducerNode1 {

    // 交换机的名称
    public static final String EXCHANGE_NAME = "fed_exchange";

    // 队列的路由键（绑定键）
    public static final String QUEUE_ROUTING_KEY = "routekey";

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.2.148");
        factory.setPort(5673);
        factory.setUsername("admin");
        factory.setPassword("admin");

        // 使用 try-with-resources 自动关闭连接和通道，确保资源释放
        try (
            // 创建连接
            Connection connection = factory.newConnection();
            // 创建信道
            Channel channel = connection.createChannel()
        ) {
            // 声明交换机
            // 参数说明：
            // exchange – 交换机的名称
            // type – 交换机类型
            // durable – 如果需要声明一个持久交换机，则为 true（交换机将在服务器重启后继续存在）
            // autoDelete – 如果需要声明 autoDelete 交换机，则为 true（当最后一个绑定队列解除绑定后，自动删除该交换机）
            // arguments – 交换的其他属性（构造参数）
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

            // 发送消息
            // 参数说明：
            // exchange – 要将消息发布到的交换机，空字符串表示默认交换机
            // routingKey – 路由 Key
            // props – 消息的其他属性，比如：使用 MessageProperties.PERSISTENT_TEXT_PLAIN 属性确保消息持久化，配合持久化队列可避免服务器重启导致消息丢失
            // body – 消息内容
            channel.basicPublish(EXCHANGE_NAME, QUEUE_ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, "hello".getBytes(StandardCharsets.UTF_8));
        }
    }

}