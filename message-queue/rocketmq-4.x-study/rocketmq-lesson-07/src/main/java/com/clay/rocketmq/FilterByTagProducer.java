package com.clay.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 消息生产者
 */
public class FilterByTagProducer {

    public static void main(String[] args) throws Exception {
        // 创建一个生产者，参数为 Producer Group（生产者组）的名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");

        // 指定 NameServer 的地址
        producer.setNamesrvAddr("192.168.2.127:9876");

        // 启动生产者
        producer.start();

        String[] tags = {"MyTagA", "MyTagB", "MyTagC"};

        // 发送 10 条消息
        for (int i = 0; i < 10; i++) {
            // 定义消息
            byte[] body = ("Hi," + i).getBytes();
            String tag = tags[i % tags.length];
            Message msg = new Message("MyTopic", tag, body);
            // 发送消息
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }

        // 关闭生产者
        producer.shutdown();
    }

}
