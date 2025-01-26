package com.uim.map.report.domain.application.port.api;

import com.uim.map.model.ReportDto;
import com.uim.map.report.domain.core.model.Report;

public interface ReportProcessingService {
    Report create(ReportDto reportDto);
}
