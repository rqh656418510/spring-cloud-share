package com.clay.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 消费者组的分区分配策略 - Sticky
 *
 * @author clay
 */
public class CustomerProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 指定Kafka的连接信息（若是Kafka集群，多个节点之间使用逗号分隔）
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 指定序列化器（必须）
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 异步发送消息（带回调函数）
            producer.send(new ProducerRecord<>("first", "hello kafka " + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
                    if (exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.println("topic: " + recordMetadata.topic() + ", partition: " + recordMetadata.partition());
                    }
                }
            });
        }
        // 关闭资源
        producer.close();
    }

}
