package com.uim.map.layer.adapter.persistence.mapper;

import com.uim.map.layer.adapter.persistence.entity.LayerEntity;
import com.uim.api.layer.domain.core.model.Layer;
import com.uim.map.model.LayerDto;
import com.uim.map.model.Point;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LayerPersistenceMapper {
    LayerEntity toLayerEntity(LayerDto layerDto);

    Layer toLayer(LayerEntity layerEntity);

    default org.springframework.data.geo.Point toPoint(Point point) {
        return new org.springframework.data.geo.Point(point.getX(), point.getY());
    }
}
