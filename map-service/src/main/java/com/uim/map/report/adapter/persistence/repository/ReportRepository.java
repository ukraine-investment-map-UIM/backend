package com.uim.map.report.adapter.persistence.repository;

import com.uim.map.report.adapter.persistence.entity.ReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<ReportEntity, String> {
}
