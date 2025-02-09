package com.uim.map.layer.domain.application.port.api;

import com.uim.map.layer.domain.core.model.Layer;

import java.util.List;
import java.util.UUID;

public interface GetLayerByIdUseCase {
    List<Layer> findAll();

    Layer findById(UUID uuid);
}
