package com.clay.boot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author clay
 */
@Configuration
public class KafkaConfiguration {

    /**
     * 创建 Topic(主题)
     *
     * @return
     */
    @Bean
    public NewTopic sportTopic() {
        return TopicBuilder.name("sport")
            .partitions(1)
            .compact()
            .build();
    }

}
