package com.uim.map.layer.domain.application.port.api;

import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.model.LayerDto;

public interface LayerProcessingService {
    Layer create(LayerDto layerDto);
}
