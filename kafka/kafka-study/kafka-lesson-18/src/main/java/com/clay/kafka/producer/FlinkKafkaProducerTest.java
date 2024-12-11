package com.clay.kafka.producer;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Kafka 集成 Flink（生产者）
 *
 * @author clay
 */
public class FlinkKafkaProducerTest {

    public static void main(String[] args) throws Exception {
        // 初始化 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 模拟源数据
        List<String> worldsList = new ArrayList<>();
        worldsList.add("hello");
        worldsList.add("flink");
        DataStreamSource<String> stream = env.fromCollection(worldsList);

        // Kafka 生产者的配置信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        // 创建 Kafka 生产者
        FlinkKafkaProducer<String> kafkaProducer = new FlinkKafkaProducer("first", new SimpleStringSchema(), properties);

        // Kafka 生产者和 Flink 流关联
        stream.addSink(kafkaProducer);

        // 执行
        env.execute();
    }

}
