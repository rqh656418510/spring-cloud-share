package com.clay.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用 Tag 过滤的消息消费者
 */
public class FilterByTagConsumer {

    public static void main(String[] args) throws Exception {
        // 定义一个 Push 消费者（使用 Push 消费方式），参数为 Consumer Group（消费者组）的名称
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pg");

        // 指定 NameServer 的地址
        consumer.setNamesrvAddr("192.168.2.127:9876");

        // 指定从哪个位置开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 指定消费的 Topic 与 Tag
        consumer.subscribe("MyTopic", "MyTagA || MyTagB");

        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // 遍历消息
                for (MessageExt me : msgs) {
                    System.out.println(me);
                }

                // 消费成功时的返回结果
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
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
