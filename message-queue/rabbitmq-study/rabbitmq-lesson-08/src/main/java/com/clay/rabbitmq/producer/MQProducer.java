package com.clay.rabbitmq.producer;

import com.clay.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MQProducer {

    // 交换机名称
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        // 创建信道
        Channel channel = RabbitMQUtils.createChannel();

        // 声明交换机
        // 参数说明：
        // exchange – 交换机的名称
        // type – 交换机类型
        // durable – 如果需要声明一个持久交换机，则为 true（交换机将在服务器重启后继续存在）
        // autoDelete – 如果需要声明 autoDelete 交换机，则为 true（当最后一个绑定队列解除绑定后，自动删除该交换机）
        // arguments – 交换的其他属性（构造参数）
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, false, false, null);

        // 从控制台获取需要发送的消息
        Scanner scanner = new Scanner(System.in);
        // 循环发送消息
        while (scanner.hasNext()) {
            // 消息内容
            String message = scanner.nextLine();
            System.out.println("发送消息：" + message);

            // 发送消息
            // 参数说明：
            // exchange – 要将消息发布到的交换机，空字符串表示默认交换机
            // routingKey – 路由 Key
            // props – 消息的其他属性，比如：使用 MessageProperties.PERSISTENT_TEXT_PLAIN 属性确保消息持久化，配合持久化队列可避免服务器重启导致消息丢失
            // body – 消息内容
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
        }

        // 关闭信道
        channel.close();
    }

}