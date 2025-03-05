package com.uim.map.layer.domain.application.service;

import com.uim.api.layer.domain.core.model.Layer;
import com.uim.map.layer.domain.application.port.api.GetLayerByIdUseCase;
import com.uim.map.layer.domain.application.port.api.LayerProcessingService;
import com.uim.map.layer.domain.application.port.spi.LayerDao;
import com.uim.map.model.LayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LayerApplicationService implements
        LayerProcessingService,
        GetLayerByIdUseCase {

    private final LayerDao layerDao;

    @Override
    public Layer create(LayerDto layerDto) {
        return layerDao.createLayer(layerDto);
    }

    @Override
    public List<Layer> findAll() {
        return layerDao.findAll();
    }

    @Override
    public Layer findById(UUID id) {
        return layerDao.findById(id.toString())
                .orElseThrow(() -> new LayerNotFoundException(id));
    }
}
