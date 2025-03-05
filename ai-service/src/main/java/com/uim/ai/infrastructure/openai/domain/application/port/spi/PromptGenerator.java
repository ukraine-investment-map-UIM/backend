package com.uim.ai.infrastructure.openai.domain.application.port.spi;

import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;

public interface PromptGenerator<T> {

    Prompt<T> generate(T data);
}
