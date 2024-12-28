package com.clay.kafka.producer;

import com.clay.kafka.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

/**
 * 消息生产者
 */
@Component
public class MessageProducer {

    private final BlockingQueue<Person> messageQueue = new LinkedBlockingQueue<>(1000);

    /**
     * 发送消息（基于函数式编程定义 Supplier）
     * <p> 对应 YML 配置文件中的 sendMsg-out-0
     * <p> 对应 YML 配置文件中的 spring.cloud.function.definition = sendMsg
     */
    @Bean
    public Supplier<Message<Person>> sendMsg() {
        return () -> {
            // 从队列中取出消息（非阻塞操作）
            Person person = messageQueue.poll();
            if (person != null) {
                // 构建消息
                return MessageBuilder.withPayload(person).build();
            }
            return null;
        };
    }

    /**
     * 提供外部方法发送消息，将 Person 对象加入队列
     */
    public void sendPersonMessage(Person person) {
        try {
            // 将消息放入到队列（阻塞操作）
            messageQueue.put(person);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to enqueue message", e);
        }
    }

}
