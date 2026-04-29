package com.clay.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量发送的生产者
 */
public class BatchProducer {

    public static void main(String[] args) throws Exception {
        // 创建一个生产者，参数为 Producer Group（生产者组）的名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");

        // 指定 NameServer 的地址
        producer.setNamesrvAddr("192.168.2.127:9876");

        // 指定要发送的消息的最大大小，默认是 4MB
        // 注意：仅修改该属性是不行的，还需要同时修改 Broker 加载的配置文件中的 maxMessageSize 属性
        producer.setMaxMessageSize(4 * 1024 * 1024);

        // 启动生产者
        producer.start();

        // 定义要发送的消息集合
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("BatchTopic", "BatchTag", body);
            messages.add(msg);
        }

        // 指定每条消息的极限大小（比如 4MB）
        final int sizeLimit = 4 * 1024 * 1024;

        // 定义消息列表分割器，将消息列表分割为多个不超出 4MB 大小的小列表
        MessageListSplitter splitter = new MessageListSplitter(messages, sizeLimit);
        while (splitter.hasNext()) {
            try {
                // 获取经过分割的消息列表
                List<Message> listItem = splitter.next();
                // 批量发送消息
                SendResult result = producer.send(listItem);
                // 打印发送结果
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 关闭生产者
        producer.shutdown();
    }

}