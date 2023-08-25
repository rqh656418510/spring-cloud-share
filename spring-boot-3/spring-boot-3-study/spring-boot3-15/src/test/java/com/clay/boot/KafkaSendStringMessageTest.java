package com.clay.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class KafkaSendStringMessageTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送普通消息
     */
    @Test
    public void sendSimpleMessage() {
        CompletableFuture[] futures = new CompletableFuture[5];
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 5; i++) {
            CompletableFuture future = kafkaTemplate.send("news", "hello", "world");
            futures[i] = future;
        }

        CompletableFuture.allOf(futures).join();
        stopWatch.stop();

        System.out.println("take " + stopWatch.getTotalTimeMillis() + " millis to send message");
    }

}
