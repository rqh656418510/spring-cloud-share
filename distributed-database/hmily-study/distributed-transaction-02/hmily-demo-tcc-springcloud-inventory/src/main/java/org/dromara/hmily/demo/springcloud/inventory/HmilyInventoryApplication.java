package org.dromara.hmily.demo.springcloud.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The type Spring cloud tcc inventory application.
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagement
@ImportResource({"classpath:applicationContext.xml"})
@MapperScan("org.dromara.hmily.demo.common.inventory.mapper")
public class HmilyInventoryApplication {

    public static void main(final String[] args) {
        SpringApplication.run(HmilyInventoryApplication.class, args);
    }

}