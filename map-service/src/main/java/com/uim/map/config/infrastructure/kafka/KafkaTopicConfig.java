package com.uim.map.config.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createMyJsonTopic() {
        return TopicBuilder.name("prod.uim.generate-pdf")
                .partitions(1)
                .replicas(1)
                .build();
    }
}