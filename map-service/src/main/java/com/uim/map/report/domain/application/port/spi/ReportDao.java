package com.uim.map.report.domain.application.port.spi;

import com.uim.api.report.domain.core.model.Report;
import com.uim.map.model.ReportDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportDao {
    Report createReport(ReportDto reportDao);

    List<Report> findAllByUserId(UUID userId);

    Optional<Report> findById(UUID id);

    Report updateReport(Report report, ReportDto updateReportDto);

    Report updateReport(Report report);
}
