package com.clay.rabbitmq.consumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MQConsumer {

    // 交换机的名称
    public static final String EXCHANGE_NAME = "priority.exchange";

    // 队列的名称
    public static final String QUEUE_NAME = "priority.queue";

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.2.127");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        // 创建连接
        Connection connection = factory.newConnection();

        // 创建信道
        Channel channel = connection.createChannel();

        // 声明交换机
        // 参数说明：
        // exchange – 交换机的名称
        // type – 交换机类型
        // durable – 如果需要声明一个持久交换机，则为 true（交换机将在服务器重启后继续存在）
        // autoDelete – 如果需要声明 autoDelete 交换机，则为 true（当最后一个绑定队列解除绑定后，自动删除该交换机）
        // arguments – 交换的其他属性（构造参数）
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, null);

        // 设置队列属性
        Map<String, Object> queueArguments = new HashMap<>();
        // 队列的最大优先级，范围在 0 ~ 255 之间的整数
        queueArguments.put("x-max-priority", 10);

        // 声明队列
        // 参数说明：
        // queue – 队列的名称
        // durable – 如果需要声明一个持久队列，则为 true（队列将在服务器重启后继续存在）
        // exclusive – 如果需要声明一个独占队列（仅限于此连接使用，连接关闭后队列自动删除），则为 true。
        // autoDelete – 如果需要声明 autoDelete 队列，则为 true（服务器将在最后一个消费者断开连接以后，自动删除该队列）
        // arguments – 队列的其他属性（构造参数）
        // 特别注意：如果确定队列已存在，消费者可以不声明队列。但是，强烈建议无论生产者还是消费者，都应该声明队列，确保参数可控
        channel.queueDeclare(QUEUE_NAME, true, false, false, queueArguments);

        // 绑定交换机和队列
        // 参数说明：
        // queue – 队列的名称
        // exchange – 交换机的名称
        // routingKey – 用于绑定的 RoutingKey（如果不使用 Routingkey，可填空字符串）
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        // 消费消息时的回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("Successed to consume message : " + msg);

            // 手动确认消息
            // 参数说明：
            // deliveryTag – 消息的标记
            // multiple – true 表示确认所有消息，包括提供的送达标签为止的所有消息；false 仅确认提供的投放标记
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        // 取消消费时的回调（比如，在消费的时候队列已被删除掉）
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("Failed to consume message : " + consumerTag);
        };

        // 消费者延后启动（让所有消息发送完毕再消费）
        try {
            System.out.println("消费者等待消息发送完毕...");
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("消费者开始消费消息...");

        // 关闭自动确认机制
        boolean autoAck = false;

        // 消费消息
        // 参数说明：
        // queue – 队列的名称
        // autoAck – 如果需要服务器在消息投递后自动确认消息，则为 true；如果需要客户端手动确认消息，则为 false
        // deliverCallback – 消费消息时的回调
        // cancelCallback – 取消消费时的回调
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, cancelCallback);

        // 让消费者持续运行
        System.out.println("按回车键退出程序：");
        new Scanner(System.in).nextLine();

        // 关闭信道
        channel.close();
        connection.close();
    }

}
