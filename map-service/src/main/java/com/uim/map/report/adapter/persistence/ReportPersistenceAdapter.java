package com.uim.map.report.adapter.persistence;

import com.uim.map.config.security.SecurityUtils;
import com.uim.map.model.ReportDto;
import com.uim.map.report.adapter.persistence.entity.ReportEntity;
import com.uim.map.report.adapter.persistence.mapper.ReportPersistenceMapper;
import com.uim.map.report.adapter.persistence.repository.ReportRepository;
import com.uim.map.report.domain.application.port.spi.ReportDao;
import com.uim.map.report.domain.application.service.ReportNotFoundException;
import com.uim.map.report.domain.core.model.Report;
import com.uim.map.report.domain.core.model.ReportStatus;
import com.uim.map.report.domain.core.port.spi.ReportProcessingDao;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements ReportDao, ReportProcessingDao {

    private final ReportRepository reportRepository;
    private final ReportPersistenceMapper reportPersistenceMapper;

    @Override
    public Report createReport(@NonNull ReportDto reportDto) {
        ReportEntity reportEntity = reportPersistenceMapper.toReportEntity(reportDto);
        reportEntity.setSelf(UUID.randomUUID().toString());
        reportEntity.setUserId(SecurityUtils.getCurrentUserId().toString());
        updateStatus(reportEntity);
        reportEntity = reportRepository.save(reportEntity);

        return reportPersistenceMapper.toReport(reportEntity);
    }

    @Override
    public Report updateReport(Report report, ReportDto updateReportDto) {
        ReportEntity reportEntity = reportRepository.findById(report.getSelf().toString())
                .orElseThrow(() -> new ReportNotFoundException(report.getSelf()));
        reportPersistenceMapper.toReportEntity(reportEntity, updateReportDto);
        updateStatus(reportEntity);
        reportEntity = reportRepository.save(reportEntity);
        return reportPersistenceMapper.toReport(reportEntity);
    }

    @Override
    public Report updateStatus(Report report, ReportStatus reportStatus) {
        ReportEntity reportEntity = reportRepository.findById(report.getSelf().toString())
                .orElseThrow(() -> new ReportNotFoundException(report.getSelf()));
        reportEntity.setStatus(reportStatus);
        reportEntity = reportRepository.save(reportEntity);
        return reportPersistenceMapper.toReport(reportEntity);
    }

    private ReportEntity updateStatus(ReportEntity reportEntity) {
        if (reportEntity.getAreas().isEmpty()) {
            reportEntity.setStatus(ReportStatus.CREATED);
            return reportEntity;
        }
        if (reportEntity.getLayers().isEmpty()) {
            reportEntity.setStatus(ReportStatus.AREAED);
            return reportEntity;
        }
        if (Objects.isNull(reportEntity.getUrl()) || reportEntity.getUrl().isBlank()) {
            reportEntity.setStatus(ReportStatus.LAYERED);
            return reportEntity;
        }
        reportEntity.setStatus(ReportStatus.FORMED);
        return reportEntity;
    }

    @Override
    public List<Report> findAllByUserId(UUID userId) {
        List<ReportEntity> reportEntities = reportRepository.findAllByUserId(userId.toString());
        return reportEntities.stream().map(reportPersistenceMapper::toReport).toList();
    }

    @Override
    public Optional<Report> findById(UUID reportId) {
        return reportRepository.findById(reportId.toString())
                .map(reportPersistenceMapper::toReport);
    }
}
