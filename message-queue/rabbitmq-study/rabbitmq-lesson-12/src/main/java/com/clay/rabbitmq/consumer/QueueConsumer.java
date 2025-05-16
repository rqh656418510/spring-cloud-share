package com.clay.rabbitmq.consumer;

import com.clay.rabbitmq.config.QueueConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class QueueConsumer {

    /**
     * 监听队列
     *
     * <p> 参数 ackMode = "MANUAL" 表示使用手动确认消费机制
     */
    @RabbitListener(queues = QueueConfig.ORDER_QUEUE_NAME, ackMode = "MANUAL")
    public void receiveMsg(Message message, Channel channel) throws Exception {
        // 消息内容
        String content = new String(message.getBody(), StandardCharsets.UTF_8);

        // 消息唯一标记
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            // 模拟消息处理耗时
            Thread.sleep(5000);
            log.info("当前时间: {}, 接收到信息: {}", new Date(), content);

            // 手动确认消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 消息处理失败时，可选择拒绝消息并决定是否重新入队
            channel.basicNack(deliveryTag, false, true);
            // 记录错误日志信息
            log.error("Consume message failed: {}", e.getMessage());
        }
    }

}
