package com.uim.map.report.adapter.persistence;

import com.uim.map.config.security.SecurityUtils;
import com.uim.map.model.ReportDto;
import com.uim.map.report.adapter.persistence.entity.ReportEntity;
import com.uim.map.report.adapter.persistence.mapper.ReportPersistenceMapper;
import com.uim.map.report.adapter.persistence.repository.ReportRepository;
import com.uim.map.report.domain.application.port.spi.ReportDao;
import com.uim.map.report.domain.core.model.Report;
import com.uim.map.report.domain.core.model.ReportStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements ReportDao {

    private final ReportRepository reportRepository;
    private final ReportPersistenceMapper reportPersistenceMapper;

    @Override
    public Report createReport(@NonNull ReportDto reportDto) {
        ReportEntity reportEntity = reportPersistenceMapper.toReportEntity(reportDto);
        reportEntity.setSelf(UUID.randomUUID().toString());
        reportEntity.setStatus(ReportStatus.PENDING);
        reportEntity.setUserId(SecurityUtils.getCurrentUserId().toString());
        reportEntity = reportRepository.save(reportEntity);

        return reportPersistenceMapper.toReport(reportEntity);
    }
}
