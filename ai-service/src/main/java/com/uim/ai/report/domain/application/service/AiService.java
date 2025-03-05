package com.uim.ai.report.domain.application.service;

import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public <T> Prompt<T> generate(Prompt<T> prompt) {
        String response = chatClient.prompt(prompt.getPromptInput()).call().content();
        prompt.setPromptResponse(response);
        return prompt;
    }
}
