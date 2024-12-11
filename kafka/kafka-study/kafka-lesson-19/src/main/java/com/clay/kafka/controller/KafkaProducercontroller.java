package com.clay.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducercontroller {

    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息
     */
    @GetMapping("/produce")
    public String produce(String msg) {
        try {
            kafkaTemplate.send(TOPIC, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
