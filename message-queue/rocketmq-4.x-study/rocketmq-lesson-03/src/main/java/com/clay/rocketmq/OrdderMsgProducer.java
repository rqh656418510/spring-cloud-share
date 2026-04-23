package com.clay.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 生产者自定义 Queue 选择器（为了实现 Queue 内消息的有序发送和消费）
 */
public class OrdderMsgProducer {

    public static void main(String[] args) throws Exception {
        // 创建一个生产者，参数为 Producer Group（生产者组）的名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        // 指定 NameServer 的地址
        producer.setNamesrvAddr("192.168.2.127:9876");
        // 指定自动新创建的 Topic 的 Queue 数量，默认为 4
        producer.setDefaultTopicQueueNums(4);
        // 设置当发送失败时重试发送的次数，默认为 2 次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时间，默认 3 秒
        producer.setSendMsgTimeout(5000);

        // 启动生产者
        producer.start();

        // 生产并发送 10 条消息
        for (int i = 0; i < 10; i++) {
            // 订单 ID
            Integer orderId = i + 1;
            // 消息内容
            byte[] body = ("Hi," + i).getBytes();
            // 定义消息，包括设置 Topic、Tag、消息内容
            Message msg = new Message("TopicA", "TagA", body);
            // 为消息指定 Key
            msg.setKeys(orderId.toString());
            // 同步发送消息，并自定义 Queue 选择器
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    // 使用消息 key 作为 "选择 key"
                    // String keys = msg.getKeys();
                    // Integer orderId = Integer.valueOf(keys);

                    // 或者使用 arg 参数作为 "选择 key"
                    Integer orderId = (Integer) arg;

                    // 根据取模算法选择对应的 Queue（分区），从而保证同一个订单 ID 的消息在单个 Queue 内有序（分区有序）
                    int index = orderId % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);
            // 打印发送结果
            System.out.println(sendResult);
        }

        // 关闭生产者
        producer.shutdown();
    }

}