package com.uim.ai.report.adapter.kafka.consumer;

import com.uim.ai.infrastructure.openai.domain.application.port.spi.PromptDataValidator;
import com.uim.ai.infrastructure.openai.domain.application.port.spi.PromptGenerator;
import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;
import com.uim.ai.infrastructure.pdf.adapter.generator.HtmlToPdfGenerator;
import com.uim.ai.infrastructure.pdf.domain.application.port.spi.PdfGenerator;
import com.uim.api.infrastructure.calculation.domain.core.model.CalculatedReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportInitiationConsumer {

    private final PromptDataValidator<CalculatedReport> promptDataValidator;
    private final PromptGenerator<CalculatedReport> promptGenerator;
    private final HtmlToPdfGenerator htmlToPdfGenerator;

    @KafkaListener(topics = "${spring.kafka.topics.pdf-generation-request}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ConsumerRecord<String, CalculatedReport> reportConsumerRecord) {
        log.info("key: {}", reportConsumerRecord.key());
        log.info("data: {}", reportConsumerRecord.value());
        CalculatedReport report = reportConsumerRecord.value();
        promptDataValidator.validate(report);
        Prompt<CalculatedReport> prompt = promptGenerator.generate(report);
//        Prompt<CalculatedReport> prompt = new Prompt.Builder<CalculatedReport>()
//                .promptResponse("""
//                        <!DOCTYPE html>
//                        <html lang="en" xmlns="http://www.w3.org/1999/xhtml">
//                        <head>
//                            <meta charset="UTF-8" />
//                            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
//                            <title>Investment Analysis Report</title>
//                            <style>
//                                body {
//                                    font-family: Arial, sans-serif;
//                                    margin: 20px;
//                                    padding: 20px;
//                                }
//                                h1, h2 {
//                                    text-align: center;
//                                    color: #333;
//                                }
//                                .chart-container {
//                                    width: 80%;
//                                    margin: 20px auto;
//                                    text-align: center;
//                                }
//                                img {
//                                    width: 80%;
//                                    max-width: 600px;
//                                    display: block;
//                                    margin: 0 auto;
//                                }
//                            </style>
//                        </head>
//                        <body>
//                        <h1>Investment Analysis Report</h1>
//
//                        <h2>Title &amp; Introduction</h2>
//                        <p>A brief summary of the selected area based on its coordinates.</p>
//                        <p>A description of the layers (e.g., water availability, soil type, etc.) and how they influence investment opportunities.</p>
//
//                        <h2>Charts &amp; Graphs</h2>
//                        <div class="chart-container">
//                            <img src="https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg" alt="Water Intensity Chart" />
//                        </div>
//
//                        <h2>Investment Suggestions</h2>
//                        <ul>
//                            <li><strong>Agriculture:</strong> Recommend crops based on soil &amp; water availability</li>
//                            <li><strong>Real Estate:</strong> Evaluate housing or commercial potential based on location</li>
//                            <li><strong>Infrastructure Projects:</strong> Assess feasibility near water sources or landmarks</li>
//                        </ul>
//
//                        <h2>Conclusion</h2>
//                        <p>Summarize key insights and offer potential next steps for a detailed feasibility study.</p>
//                        </body>
//                        </html>
//                        """)
//                .generationType(Prompt.GenerationType.HTML)
//                .build();
        if (Prompt.GenerationType.HTML.equals(prompt.getGenerationType())) {
            String url = htmlToPdfGenerator.generateByPrompt(prompt);

        }

    }
}
