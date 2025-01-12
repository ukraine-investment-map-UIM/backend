package com.uim.map.area.infrastructure;

import com.uim.map.area.integration.model.AreaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AreaRepository extends MongoRepository<AreaEntity, UUID> {

}
