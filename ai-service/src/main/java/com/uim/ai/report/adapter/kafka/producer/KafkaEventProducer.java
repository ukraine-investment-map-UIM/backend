package com.uim.ai.report.adapter.kafka.producer;

import com.uim.ai.report.domain.application.port.spi.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer<T> implements EventPublisher<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void publish(String key, T message, String topicName) {
        kafkaTemplate.send(topicName, key, message);
        log.info("Event {} has been published with body {}", message.getClass(), message);
    }
}
