package com.clay.boot.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SportTopicListener {

    /**
     * 订阅消息（最新的）
     *
     * @param record
     */
    @KafkaListener(topics = "sport", groupId = "sport-group-1")
    public void subscribeNewest(ConsumerRecord record) {
        Object key = record.key();
        Object value = record.value();
        String topic = record.topic();
        log.info("receive message from topic {}, key: {}, value: {}", topic, key, value);
    }

    /**
     * 订阅消息（（所有消息，包括历史消息））
     *
     * @param record
     */
    @KafkaListener(groupId = "sport-group-2", topicPartitions = {
        @TopicPartition(topic = "sport", partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")})})
    public void subscribeAll(ConsumerRecord record) {
        Object key = record.key();
        Object value = record.value();
        String topic = record.topic();
        log.info("receive message from topic [{}], key: {}, value: {}", topic, key, value);
    }

}
