package com.uim.ai.infrastructure.openai.domain.application.port.spi;

public interface PromptDataValidator<T> {

    void validate(T data);
}
