package com.clay.kafka.producer;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * Kafka 集成 Flink（生产者）
 *
 * @author clay
 */
public class FlinkKafkaProducerTest2 {

    public static void main(String[] args) throws Exception {
        // 初始化 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 模拟源数据
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("flink");
        DataStream<String> dataStream = env.fromCollection(list);

        // 配置 KafkaSink（相当于 Kafka 生产者）
        KafkaSink<String> kafkaSink = KafkaSink.<String>builder()
            .setBootstrapServers("127.0.0.1:9092")
            .setRecordSerializer(
                KafkaRecordSerializationSchema.builder()
                    .setTopic("first")
                    .setKeySerializationSchema(new SimpleStringSchema())
                    .setValueSerializationSchema(new SimpleStringSchema())
                    .build()
            )
            .setDeliveryGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
            .build();

        // Flink 流关联 KafkaSink
        dataStream.sinkTo(kafkaSink);

        // 执行
        env.execute();
    }

}
