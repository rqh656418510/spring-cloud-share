package com.clay.rabbitmq.consumer;

import com.clay.rabbitmq.config.QueueConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class DelayedQueueConsumer {

    @RabbitListener(queues = QueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayedQueue(Message message, Channel channel) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("当前时间: {}, 接收到延迟队列信息: {}", new Date(), msg);
    }

}
