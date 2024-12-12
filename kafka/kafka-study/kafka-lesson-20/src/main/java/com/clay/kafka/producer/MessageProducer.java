package com.clay.kafka.producer;

import com.clay.kafka.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

/**
 * 消息生产者
 */
@Component
public class MessageProducer {

    private BlockingQueue<Person> unbounded = new LinkedBlockingQueue<>();

    /**
     * 发送消息
     * <p> 对应 YML 配置文件中的 sendMsg-out-0
     */
    @Bean
    public Supplier<Person> sendMsg() {
        return () -> unbounded.poll();
    }

    /**
     * 发送消息
     */
    public void send(Person person) {
        unbounded.offer(person);
    }

}
