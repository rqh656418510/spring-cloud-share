package com.clay.kafka.controller;

import com.clay.kafka.domain.Person;
import com.clay.kafka.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Autowired
    private MessageProducer messageProducer;

    /**
     * 发送消息
     */
    @PostMapping("/produce")
    public String produce(@RequestBody Person person) {
        try {
            messageProducer.send(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}