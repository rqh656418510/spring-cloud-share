package com.clay.kafka.controller;

import com.clay.kafka.domain.Person;
import com.clay.kafka.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private MessageProducer messageProducer;

    /**
     * 第一种方式发送消息
     * <p> 依赖 StreamBridge
     * <p> 依赖 spring.cloud.stream.bindings 的配置
     * <p> 不依赖 spring.cloud.function.definition 的配置，但建议加上对应的配置
     */
    @PostMapping("/produce")
    public String produce(@RequestBody Person person) {
        try {
            // 发送消息到指定的绑定目标（Binding）
            streamBridge.send("sendMsg-out-0", person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 第二种方式发送消息
     * <p> 依赖函数式编程(定义 Supplier)
     * <p> 依赖 spring.cloud.stream.bindings 的配置
     * <p> 依赖 spring.cloud.function.definition 的配置
     * <p> 不依赖 StreamBridge
     */
    @PostMapping("/sendMsg")
    public String send(@RequestBody Person person) {
        try {
            messageProducer.sendPersonMessage(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
