package com.clay.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 生产者使用事务
 * <p> 使用事务之前，必须开启幂等性
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

        // 设置事务 ID（全局唯一）
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction_id_01");

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 初始化事务
        producer.initTransactions();

        // 开启事务
        producer.beginTransaction();

        try {
            // 发送数据
            for (int i = 0; i < 5; i++) {
                producer.send(new ProducerRecord<>("test", "hello kafka " + i));
            }
            // 提交事务
            producer.commitTransaction();
        } catch (Exception e) {
            // 放弃事务
            producer.abortTransaction();
        } finally {
            // 关闭资源
            producer.close();
        }

    }

}
