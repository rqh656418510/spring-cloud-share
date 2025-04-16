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

    @GetMapping("/sendDelayMsg/{msg}/{delayTime}")
    public String sendDelayMsg(@PathVariable("msg") String message, @PathVariable("delayTime") Integer delayTime) {
        log.info("当前时间: {}, 发送一条时长 {} 毫秒的信息给延迟队列: {}", new Date(), delayTime, message);
        rabbitTemplate.convertAndSend(QueueConfig.DELAYED_EXCHANGE_NAME, QueueConfig.DELAYED_QUEUE_ROUTING_KEY, message, msg -> {
            // 指定消息的延迟时间（单位毫秒）
            msg.getMessageProperties().setDelay(delayTime);
            return msg;
        });
        return "success";
    }

}