package com.uim.ai.infrastructure.openai.domain.core.model;

public class Prompt<T> {
    private T data;
    private String promptInput;
    private String promptResponse;
    private GenerationType generationType = GenerationType.HTML;

    public static enum GenerationType {
        LATEX, HTML
    }

    public Prompt(T data) {
        this.data = data;
    }

    private Prompt(Builder<T> builder) {
        setData(builder.data);
        setPromptInput(builder.promptInput);
        setPromptResponse(builder.promptResponse);
        setGenerationType(builder.generationType);
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPromptInput() {
        return promptInput;
    }

    public void setPromptInput(String promptInput) {
        this.promptInput = promptInput;
    }

    public String getPromptResponse() {
        return promptResponse;
    }

    public void setPromptResponse(String promptResponse) {
        this.promptResponse = promptResponse;
    }

    public static final class Builder<T> {
        private T data;
        private String promptInput;
        private String promptResponse;
        private GenerationType generationType;

        public Builder() {
        }

        public Builder<T> data(T val) {
            data = val;
            return this;
        }

        public Builder<T> promptInput(String val) {
            promptInput = val;
            return this;
        }

        public Builder<T> promptResponse(String val) {
            promptResponse = val;
            return this;
        }

        public Builder<T> generationType(GenerationType val) {
            generationType = val;
            return this;
        }

        public Prompt<T> build() {
            return new Prompt<>(this);
        }
    }
}
