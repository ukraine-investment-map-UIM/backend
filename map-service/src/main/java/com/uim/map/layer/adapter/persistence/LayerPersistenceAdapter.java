package com.uim.map.layer.adapter.persistence;

import com.uim.api.layer.domain.core.model.Layer;
import com.uim.map.layer.adapter.persistence.entity.LayerEntity;
import com.uim.map.layer.adapter.persistence.mapper.LayerPersistenceMapper;
import com.uim.map.layer.adapter.persistence.repository.LayerRepository;
import com.uim.map.layer.domain.application.port.spi.LayerDao;
import com.uim.map.model.LayerDto;
import com.uim.map.report.domain.core.port.spi.LayerProcessingDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LayerPersistenceAdapter implements LayerDao, LayerProcessingDao {

    private final LayerRepository layerRepository;
    private final LayerPersistenceMapper layerPersistenceMapper;

    @Override
    public Layer createLayer(LayerDto layerDto) {
        LayerEntity layerEntity = layerPersistenceMapper.toLayerEntity(layerDto);
        layerEntity.setSelf(UUID.randomUUID().toString());
        layerEntity = layerRepository.save(layerEntity);
        return layerPersistenceMapper.toLayer(layerEntity);
    }

    @Override
    public List<Layer> findAll() {
        return layerRepository.findAll().stream()
                .map(layerPersistenceMapper::toLayer)
                .toList();
    }

    @Override
    public Optional<Layer> findById(String id) {
        return layerRepository.findById(id)
                .map(layerPersistenceMapper::toLayer);
    }

    @Override
    public List<Layer> findLayersByIds(List<String> layerIds) {
        return layerRepository.findByIdIn(layerIds).stream()
                .map(layerPersistenceMapper::toLayer)
                .toList();
    }
}
