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

    @Autowired
    private RabbitTemplate.ReturnsCallback returnsCallback;

    /**
     * 配置 RabbitTemplate Bean，用于自定义消息发送行为，比如发布确认处理
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        // 设置发布确认回调，用于确认消息是否成功到达交换机，需要在配置中启用 spring.rabbitmq.publisher-confirm-type=correlated
        rabbitTemplate.setConfirmCallback(confirmCallback);

        // 启用强制消息投递（mandatory=true），如果消息无法被路由到队列（即找不到匹配队列），则会触发 ReturnsCallback 回调
        rabbitTemplate.setMandatory(true);

        // 设置消息退回回调，用于处理消息未被队列接收的情况（比如路由键错误），需要在配置中启用 spring.rabbitmq.publisher-returns=true
        rabbitTemplate.setReturnsCallback(returnsCallback);

        return rabbitTemplate;
    }

}