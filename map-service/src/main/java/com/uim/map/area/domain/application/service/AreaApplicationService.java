package com.uim.map.area.domain.application.service;

import com.uim.map.area.adapter.persistence.AreaPersistenceAdapter;
import com.uim.map.area.domain.application.port.api.GetAreaByIdUseCase;
import com.uim.map.area.domain.core.model.Area;
import com.uim.map.area.domain.core.service.AreaService;
import com.uim.map.model.AreaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AreaApplicationService implements GetAreaByIdUseCase, AreaApplicationProcessingService {

    private final AreaService areaService;
    private final AreaPersistenceAdapter areaPersistenceAdapter;

    @Override
    public Area initializeArea(AreaDto areaDto) {
        return areaPersistenceAdapter.initializeArea(areaDto);
    }

    @Override
    public Area getAreaById(UUID self) {
        return areaPersistenceAdapter.findById(self.toString())
                .orElseThrow(() -> new AreaNotFoundException(self));
    }
}
