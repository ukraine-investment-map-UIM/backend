package com.uim.map.layer.adapter.persistence;

import com.uim.map.layer.adapter.persistence.entity.LayerEntity;
import com.uim.map.layer.adapter.persistence.mapper.LayerPersistenceMapper;
import com.uim.map.layer.adapter.persistence.repository.LayerRepository;
import com.uim.map.layer.domain.application.port.spi.LayerDao;
import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.model.LayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LayerPersistenceAdapter implements LayerDao {

    private final LayerRepository layerRepository;
    private final LayerPersistenceMapper layerPersistenceMapper;

    @Override
    public Layer createLayer(LayerDto layerDto) {
        LayerEntity layerEntity = layerPersistenceMapper.toLayerEntity(layerDto);
        layerEntity.setSelf(UUID.randomUUID().toString());
        layerEntity = layerRepository.save(layerEntity);
        return layerPersistenceMapper.toLayer(layerEntity);
    }
}
