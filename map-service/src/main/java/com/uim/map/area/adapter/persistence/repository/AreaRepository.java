package com.uim.map.area.adapter.persistence.repository;

import com.uim.map.area.adapter.persistence.entity.AreaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface AreaRepository extends MongoRepository<AreaEntity, String> {

    @Query("""
            { "_id": ?0, "reportId": null }
            """)
    @Update("""
            { "$set": { "reportId": ?1 }}
            """)
    long updateAreaReportId(String areaId, String reportId);

    @Query("""
            { "userId": ?0 }
            """)
    List<AreaEntity> findAllByUserId(String userId);
}
