package com.uim.map.layer.domain.application.port.spi;

import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.model.LayerDto;

import java.util.List;
import java.util.Optional;

public interface LayerDao {
    Layer createLayer(LayerDto layerDto);

    List<Layer> findAll();

    Optional<Layer> findById(String id);
}
