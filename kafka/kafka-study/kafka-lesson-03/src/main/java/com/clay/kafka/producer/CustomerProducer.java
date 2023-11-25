package com.clay.kafka.producer;

import com.clay.kafka.partitioner.CustomPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 异步发送
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
        // 指定自定义分区器
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 5; i++) {
            // 异步发送消息
            producer.send(new ProducerRecord<>("test", "hello kafka " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("Partition : " + metadata.partition());
                }
            });
        }
        // 关闭资源
        producer.close();
    }

}