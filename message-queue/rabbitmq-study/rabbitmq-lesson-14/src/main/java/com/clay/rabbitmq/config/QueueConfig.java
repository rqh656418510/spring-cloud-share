package com.clay.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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

    // 死信交换机的名称
    public static final String DEAD_LETTER_EXCHANGE = "Y";

    // 死信队列的名称
    public static final String DEAD_LETER_QUEUE = "QD";

    // 死信队列的路由键（绑定键）
    public static final String DEAD_LETTER_ROUTING_KEY = "YD";

    // 普通交换机的名称
    public static final String NORMAL_EXCHANGE = "X";

    // 普通队列的名称
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";

    // 普通队列的路由键（绑定键）
    public static final String ROUTING_KEY_QUEUE_A = "XA";
    public static final String ROUTING_KEY_QUEUE_B = "XB";

    /**
     * 声明死信交换机
     */
    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明死信队列
     */
    @Bean("queueD")
    public Queue queueD() {
        return new Queue(DEAD_LETER_QUEUE);
    }

    /**
     * 声明普通交换机
     */
    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(NORMAL_EXCHANGE);
    }

    /**
     * 声明普通队列 QA
     */
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> args = new HashMap<>(3);
        // 声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // 声明当前队列的死信队列的路由键（RoutingKey）
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        // 声明当前队列的 TTL（单位毫秒）
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }

    /**
     * 声明普通队列 QB
     */
    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> args = new HashMap<>(3);
        // 声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // 声明当前队列的死信队列的路由键（RoutingKey）
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        // 声明当前队列的 TTL（单位毫秒）
        args.put("x-message-ttl", 40000);
        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
    }

    // 绑定死信交换机和死信队列
    @Bean
    public Binding bindingDeadLetter(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange yExchange) {
        return BindingBuilder.bind(queueD).to(yExchange).with(DEAD_LETTER_ROUTING_KEY);
    }

    // 绑定普通交换机和普通队列 QA
    @Bean
    public Binding BindingQueueA(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueA).to(xExchange).with(ROUTING_KEY_QUEUE_A);
    }

    // 绑定普通交换机和普通队列 QB
    @Bean
    public Binding BindingQueueB(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueB).to(xExchange).with(ROUTING_KEY_QUEUE_B);
    }

}
