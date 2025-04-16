package com.clay.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {

    // 交换机的名称
    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";

    // 队列的名称
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";

    // 队列的路由键（绑定键）
    public static final String CONFIRM_QUEUE_ROUTING_KEY = "key1";

    // 备份交换机的名称
    public static final String BACKUP_EXCHANGE_NAME = "backup.exchange";

    // 备份队列的名称
    public static final String BACKUP_QUEUE_NAME = "backup.queue";

    // 报警队列的名称
    public static final String WARNING_QUEUE_NAME = "warning.queue";

    // 声明交换机
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        Map<String, Object> arguments = new HashMap<>();
        // 设置当前交换机的备份交换机
        arguments.put("alternate-exchange", BACKUP_EXCHANGE_NAME);
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true).withArguments(arguments).build();
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

    // 声明备份交换机
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    // 声明备份队列
    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    // 声明报警队列
    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    // 绑定备份交换机和备份队列
    public Binding bindingBackupQueue(@Qualifier("backupQueue") Queue backupQueue, @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    // 绑定备份交换机和报警队列
    public Binding bindingWarningQueue(@Qualifier("warningQueue") Queue warningQueue, @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }

}
