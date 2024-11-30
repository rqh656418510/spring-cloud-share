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
 * 消费者自动提交 offset
 *
 * @author clay
 */
public class CustomerConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 指定Kafka的连接信息（若是Kafka集群，多个节点之间使用逗号分隔）
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 指定反序列化器（必须）
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 指定消费者组 ID（必须，可以任意定义）
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test3");
        // 是否自动提交 offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 指定自动提交 offset 的时间间隔，默认值是 5 秒
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);

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
        }
    }

}
