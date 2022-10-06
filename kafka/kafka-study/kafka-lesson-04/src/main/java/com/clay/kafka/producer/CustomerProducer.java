package com.clay.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 普通的异步发送
 *
 * @author clay
 */
public class CustomerProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 指定Kafka集群的连接信息
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092,127.0.0.1:9093");
        // 指定序列化器（必需）
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 等待时间（默认0ms）
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        // 批次大小（默认16K）
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16 * 1024);
        // 压缩方式（默认none）
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        // 缓冲区大小（默认32M）
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 64 * 1024 * 1024);

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 5; i++) {
            // 异步发送消息
            producer.send(new ProducerRecord<>("test", "hello kafka " + i));
        }
        // 关闭资源
        producer.close();
    }

}
