package com.uim.map.area.adapter.persistence.mapper;

import com.uim.map.area.adapter.persistence.entity.AreaEntity;
import com.uim.map.area.domain.core.model.Area;
import com.uim.map.model.AreaDto;
import com.uim.map.model.Point;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaPersistenceMapper {
    AreaEntity toAreaEntity(AreaDto area);

    Area toArea(AreaEntity areaEntity);

    AreaEntity toAreaEntity(Area area);

    default org.springframework.data.geo.Point toPoint(Point point) {
        return new org.springframework.data.geo.Point(point.getX(), point.getY());
    }
}
