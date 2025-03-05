package com.uim.map.area.adapter.persistence.mapper;

import com.uim.api.area.domain.core.model.Area;
import com.uim.api.infrastructure.calculation.domain.core.model.Point;
import com.uim.map.area.adapter.persistence.entity.AreaEntity;
import com.uim.map.model.AreaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaPersistenceMapper {
    AreaEntity toAreaEntity(AreaDto area);

    Area toArea(AreaEntity areaEntity);

    AreaEntity toAreaEntity(Area area);

    default Point toPoint(com.uim.map.model.Point point) {
        return new Point(point.getX(), point.getY());
    }

    default org.springframework.data.geo.Point toPoint(Point point) {
        return new org.springframework.data.geo.Point(point.getX(), point.getY());
    }
}
