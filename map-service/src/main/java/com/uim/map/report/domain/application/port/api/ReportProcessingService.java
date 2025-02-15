package com.uim.map.report.domain.application.port.api;

import com.uim.map.model.ReportDto;
import com.uim.map.report.domain.core.model.Report;

import java.util.UUID;

public interface ReportProcessingService {
    Report create(ReportDto reportDto);

    Report updateReportById(UUID reportId, ReportDto updateReportDto);
}
