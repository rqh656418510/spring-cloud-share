package com.clay.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 队列的配置
 */
@Configuration
public class QueueConfig {

    // 自定义交换机的类型
    public static final String CUSTOM_EXCHANGE_TYPE = "x-delayed-message";

    // 延迟交换机的名称
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";

    // 延迟队列的名称
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";

    // 延迟队列的路由键（绑定键）
    public static final String DELAYED_QUEUE_ROUTING_KEY = "delayed.routing.key";

    // 声明延迟交换机
    @Bean("delayedExchange")
    public CustomExchange delayedExchange() {
        Map<String, Object> arguments = new HashMap<>(1);
        // 当消息的延迟时间到达之后，使用哪种类型的交换机逻辑来处理消息路由，例如 direct、fanout、topic、headers
        arguments.put("x-delayed-type", "direct");

        // 自定义交换机
        // 参数说明:
        // name       交换机的名称，用于在 RabbitMQ 中标识该交换机
        // type       交换机的类型，例如 direct、fanout、topic、headers、x-delayed-message
        // durable    是否持久化，如果为 true，则在 RabbitMQ 服务重启后该交换机仍然存在
        // autoDelete 是否自动删除，如果为 true，则在没有队列绑定到该交换机时会被自动删除
        // arguments  额外的配置参数
        return new CustomExchange(DELAYED_EXCHANGE_NAME, CUSTOM_EXCHANGE_TYPE, true, false, arguments);
    }

    // 声明延迟队列
    @Bean("delayedQueue")
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    // 绑定延迟交换机和延迟队列
    @Bean
    public Binding delayedBinding(@Qualifier("delayedQueue") Queue delayedQueue, @Qualifier("delayedExchange") CustomExchange delayedExchange) {
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_QUEUE_ROUTING_KEY).noargs();
    }

}
