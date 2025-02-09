package com.uim.map.layer.config;

import com.uim.map.layer.domain.core.service.LayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LayerConfig {

    @Bean
    public LayerService layerService() {
        return new LayerService();
    }
}
