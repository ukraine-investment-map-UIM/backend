package com.uim.ai.infrastructure.pdf.domain.application.port.spi;

import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;

public interface PdfGenerator {

    <T> String generateByPrompt(Prompt<T> prompt);
}
