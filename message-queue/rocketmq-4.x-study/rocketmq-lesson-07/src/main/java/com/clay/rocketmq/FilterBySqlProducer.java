package com.clay.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 消息生产者
 */
public class FilterBySqlProducer {

    public static void main(String[] args) throws Exception {
        // 创建一个生产者，参数为 Producer Group（生产者组）的名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");

        // 指定 NameServer 的地址
        producer.setNamesrvAddr("192.168.2.127:9876");

        // 启动生产者
        producer.start();

        // 发送 10 条消息
        for (int i = 0; i < 10; i++) {
            // 定义消息（不带 Tag）
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("MyTopic", body);

            // 新增用户自定义属性
            msg.putUserProperty("age", String.valueOf(i));

            // 发送消息
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }

        // 关闭生产者
        producer.shutdown();
    }

}
