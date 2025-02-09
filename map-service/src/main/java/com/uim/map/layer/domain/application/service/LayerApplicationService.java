package com.uim.map.layer.domain.application.service;

import com.uim.map.layer.domain.application.port.api.LayerProcessingService;
import com.uim.map.layer.domain.application.port.spi.LayerDao;
import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.model.LayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LayerApplicationService implements LayerProcessingService {

    private final LayerDao layerDao;

    @Override
    public Layer create(LayerDto layerDto) {
        return layerDao.createLayer(layerDto);
    }
}
