package com.uim.ai.report.domain.application.port.spi;

public interface EventPublisher<T> {

    void publish(String key, T message, String topicName);
}
