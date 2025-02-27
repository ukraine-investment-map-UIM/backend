package com.uim.map.area.domain.application.service;

import com.uim.map.area.adapter.persistence.AreaPersistenceAdapter;
import com.uim.map.area.domain.application.port.api.AreaApplicationProcessingService;
import com.uim.map.area.domain.application.port.api.GetAreaByIdUseCase;
import com.uim.map.area.domain.application.port.api.GetAreaByUserIdUseCase;
import com.uim.api.area.domain.core.model.Area;
import com.uim.map.area.domain.core.service.AreaService;
import com.uim.map.config.web.ports.output.AppException;
import com.uim.map.model.AreaDto;
import com.uim.map.model.SelectAreaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AreaApplicationService implements
        GetAreaByIdUseCase,
        AreaApplicationProcessingService,
        GetAreaByUserIdUseCase {

    private final AreaService areaService;
    private final AreaPersistenceAdapter areaPersistenceAdapter;

    @Override
    public Area initializeArea(AreaDto areaDto) {
        return areaPersistenceAdapter.initializeArea(areaDto);
    }

    @Override
    public Area selectArea(Area area, SelectAreaDto selectAreaDto) {
        try {
            areaService.validateAccess(area); // todo mode to annotation & aspect
            areaService.validateSelectedReportValue(area, selectAreaDto.getReportId());
        } catch (IllegalStateException e) {
            throw new AppException(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return areaPersistenceAdapter.updateAreaCoordinates(area, selectAreaDto);
    }

    @Override
    public Area getAreaById(UUID self) {
        return areaPersistenceAdapter.findById(self.toString())
                .orElseThrow(() -> new AreaNotFoundException(self));
    }

    @Override
    public List<Area> getByUserId(UUID userId) {
        return areaPersistenceAdapter.findAllByUserId(userId.toString());
    }
}
