package com.clay.kafka.consumer;

import com.clay.kafka.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 消息消费者
 */
@Component
public class MessageConsumer {

    /**
     * 消费消息
     * <p> 对应 YML 配置文件中的 receiveMsg-in-0
     */
    @Bean
    public Consumer<Person> receiveMsg() {
        return person -> {
            if (person != null) {
                System.out.println("name: " + person.getName() + ", age: " + person.getAge());
            }
        };
    }

}
