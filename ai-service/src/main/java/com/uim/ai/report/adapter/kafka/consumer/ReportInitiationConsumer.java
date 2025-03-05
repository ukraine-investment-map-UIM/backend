package com.uim.ai.report.adapter.kafka.consumer;

import com.uim.ai.infrastructure.openai.domain.application.port.spi.PromptDataValidator;
import com.uim.ai.infrastructure.openai.domain.application.port.spi.PromptGenerator;
import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;
import com.uim.ai.infrastructure.pdf.adapter.generator.HtmlToPdfGenerator;
import com.uim.ai.report.adapter.kafka.producer.KafkaEventProducer;
import com.uim.api.infrastructure.calculation.domain.core.model.CalculatedReport;
import com.uim.api.report.domain.core.model.ReportStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportInitiationConsumer {

    private final PromptDataValidator<CalculatedReport> promptDataValidator;
    private final PromptGenerator<CalculatedReport> promptGenerator;
    private final HtmlToPdfGenerator htmlToPdfGenerator;
    private final KafkaEventProducer<CalculatedReport> kafkaEventProducer;
    @Value("${spring.kafka.topics.pdf-generation-response}")
    private String pdfGenerationResponseTopic;

    @KafkaListener(topics = "${spring.kafka.topics.pdf-generation-request}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ConsumerRecord<String, CalculatedReport> reportConsumerRecord) {
        log.info("key: {}", reportConsumerRecord.key());
        log.info("data: {}", reportConsumerRecord.value());
        CalculatedReport report = reportConsumerRecord.value();
        promptDataValidator.validate(report);
        Prompt<CalculatedReport> prompt = promptGenerator.generate(report);
        if (Prompt.GenerationType.HTML.equals(prompt.getGenerationType())) {
            String url = htmlToPdfGenerator.generateByPrompt(prompt);
            report.getReport().setUrl(url);
            report.getReport().setStatus(ReportStatus.FORMED);
            kafkaEventProducer.publish(reportConsumerRecord.key(), report, pdfGenerationResponseTopic);
        }

    }
}
