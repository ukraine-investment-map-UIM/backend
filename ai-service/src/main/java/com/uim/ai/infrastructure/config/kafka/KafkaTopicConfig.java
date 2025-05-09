package com.uim.ai.infrastructure.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createGeneratePdfTopic() {
        return TopicBuilder.name("prod.uim.generate-pdf")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createPdfResponseTopic() {
        return TopicBuilder.name("prod.uim.generate-pdf-response")
                .partitions(1)
                .replicas(1)
                .build();
    }
}