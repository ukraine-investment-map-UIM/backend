package com.uim.ai.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@Slf4j
public class FileProcessorUtil {

    public static String readFile(Resource resource) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))) {
            return br.lines().reduce("", (acc, line) -> acc + line + '\n');
        } catch (IOException e) {
            log.error("Unable to read from file {}", resource.getFilename());
            throw new IllegalArgumentException("Unable to read from file", e);
        }
    }

    public static String replacePlaceholders(String content, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            content = content.replaceAll(entry.getKey(), entry.getValue());
        }
        return content;
    }

    public static String processFile(Resource resource, Map<String, String> placeholders) {
        String content = readFile(resource);
        return replacePlaceholders(content, placeholders);
    }
}
