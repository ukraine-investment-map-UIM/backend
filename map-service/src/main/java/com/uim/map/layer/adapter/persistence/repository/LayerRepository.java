package com.uim.map.layer.adapter.persistence.repository;

import com.uim.map.layer.adapter.persistence.entity.LayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LayerRepository extends MongoRepository<LayerEntity, String> {

    @Query(value = "{}")
    List<LayerEntity> findAll();
}
