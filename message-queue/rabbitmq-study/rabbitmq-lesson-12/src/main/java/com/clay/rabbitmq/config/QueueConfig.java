package com.clay.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    // 交换机的名称
    public static final String ORDER_EXCHANGE_NAME = "order.exchange";

    // 队列的名称
    public static final String ORDER_QUEUE_NAME = "order.queue";

    // 队列的路由键（绑定键）
    public static final String ORDER_QUEUE_ROUTING_KEY = "order";

    // 声明交换机
    @Bean("orderExchange")
    public TopicExchange orderExchange() {
        // 定义主题交换机
        return ExchangeBuilder.topicExchange(ORDER_EXCHANGE_NAME).build();
    }

    // 声明队列
    @Bean("orderQueue")
    public Queue orderQueue() {
        // 定义持久化队列
        return QueueBuilder.durable(ORDER_QUEUE_NAME).build();
    }

    // 绑定交换机和队列
    @Bean
    public Binding bindingQueue(@Qualifier("orderQueue") Queue orderQueue, @Qualifier("orderExchange") TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(ORDER_QUEUE_ROUTING_KEY);
    }

}