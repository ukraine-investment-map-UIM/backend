package com.uim.map.report.domain.core.port.spi;

import com.uim.api.report.domain.core.model.Report;
import com.uim.api.report.domain.core.model.ReportStatus;

public interface ReportProcessingDao {
    Report updateStatus(Report report, ReportStatus reportStatus);
}
