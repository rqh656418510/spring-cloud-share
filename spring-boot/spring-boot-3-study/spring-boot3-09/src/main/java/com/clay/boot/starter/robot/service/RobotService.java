package com.clay.boot.starter.robot.service;

import com.clay.boot.starter.robot.properties.RobotProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author clay
 */
@Service
public class RobotService {

    @Autowired
    private RobotProperties robotProperties;

    public String sayHello() {
        return "Hello " + robotProperties.getName();
    }

}
