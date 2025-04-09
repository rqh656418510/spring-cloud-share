package com.clay.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {

    public static ConnectionFactory connectionFactory;

    static {
        // 创建连接工厂
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.2.127");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
    }

    /**
     * 创建信道
     */
    public static Channel createChannel() throws Exception {
        // 创建连接
        Connection connection = connectionFactory.newConnection();

        // 创建信道
        Channel channel = connection.createChannel();

        return channel;
    }

}
