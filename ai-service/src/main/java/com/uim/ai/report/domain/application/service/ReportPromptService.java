package com.uim.ai.report.domain.application.service;

import com.uim.ai.infrastructure.openai.domain.application.port.spi.PromptDataValidator;
import com.uim.ai.infrastructure.openai.domain.application.port.spi.PromptGenerator;
import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;
import com.uim.ai.infrastructure.utils.FileProcessorUtil;
import com.uim.api.infrastructure.calculation.domain.core.model.CalculatedReport;
import com.uim.api.report.domain.core.model.ReportStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Qualifier(ReportPromptService.REPORT_PROMPT_SERVICE_QUALIFIER)
@RequiredArgsConstructor
public class ReportPromptService implements PromptGenerator<CalculatedReport>, PromptDataValidator<CalculatedReport> {

    public static final String REPORT_PROMPT_SERVICE_QUALIFIER = "REPORT_PROMPT_SERVICE_QUALIFIER";
    @Value("classpath:prompts/one-page-report-prompt.txt")
    private Resource onePageReportPromptResource;

    private final AiService aiService;

    @Override
    public Prompt<CalculatedReport> generate(CalculatedReport data) {
        Prompt<CalculatedReport> prompt = new Prompt.Builder<CalculatedReport>()
                .data(data)
                .promptInput(getPromptInput(data))
                .generationType(getGenerationType(data))
                .build();
        return aiService.generate(prompt);
    }

    private Prompt.GenerationType getGenerationType(CalculatedReport data) {
        return switch (data.getInitializationParameters().getType()) {
            case ONE_PAGE -> Prompt.GenerationType.HTML;
            default -> throw new IllegalArgumentException("Incorrect type of the report");
        };
    }

    private String getPromptInput(CalculatedReport data) {
        return switch (data.getInitializationParameters().getType()) {
            case ONE_PAGE -> FileProcessorUtil.processFile(onePageReportPromptResource, getOnePagePlaceholders(data));
            default -> throw new IllegalArgumentException("Incorrect type of the report");
        };
    }

    private Map<String, String> getOnePagePlaceholders(CalculatedReport data) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("\\$\\$FULL_REPORT\\$\\$", data.toString());
        return placeholders;
    }

    @Override
    public void validate(CalculatedReport report) {
        if (!ReportStatus.PENDING.equals(report.getReport().getStatus())) {
            throw new IllegalStateException("Report is in the incorrect state");
        }
    }
}
