package com.clay.kafka.consumer;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * Kafka 集成 Flink（消费者）
 *
 * @author clay
 */
public class FlinkKafkaConsumerTest1 {

    public static void main(String[] args) throws Exception {
        // 初始化 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Kafka 消费者的配置信息
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "flink1");

        // 创建 Kafka 消费者
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer("first", new SimpleStringSchema(), properties);
        kafkaConsumer.setStartFromLatest();

        // Flink 流关联 Kafka 消费者
        DataStream<String> dataStream = env.addSource(kafkaConsumer);
        dataStream.print();

        // 执行
        env.execute();
    }

}
