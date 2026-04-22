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
 * 消费者消费普通消息
 */
public class MessageConsumer {

    public static void main(String[] args) throws Exception {
        // 如果需要使用 Pull 消费者（使用 Pull 消费方式），可以使用 DefaultLitePullConsumer（如下所示）
        // DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("cg");

        // 定义一个 Push 消费者（使用 Push 消费方式），参数为 Consumer Group（消费者组）的名称
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("cg");

        // 指定 NameServer 的地址
        consumer.setNamesrvAddr("192.168.2.127:9876");

        // 指定消费线程的最小数量，默认是 20 个消费线程
        consumer.setConsumeThreadMin(2);

        // 指定消费线程的最大数量，默认是 20 个消费线程
        consumer.setConsumeThreadMax(4);

        // 指定从哪个位置开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 指定消费的 Topic 与 Tag
        consumer.subscribe("MyTopic", "*");

        // 指定消费模式，默认为 "集群消费模式"
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            // 一旦 Broker 中有了其订阅的消息就会触发该方法的执行，其返回值为当前 Consumer 消费的状态
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // 遍历消息
                for (MessageExt msg : msgs) {
                    System.out.println(msg);
                }
                // 返回消费状态：消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动消费者
        consumer.start();

        // 等待消费者消费完
        TimeUnit.SECONDS.sleep(15);

        // 关闭消费者
        consumer.shutdown();
    }
}