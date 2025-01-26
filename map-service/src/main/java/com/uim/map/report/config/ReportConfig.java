package com.uim.map.report.config;

import com.uim.map.report.domain.core.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ReportConfig {

    @Bean
    public ReportService reportService() {
        return new ReportService();
    }
}
