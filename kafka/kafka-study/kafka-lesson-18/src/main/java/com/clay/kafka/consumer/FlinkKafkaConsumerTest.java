package com.clay.kafka.consumer;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Kafka 集成 Flink
 *
 * @author clay
 */
public class FlinkKafkaConsumerTest {

    public static void main(String[] args) throws Exception {
        // 初始化 Flink 环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(3);

        // Kafka 数据源
        KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
            .setBootstrapServers("127.0.0.1:9092")
            .setTopics("first")
            .setGroupId("flink")
            .setStartingOffsets(OffsetsInitializer.latest())
            .setValueOnlyDeserializer(new SimpleStringSchema())
            .build();

        DataStreamSource<String> stream = env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "kafka-source");
        stream.print("kafka");

        // 执行
        env.execute();
    }

}
