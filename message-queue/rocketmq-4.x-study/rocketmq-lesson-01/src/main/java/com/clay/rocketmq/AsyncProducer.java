package com.clay.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 生产者异步发送消息
 */
public class AsyncProducer {

    public static void main(String[] arsgs) throws Exception {
        // 创建一个生产者，参数为 Producer Group（生产者组）的名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        // 指定 NameServer 的地址
        producer.setNamesrvAddr("192.168.2.127:9876");
        // 指定自动新创建的 Topic 的 Queue 数量，默认为 4
        producer.setDefaultTopicQueueNums(4);
        // 设置当异步发送失败时重试发送的次数
        producer.setRetryTimesWhenSendAsyncFailed(0);

        // 启动生产者
        producer.start();

        // 消息数量
        final int size = 10;

        // 消息发送计数器
        CountDownLatch countDownLatch = new CountDownLatch(size);

        // 生产并发送 10 条消息
        for (int i = 0; i < size; i++) {
            byte[] body = ("Hi, " + i).getBytes();
            try {
                // 定义消息，包括设置 Topic、Tag、消息内容
                Message msg = new Message("MyTopic", "MyTag", body);
                // 为消息指定 Key
                msg.setKeys("key-" + i);
                // 异步发送消息，指定回调接口
                producer.send(msg, new SendCallback() {

                    // 当生产者接收到 MQ 发送来的 ACK 后，就会触发该回调方法的执行
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        countDownLatch.countDown();
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 由于采用的是异步发送，所以如果这里不等待消息发送完成，就会将生产者提前关闭掉，导致消息发送失败
        countDownLatch.await(5, TimeUnit.SECONDS);

        // 关闭生产者
        producer.shutdown();
    }

}