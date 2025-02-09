package com.uim.map.layer.domain.application.port.spi;

import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.model.LayerDto;

public interface LayerDao {
    Layer createLayer(LayerDto layerDto);
}
