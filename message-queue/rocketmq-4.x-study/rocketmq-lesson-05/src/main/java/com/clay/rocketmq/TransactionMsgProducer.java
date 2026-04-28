package com.clay.rocketmq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事务消息的生产者
 */
public class TransactionMsgProducer {

    public static void main(String[] args) throws Exception {
        // 定义一个线程池
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        // 定义一个事务消息的生产者
        TransactionMQProducer producer = new TransactionMQProducer("pg");

        // 为生产者指定 NameServer 的地址
        producer.setNamesrvAddr("192.168.2.127:9876");

        // 为生产者指定一个线程池
        producer.setExecutorService(executorService);

        // 为生产者添加事务监听器
        producer.setTransactionListener(new ICBCTransactionListener());

        // 启动生产者
        producer.start();

        // 生产者发送 3 条事务消息
        String[] tags = {"TAGA", "TAGB", "TAGC"};
        for (int i = 0; i < 3; i++) {
            byte[] body = ("Hi," + i).getBytes();
            // 封装事务消息
            Message msg = new Message("TxTopic", tags[i], body);
            // 发送事务消息，第二个参数用于指定在执行本地事务时要使用的业务参数
            SendResult sendResult = producer.sendMessageInTransaction(msg, null);
            System.out.println("事务消息发送结果为：" + sendResult.getSendStatus());
        }
    }

}