package com.clay.rabbitmq.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 消息退回的回调类
 */
@Slf4j
@Component
public class CustomReturnsCallback implements RabbitTemplate.ReturnsCallback {

    /**
     * 当消息成功发送到交换机，但因为路由失败（比如没有匹配的队列）而被退回时，会触发该回调方法
     *
     * @param returned 包含被退回消息的详细信息，如消息内容、路由键、交换机、退回原因等
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        String cause = returned.getReplyText();
        String exchagne = returned.getExchange();
        String routingKey = returned.getRoutingKey();
        String message = new String(returned.getMessage().getBody(), StandardCharsets.UTF_8);
        log.error("消息 {} 被交换机 {} 退回, 路由键: {}, 退回原因: {}", message, exchagne, routingKey, cause);
    }

}
