package com.uim.ai.infrastructure.config.aws;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@Getter
public class AwsConfig {

    @Value("${aws.s3.access-key}")
    private String accessKey;
    @Value("${aws.s3.password}")
    private String password;
    @Value("${aws.region}")
    private String region;
    @Value("${aws.s3.bucket}")
    private String bucket;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, password)))
                .build();
    }

}
