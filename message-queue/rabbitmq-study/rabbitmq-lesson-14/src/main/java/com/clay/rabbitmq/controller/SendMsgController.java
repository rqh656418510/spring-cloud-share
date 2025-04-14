package com.clay.rabbitmq.controller;

import com.clay.rabbitmq.config.QueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/delay")
public class SendMsgController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg") String message) {
        log.info("当前时间: {}, 发送一条信息给两个 TTL 队列: {}", new Date(), message);
        rabbitTemplate.convertAndSend(QueueConfig.NORMAL_EXCHANGE, QueueConfig.ROUTING_KEY_QUEUE_A, "消息来自 TTL 为 10s 的队列: " + message);
        rabbitTemplate.convertAndSend(QueueConfig.NORMAL_EXCHANGE, QueueConfig.ROUTING_KEY_QUEUE_B, "消息来自 TTL 为 40s 的队列: " + message);
        return "success";
    }

}