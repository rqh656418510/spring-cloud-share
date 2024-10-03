package com.springcloud.study.controller;

import com.springcloud.study.service.UserCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get")
    public String get(String userName) {
        UserCommand commandOne = new UserCommand(userName, restTemplate);
        commandOne.execute();
        logger.info("from cache: " + commandOne.isResponseFromCache());

        UserCommand commandTwo = new UserCommand(userName, restTemplate);
        commandTwo.execute();
        logger.info("from cache: " + commandTwo.isResponseFromCache());
        return "cache test finished";
    }

}
