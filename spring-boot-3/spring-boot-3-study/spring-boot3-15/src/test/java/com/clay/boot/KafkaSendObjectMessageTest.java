package com.clay.boot;

import com.clay.boot.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class KafkaSendObjectMessageTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送对象消息
     */
    @Test
    public void sendObjectMessage() {
        Person person = new Person(1L, "Jim", 18);
        CompletableFuture future = kafkaTemplate.send("news", "person", person);
        future.join();
        System.out.println("success to send message");
    }

}
