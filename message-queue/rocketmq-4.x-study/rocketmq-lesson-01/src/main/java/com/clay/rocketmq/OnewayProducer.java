package com.clay.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 生产者单向发送普通消息
 */
public class OnewayProducer {

    public static void main(String[] arsgs) throws Exception {
        // 创建一个生产者，参数为 Producer Group（生产者组）的名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        // 指定 NameServer 的地址
        producer.setNamesrvAddr("192.168.2.127:9876");
        // 指定自动新创建的 Topic 的 Queue 数量，默认为 4
        producer.setDefaultTopicQueueNums(4);

        // 启动生产者
        producer.start();

        // 生产并发送 10 条消息
        for (int i = 0; i < 10; i++) {
            byte[] body = ("Hi, " + i).getBytes();
            // 定义消息，包括设置 Topic、Tag、消息内容
            Message msg = new Message("MyTopic", "MyTag", body);
            // 为消息指定 Key
            msg.setKeys("key-" + i);
            // 单向发送消息（从调用线程的角度看是同步执行的，并不会在后台另起线程异步执行）
            producer.sendOneway(msg);
        }

        // 关闭生产者
        producer.shutdown();
    }

}