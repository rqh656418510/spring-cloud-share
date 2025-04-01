package com.clay.rabbitmq.consumer;

import com.clay.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MQConsumer02 {

    // 队列名称
    public static final String QUEUE_NAME = "test";

    public static void main(String[] args) throws Exception {
        // 创建信道
        Channel channel = RabbitMQUtils.createChannel();

        // 声明队列
        // 参数说明：
        // queue – 队列的名称
        // durable – 如果需要声明一个持久队列，则为 true（队列将在服务器重启后继续存在）
        // exclusive – 如果需要声明一个独占队列（仅限于此连接使用，连接关闭后队列自动删除），则为 true。
        // autoDelete – 如果需要声明 autoDelete 队列，则为 true（服务器将在最后一个消费者断开连接以后，自动删除该队列）
        // arguments – 队列的其他属性（构造参数）
        // 特别注意：如果确定队列已存在，消费者可以不声明队列。但是，强烈建议无论生产者还是消费者，都应该声明队列，确保参数可控
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 消费消息时的回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);

            // 模拟消息的耗时处理
            try {
                Thread.sleep(8000);
                System.out.println("Successed to consume message : " + msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

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

        System.out.println("消费者二等待接收消息，处理消息较慢...");

        // 设置预取计数值（当值为 1，其运行效果就是不公平分发，即能者多劳）
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

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

        // 关闭连接
        channel.close();
    }

}
