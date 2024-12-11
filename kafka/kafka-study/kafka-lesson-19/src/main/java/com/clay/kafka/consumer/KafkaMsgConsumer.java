package com.clay.kafka.consumer;

import com.clay.kafka.config.KafkaConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaMsgConsumer {

    /**
     * 消费消息（批量消费）
     */
    @KafkaListener(topics = KafkaConstants.TOPIC_TEST, groupId = KafkaConstants.GROUP_ID)
    public void receive(List<ConsumerRecord<String, String>> records, Acknowledgment acknowledgment) {
        try {
            for (ConsumerRecord<String, String> record : records) {
                // 处理消息
                System.out.println("Group: " + KafkaConstants.GROUP_ID + ", Topic: " + record.topic() + ", Msg: " + record.value());
            }
            // 手动提交偏移量
            acknowledgment.acknowledge();
        } catch (Exception e) {
            // 消息处理失败，选择不提交偏移量，保证消息再次消费
            System.err.println("Error processing message: " + e.getMessage());
        }
    }

}
