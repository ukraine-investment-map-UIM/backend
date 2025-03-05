package com.uim.ai.infrastructure.config.aws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    private final S3Client s3Client;
    private final AwsConfig awsConfig;


    public String uploadToS3(byte[] pdfBytes, String filename) {
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(awsConfig.getBucket())
                .key(filename)
                .contentType("application/pdf")
                .build();
        s3Client.putObject(putRequest, RequestBody.fromBytes(pdfBytes));
        String url = "https://" + awsConfig.getBucket() + ".s3.amazonaws.com/" + filename;
        log.info("PDF url: {}", url);
        return url;
    }
}
