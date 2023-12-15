package com.clay.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 生产者保证数据可靠性
 *
 * @author clay
 */
public class CustomerProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 指定Kafka的连接信息（若是Kafka集群，多个节点之间使用逗号分隔）
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.127:9092");
        // 指定序列化器（必需）
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 设置 ACK 应答级别，默认值是 "all"
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        // 设置重试次数，默认值是 int 类型的最大值 2147483647
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

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
