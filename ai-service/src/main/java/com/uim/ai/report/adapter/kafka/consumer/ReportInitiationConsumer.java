package com.uim.ai.report.adapter.kafka.consumer;

import com.uim.api.infrastructure.calculation.domain.core.model.CalculatedReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportInitiationConsumer {

    @KafkaListener(topics = "${spring.kafka.topics.pdf-generation-request}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ConsumerRecord<String, CalculatedReport> reportConsumerRecord) {
        log.info("key: {}", reportConsumerRecord.key());
        log.info("data: {}", reportConsumerRecord.value());
    }
}
