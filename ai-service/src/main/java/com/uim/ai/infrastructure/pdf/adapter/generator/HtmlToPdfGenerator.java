package com.uim.ai.infrastructure.pdf.adapter.generator;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.uim.ai.infrastructure.config.aws.S3Service;
import com.uim.ai.infrastructure.openai.domain.core.model.Prompt;
import com.uim.ai.infrastructure.pdf.domain.application.port.spi.PdfGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Qualifier(HtmlToPdfGenerator.HTML_PDF_GENERATOR_QUALIFIER)
public class HtmlToPdfGenerator implements PdfGenerator {
    public static final String HTML_PDF_GENERATOR_QUALIFIER = "HTML_PDF_GENERATOR_QUALIFIER";
    private final S3Service s3Service;

    @Override
    @SneakyThrows
    public <T> String generateByPrompt(Prompt<T> prompt) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(prompt.getPromptResponse(), null);
            builder.toStream(os);
            builder.run();

            byte[] pdfBytes = os.toByteArray();
            String filename = UUID.randomUUID().toString() + "-report.pdf";
            return s3Service.uploadToS3(pdfBytes, filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
