package com.clay.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author clay
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MainApplication.class);
        // 设置Banner（此方式的配置优先级较低，低于配置文件）
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
    }

}
