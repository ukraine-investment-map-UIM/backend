package com.uim.map.report.adapter.persistence.repository;

import com.uim.map.report.adapter.persistence.entity.ReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReportRepository extends MongoRepository<ReportEntity, String> {

    @Query("""
        { "userId": ?0 }
        """)
    List<ReportEntity> findAllByUserId(String string);
}
