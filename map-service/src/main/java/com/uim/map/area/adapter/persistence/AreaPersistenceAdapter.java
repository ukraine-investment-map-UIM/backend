package com.uim.map.area.adapter.persistence;

import com.uim.map.area.adapter.persistence.entity.AreaEntity;
import com.uim.map.area.adapter.persistence.mapper.AreaPersistenceMapper;
import com.uim.map.area.adapter.persistence.repository.AreaRepository;
import com.uim.map.area.domain.application.port.spi.AreaDao;
import com.uim.map.area.domain.core.model.Area;
import com.uim.map.area.domain.core.model.AreaStatus;
import com.uim.map.config.security.SecurityUtils;
import com.uim.map.model.AreaDto;
import com.uim.map.model.SelectAreaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AreaPersistenceAdapter implements AreaDao {

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
        area.setCoordinates(selectAreaDto.getCords().stream().map(areaMapper::toPoint).collect(Collectors.toList()));
        if (!area.getStatus().equals(AreaStatus.REPORTED)) {
            area.setStatus(AreaStatus.AREA_SELECTED);
        }
        AreaEntity areaEntity = areaMapper.toAreaEntity(area);
        areaRepository.save(areaEntity);
        return areaMapper.toArea(areaEntity);
    }
}
