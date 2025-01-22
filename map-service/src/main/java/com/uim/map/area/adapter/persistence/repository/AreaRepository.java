package com.uim.map.area.adapter.persistence.repository;

import com.uim.map.area.adapter.persistence.entity.AreaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AreaRepository extends MongoRepository<AreaEntity, String> {

}
