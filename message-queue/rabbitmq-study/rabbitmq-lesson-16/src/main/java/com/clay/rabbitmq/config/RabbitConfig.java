package com.clay.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    private RabbitTemplate.ConfirmCallback confirmCallback;

    /**
     * 配置 RabbitTemplate Bean，用于自定义消息发送行为，比如发布确认处理
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 设置发布确认回调，用于确认消息是否成功到达交换机，需要在配置中启用 spring.rabbitmq.publisher-confirm-type=correlated
        rabbitTemplate.setConfirmCallback(confirmCallback);
        return rabbitTemplate;
    }

}