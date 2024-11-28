package com.clay.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 带回调函数的同步发送
 *
 * @author clay
 */
public class CustomerProducer2 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 指定Kafka的连接信息（若是Kafka集群，多个节点之间使用逗号分隔）
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 指定序列化器（必须）
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 5; i++) {
            // 同步发送消息（带回调函数）
            try {
                producer.send(new ProducerRecord<>("test", "hello kafka " + i), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
                        if (exception == null) {
                            System.out.println("topic: " + recordMetadata.topic() + ", partition: " + recordMetadata.partition());
                        }
                    }
                }).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 关闭资源
        producer.close();
    }

}