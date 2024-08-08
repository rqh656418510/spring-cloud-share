package com.clay.boot.starter.robot.controller;

import com.clay.boot.starter.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @GetMapping("/saveHello")
    public String sayHello() {
        return robotService.sayHello();
    }

}
