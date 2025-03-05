package com.uim.ai.infrastructure.pdf.domain.application;

import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PdfGenerator {

    public <T> void generateByPrompt(Prompt<T> prompt) {

    }

}
