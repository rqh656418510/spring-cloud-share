package com.clay.rabbitmq.producer;

import com.clay.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true, false, null);

        Map<String, String> routingKeyMap = new HashMap<>();
        routingKeyMap.put("quick.orange.rabbit", "被队列 Q1 和 队列 Q2 接收到");
        routingKeyMap.put("lazy.orange.elephant", "被队列 Q1 和 队列 Q2 接收到");
        routingKeyMap.put("quick.orange.fox", "被队列 Q1 接收到");
        routingKeyMap.put("lazy.brown.fox", "被队列 Q2 接收到");
        routingKeyMap.put("lazy.pink.rabbit", "虽然满足两个绑定，但只被队列 Q2 接收一次");
        routingKeyMap.put("quick.brown.fox", "不匹配任何绑定，不会被任何队列接收到，消息会被丢弃");
        routingKeyMap.put("quick.orange.male.rabbit", "包含四个单词，不匹配任何绑定，消息会被丢弃");
        routingKeyMap.put("lazy.orange.male.rabbit", "包含四个单词，被队列 Q2 接收到");

        // 发送消息
        for (Map.Entry<String, String> bindingKeyEntry : routingKeyMap.entrySet()) {
            String routingKey = bindingKeyEntry.getKey();
            String message = bindingKeyEntry.getValue();

            // 发送消息
            // 参数说明：
            // exchange – 要将消息发布到的交换机，空字符串表示默认交换机
            // routingKey – 路由 Key
            // props – 消息的其他属性，比如：使用 MessageProperties.PERSISTENT_TEXT_PLAIN 属性确保消息持久化，配合持久化队列可避免服务器重启导致消息丢失
            // body – 消息内容
            channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
        }

        // 关闭通道
        channel.close();

        // 关闭连接
        channel.getConnection().close();
    }

}