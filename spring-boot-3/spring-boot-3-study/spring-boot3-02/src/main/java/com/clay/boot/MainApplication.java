package com.clay.boot;

import com.clay.boot.properties.PigProperties;
import com.clay.boot.properties.SheepProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author clay
 */
@SpringBootApplication
@EnableConfigurationProperties(SheepProperties.class)
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        PigProperties pigProperties = context.getBean(PigProperties.class);
        System.out.println(pigProperties);

        SheepProperties sheepProperties = context.getBean(SheepProperties.class);
        System.out.println(sheepProperties);
    }

}
