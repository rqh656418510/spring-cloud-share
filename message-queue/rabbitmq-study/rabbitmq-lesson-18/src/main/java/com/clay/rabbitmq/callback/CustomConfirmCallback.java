package com.clay.rabbitmq.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 发送确认的回调类
 */
@Slf4j
@Component
public class CustomConfirmCallback implements RabbitTemplate.ConfirmCallback {

    /**
     * 消息发送到交换机后的回调函数，用于确认消息是否成功到达交换机
     * <p> 特别注意：如果消息成功发送到交换机，但是交换机根据 RoutingKey 无法将消息路由到匹配的队列，消息会被丢弃，但 ack 参数的值仍然为 true
     *
     * @param correlationData 关联数据，用于唯一标识发送的消息，可以在发送消息时设置，用于跟踪消息的状态
     * @param ack             表示消息是否成功到达交换机。true 表示成功，false 表示失败
     * @param cause           如果 ack 为 false，该字段表示失败的原因；如果 ack 为 true，该字段通常为 null
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("ID 为 {} 的消息成功发送到交换机", msgId);
        } else {
            log.error("ID 为 {} 的消息发送到交换机失败，原因：{}", msgId, cause);
        }
    }

}
