package com.clay.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 消费者手动提交 offset（同步提交）
 *
 * @author clay
 */
public class CustomerConsumer1 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 指定Kafka的连接信息（若是Kafka集群，多个节点之间使用逗号分隔）
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 指定反序列化器（必须）
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 指定消费者组 ID（必须，可以任意定义）
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // 关闭自动提交 offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        // 创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 订阅要消费的主题
        List<String> topics = new ArrayList<>();
        topics.add("first");
        consumer.subscribe(topics);

        while (true) {
            // 设置每隔 1 秒消费一批数据
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
            // 同步提交 offset（会阻塞当前线程，并且有失败重试机制）
            consumer.commitSync();
        }
    }

}
