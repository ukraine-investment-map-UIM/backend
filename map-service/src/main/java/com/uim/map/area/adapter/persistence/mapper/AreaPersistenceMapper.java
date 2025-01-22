package com.uim.map.area.adapter.persistence.mapper;

import com.uim.map.area.adapter.persistence.entity.AreaEntity;
import com.uim.map.area.domain.core.model.Area;
import com.uim.map.model.AreaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaPersistenceMapper {
    AreaEntity toAreaEntity(AreaDto area);

    Area toArea(AreaEntity areaEntity);
}
