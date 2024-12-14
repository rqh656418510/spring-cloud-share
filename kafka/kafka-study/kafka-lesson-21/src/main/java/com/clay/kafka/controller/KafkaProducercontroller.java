package com.clay.kafka.controller;

import com.clay.kafka.config.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducercontroller {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息
     */
    @GetMapping("/produce")
    public String produce(String msg) {
        try {
            kafkaTemplate.send(KafkaConstants.TOPIC_TEST, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
