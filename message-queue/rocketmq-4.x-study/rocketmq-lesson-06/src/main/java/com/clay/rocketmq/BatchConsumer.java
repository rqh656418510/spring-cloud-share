package com.clay.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 批量消费的消费者
 */
public class BatchConsumer {

    public static void main(String[] args) throws Exception {
        // 定义一个 Push 消费者（使用 Push 消费方式），参数为 Consumer Group（消费者组）的名称
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("cg");

        // 指定 NameServer 的地址
        consumer.setNamesrvAddr("192.168.2.127:9876");

        // 指定从哪个位置开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 指定消费的 Topic 与 Tag
        consumer.subscribe("BatchTopic", "*");

        // 指定消费模式，默认为 "集群消费模式"
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 指定每次消费的最大消息数量，默认值是 1
        consumer.setConsumeMessageBatchMaxSize(20);

        // 指定消费者每次从 Broker 拉取的最大消息数量，默认值是 32
        consumer.setPullBatchSize(40);

        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            // 一旦 Broker 中有了其订阅的消息就会触发该方法的执行，其返回值为当前 Consumer 消费的状态
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // 打印一次消费的消息数量
                System.out.println("Batch consume size: " + msgs.size());

                // 遍历消息
                for (MessageExt msg : msgs) {
                    System.out.println(msg);
                }

                // 批量消费成功时的返回结果
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

                // 批量消费失败时的返回结果
                // return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

        });

        // 启动消费者
        consumer.start();

        // 等待消费者消费完
        TimeUnit.SECONDS.sleep(120);

        // 关闭消费者
        consumer.shutdown();
    }

}