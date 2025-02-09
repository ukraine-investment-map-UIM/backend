package com.uim.map.layer.adapter.web;

import com.uim.map.api.LayersApi;
import com.uim.map.layer.domain.application.port.api.LayerProcessingService;
import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.model.LayerDto;
import com.uim.map.model.LayerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LayerController implements LayersApi {

    private final LayerApiMapper layerApiMapper;
    private final LayerProcessingService layerProcessingService;

    @Override
    public ResponseEntity<LayerResponse> createLayer(LayerDto layerDto) {
        if (Objects.isNull(layerDto.getCoordinates())) {
            layerDto.setCoordinates(new ArrayList<>());
        }
        Layer layer = layerProcessingService.create(layerDto);
        return new ResponseEntity<>(layerApiMapper.toLayerResponse(layer), HttpStatus.CREATED);
    }
}
