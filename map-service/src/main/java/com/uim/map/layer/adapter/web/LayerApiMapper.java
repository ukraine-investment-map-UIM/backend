package com.uim.map.layer.adapter.web;

import com.uim.api.layer.domain.core.model.Layer;
import com.uim.map.model.LayerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LayerApiMapper {
    LayerResponse toLayerResponse(Layer layer);
}
