package com.clay.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    // 交换机的名称
    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";

    // 队列的名称
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";

    // 队列的路由键（绑定键）
    public static final String CONFIRM_QUEUE_ROUTING_KEY = "key1";

    // 声明交换机
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }

    // 声明队列
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    // 绑定交换机和队列
    @Bean
    public Binding bindingQueue(@Qualifier("confirmQueue") Queue confirmQueue, @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_QUEUE_ROUTING_KEY);
    }

}