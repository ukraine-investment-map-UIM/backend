package com.uim.map.report.config;

import com.uim.map.area.adapter.persistence.AreaPersistenceAdapter;
import com.uim.map.layer.adapter.persistence.LayerPersistenceAdapter;
import com.uim.map.report.adapter.persistence.ReportPersistenceAdapter;
import com.uim.map.report.domain.core.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ReportConfig {

    private final ReportPersistenceAdapter reportDao;
    private final AreaPersistenceAdapter areaDao;
    private final LayerPersistenceAdapter layerDao;

    @Bean
    public ReportService reportService() {
        return new ReportService(reportDao, areaDao, layerDao);
    }
}
