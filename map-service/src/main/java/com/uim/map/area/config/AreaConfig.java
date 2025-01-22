package com.uim.map.area.config;

import com.uim.map.area.domain.core.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AreaConfig {

    @Bean
    public AreaService areaService() {
        return new AreaService();
    }
}
