package com.uim.map.area.adapter.persistence;

import com.uim.api.area.domain.core.model.Area;
import com.uim.api.area.domain.core.model.AreaStatus;
import com.uim.map.area.adapter.persistence.entity.AreaEntity;
import com.uim.map.area.adapter.persistence.mapper.AreaPersistenceMapper;
import com.uim.map.area.adapter.persistence.repository.AreaRepository;
import com.uim.map.area.domain.application.port.spi.AreaDao;
import com.uim.map.config.security.SecurityUtils;
import com.uim.map.model.AreaDto;
import com.uim.map.model.Point;
import com.uim.map.model.SelectAreaDto;
import com.uim.map.report.domain.core.port.spi.AreaProcessingDao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AreaPersistenceAdapter implements AreaDao, AreaProcessingDao {

    private final AreaRepository areaRepository;
    private final AreaPersistenceMapper areaMapper;


    @Override
    public Area initializeArea(AreaDto area) {
        AreaEntity areaEntity = areaMapper.toAreaEntity(area);
        areaEntity.setSelf(UUID.randomUUID().toString());
        areaEntity.setUserId(SecurityUtils.getCurrentUserId().toString());
        areaEntity.setStatus(AreaStatus.CREATED);
        areaEntity = areaRepository.save(areaEntity);
        return areaMapper.toArea(areaEntity);
    }

    @Override
    public Optional<Area> findById(String self) {
        return areaRepository.findById(self.toString())
                .map(areaMapper::toArea);
    }

    @Override
    public Area updateAreaCoordinates(Area area, SelectAreaDto selectAreaDto) {
        area.setCoordinates(selectAreaDto.getCords().stream().map((@Valid Point point) -> areaMapper.toPoint(point)).collect(Collectors.toList()));
        if (!area.getStatus().equals(AreaStatus.REPORTED)) {
            area.setStatus(AreaStatus.AREA_SELECTED);
        }
        AreaEntity areaEntity = areaMapper.toAreaEntity(area);
        areaRepository.save(areaEntity);
        return areaMapper.toArea(areaEntity);
    }

    @Override
    public void updateAreaReport(String areaId, String reportId) {
        if (areaRepository.updateAreaReportId(areaId, reportId) == 0L) {
            log.warn("area {} wasn't changed due to existing reportId", areaId);
        }
    }

    @Override
    public List<Area> findAllByUserId(String userId) {
        List<AreaEntity> allByUserId = areaRepository.findAllByUserId(userId);
        return allByUserId.stream().map(areaMapper::toArea).toList();
    }

    @Override
    public List<Area> findAreasByIds(List<String> areaIds) {
        return areaRepository.findByIdIn(areaIds).stream()
                .map(areaMapper::toArea)
                .toList();
    }
}
