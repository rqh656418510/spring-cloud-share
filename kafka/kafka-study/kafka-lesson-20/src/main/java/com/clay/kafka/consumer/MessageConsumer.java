package com.clay.kafka.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 消息消费者
 */
@Component
public class MessageConsumer {

    /**
     * 消费消息（基于函数式编程定义 Consumer）
     * <p> 对应 YML 配置文件中的 receiveMsg-in-0
     */
    @Bean
    public Consumer<Message<String>> receiveMsg() {
        return message -> {
            System.out.println("Receive Msg: " + message.getPayload());
        };
    }

}
