package com.uim.map.layer.adapter.persistence.repository;

import com.uim.map.layer.adapter.persistence.entity.LayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LayerRepository extends MongoRepository<LayerEntity, String> {
}
