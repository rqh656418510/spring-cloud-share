package com.clay.boot.starter.robot.config;

import com.clay.boot.starter.robot.controller.RobotController;
import com.clay.boot.starter.robot.properties.RobotProperties;
import com.clay.boot.starter.robot.service.RobotService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author clay
 */
@Configuration
@Import({RobotController.class, RobotService.class})
@EnableConfigurationProperties(RobotProperties.class)
public class RobotAutoConfiguration {

}
