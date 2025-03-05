package com.uim.map.report.adapter.kafka.consumer;

import com.uim.api.infrastructure.calculation.domain.core.model.CalculatedReport;
import com.uim.api.report.domain.core.model.Report;
import com.uim.map.report.domain.application.port.spi.ReportDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportPdfConsumer {

    private final ReportDao reportDao;

    @KafkaListener(topics = "${spring.kafka.topics.pdf-generation-response}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ConsumerRecord<String, CalculatedReport> reportConsumerRecord) {
        Report updatedReport = reportDao.updateReport(reportConsumerRecord.value().getReport());
        log.info("Updated report: {}", updatedReport.toString());
    }
}
