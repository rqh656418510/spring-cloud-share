package com.clay.kafka.consumer;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Kafka 集成 Flink（消费者）
 *
 * @author clay
 */
public class FlinkKafkaConsumerTest2 {

    public static void main(String[] args) throws Exception {
        // 初始化 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 配置 KafkaSource（相当于 Kafka 消费者）
        KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
            .setBootstrapServers("127.0.0.1:9092")
            .setTopics("first")
            .setGroupId("flink2")
            .setStartingOffsets(OffsetsInitializer.latest())
            .setValueOnlyDeserializer(new SimpleStringSchema())
            .build();

        // Flink 流关联 KafkaSource
        DataStreamSource<String> stream = env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "kafka-source");
        stream.print();

        // 执行
        env.execute();
    }

}
