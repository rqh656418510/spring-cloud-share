package com.clay.rabbitmq.consumer;

import com.clay.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MQConsumer01 {

    // 死信交换机的名称
    public static final String DEAD_EXCHANGE_NAME = "dead_exchange";

    // 死信队列的名称
    public static final String DEAD_QUEUE_NAME = "dead-queue";

    // 普通交换机的名称
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange";

    // 普通队列的名称
    public static final String NORMAL_QUEUE_NAME = "normal-queue";

    public static void main(String[] args) throws Exception {
        // 创建信道
        Channel channel = RabbitMQUtils.createChannel();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 声明死信交换机
        // 参数说明：
        // exchange – 交换机的名称
        // type – 交换机类型
        // durable – 如果需要声明一个持久交换机，则为 true（交换机将在服务器重启后继续存在）
        // autoDelete – 如果需要声明 autoDelete 交换机，则为 true（当最后一个绑定队列解除绑定后，自动删除该交换机）
        // arguments – 交换的其他属性（构造参数）
        channel.exchangeDeclare(DEAD_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

        // 声明死信队列
        // 参数说明：
        // queue – 队列的名称
        // durable – 如果需要声明一个持久队列，则为 true（队列将在服务器重启后继续存在）
        // exclusive – 如果需要声明一个独占队列（仅限于此连接使用，连接关闭后队列自动删除），则为 true。
        // autoDelete – 如果需要声明 autoDelete 队列，则为 true（服务器将在最后一个消费者断开连接以后，自动删除该队列）
        // arguments – 队列的其他属性（构造参数）
        // 特别注意：如果确定队列已存在，消费者可以不声明队列。但是，强烈建议无论生产者还是消费者，都应该声明队列，确保参数可控
        channel.queueDeclare(DEAD_QUEUE_NAME, true, false, false, null);

        // 绑定死信交换机和死信队列
        // 参数说明：
        // queue – 队列的名称
        // exchange – 交换机的名称
        // routingKey – 用于绑定的 RoutingKey
        channel.queueBind(DEAD_QUEUE_NAME, DEAD_EXCHANGE_NAME, "lisi");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 声明普通交换机
        // 参数说明：
        // exchange – 交换机的名称
        // type – 交换机类型
        // durable – 如果需要声明一个持久交换机，则为 true（交换机将在服务器重启后继续存在）
        // autoDelete – 如果需要声明 autoDelete 交换机，则为 true（当最后一个绑定队列解除绑定后，自动删除该交换机）
        // arguments – 交换的其他属性（构造参数）
        channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

        // 普通队列绑定死信队列的配置信息
        Map<String, Object> normalArguments = new HashMap<>();
        // 普通队列设置死信交换机，参数 key 是固定值
        normalArguments.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
        // 普通队列设置死信队列的 RoutingKey，参数 key 是固定值
        normalArguments.put("x-dead-letter-routing-key", "lisi");
        // 设置队列的最大长度
        // normalArguments.put("x-max-length", 3);

        // 声明普通队列
        // 参数说明：
        // queue – 队列的名称
        // durable – 如果需要声明一个持久队列，则为 true（队列将在服务器重启后继续存在）
        // exclusive – 如果需要声明一个独占队列（仅限于此连接使用，连接关闭后队列自动删除），则为 true。
        // autoDelete – 如果需要声明 autoDelete 队列，则为 true（服务器将在最后一个消费者断开连接以后，自动删除该队列）
        // arguments – 队列的其他属性（构造参数）
        // 特别注意：如果确定队列已存在，消费者可以不声明队列。但是，强烈建议无论生产者还是消费者，都应该声明队列，确保参数可控
        channel.queueDeclare(NORMAL_QUEUE_NAME, true, false, false, normalArguments);

        // 绑定普通交换机和普通队列
        // 参数说明：
        // queue – 队列的名称
        // exchange – 交换机的名称
        // routingKey – 用于绑定的 RoutingKey
        channel.queueBind(NORMAL_QUEUE_NAME, NORMAL_EXCHANGE_NAME, "zhangsan");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 消费消息时的回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);

            // 模拟消息的耗时处理
            try {
                Thread.sleep(100);
                System.out.println("Consume from normal queue, RoutingKey : " + message.getEnvelope().getRoutingKey() + ", Message : " + msg);
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

        System.out.println("消费者一等待接收消息...");

        // 关闭自动确认机制
        boolean autoAck = false;

        // 消费消息
        // 参数说明：
        // queue – 队列的名称
        // autoAck – 如果需要服务器在消息投递后自动确认消息，则为 true；如果需要客户端手动确认消息，则为 false
        // deliverCallback – 消费消息时的回调
        // cancelCallback – 取消消费时的回调
        channel.basicConsume(NORMAL_QUEUE_NAME, autoAck, deliverCallback, cancelCallback);

        // 让消费者持续运行
        System.out.println("按回车键退出程序：");
        new Scanner(System.in).nextLine();

        // 关闭信道
        channel.close();
    }

}
