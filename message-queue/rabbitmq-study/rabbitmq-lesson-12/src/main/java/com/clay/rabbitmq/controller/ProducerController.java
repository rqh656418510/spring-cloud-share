package com.clay.rabbitmq.controller;

import com.clay.rabbitmq.config.QueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
public class ProducerController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public String sendMsg(@PathVariable("message") String message) {
        log.info("当前时间: {}, 发送一条信息给队列: {}", new Date(), message);

        MessageProperties properties = new MessageProperties();
        // 设置消息持久化
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        // 设置消息 ID
        properties.setMessageId(UUID.randomUUID().toString());

        // 构建消息
        Message msg = new Message(message.getBytes(StandardCharsets.UTF_8), properties);

        // 发送消息（第一种写法）
        rabbitTemplate.convertAndSend(QueueConfig.ORDER_EXCHANGE_NAME, QueueConfig.ORDER_QUEUE_ROUTING_KEY, msg);

        return "success";
    }

    @GetMapping("/sendErrorExchangeMsg/{message}")
    public String sendErrorExchangeMsg(@PathVariable("message") String message) {
        log.info("当前时间: {}, 发送一条信息给队列: {}", new Date(), message);

        // 发送消息（第二种写法），特意指定错误的交换机，从而验证消息是否会被确认发布
        rabbitTemplate.convertAndSend("xxxxx", QueueConfig.ORDER_QUEUE_ROUTING_KEY, message, msg -> {
            // 设置消息持久化
            msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            // 设置消息 ID
            msg.getMessageProperties().setMessageId(UUID.randomUUID().toString());
            return msg;
        });

        return "success";
    }

    @GetMapping("/sendErrorRoutingKeyMsg/{message}")
    public String sendErrorRoutingKeyMsg(@PathVariable("message") String message) {
        log.info("当前时间: {}, 发送一条信息给队列: {}", new Date(), message);

        // 发送消息（第二种写法），特意指定错误的路由键，从而验证消息是否会被退回
        rabbitTemplate.convertAndSend(QueueConfig.ORDER_EXCHANGE_NAME, "xxx", message, msg -> {
            // 设置消息持久化
            msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            // 设置消息 ID
            msg.getMessageProperties().setMessageId(UUID.randomUUID().toString());
            return msg;
        });

        return "success";
    }

}