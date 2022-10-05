package com.clay.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义分区器
 *
 * @author clay
 */
public class CustomPartitioner implements Partitioner {

    /**
     * 返回信息对应的分区
     *
     * @param topic      主题
     * @param key        消息的 key
     * @param keyBytes   消息的 key 序列化后的字节数组
     * @param value      消息的 value
     * @param valueBytes 消息的 value 序列化后的字节数组
     * @param cluster    集群元数据可以查看分区信息
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 获取消息内容
        String msgValue = value.toString();

        // 定义分区号
        int partition;

        if (msgValue.contains("order")) {
            partition = 0;
        } else {
            partition = 1;
        }
        // 返回分区号
        return partition;
    }

    /**
     * 关闭资源
     */
    @Override
    public void close() {

    }

    /**
     * 配置信息
     *
     * @param configs
     */
    @Override
    public void configure(Map<String, ?> configs) {

    }

}
