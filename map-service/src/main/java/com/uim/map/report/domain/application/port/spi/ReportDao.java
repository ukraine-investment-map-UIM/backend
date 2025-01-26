package com.uim.map.report.domain.application.port.spi;

import com.uim.map.model.ReportDto;
import com.uim.map.report.domain.core.model.Report;

public interface ReportDao {
    Report createReport(ReportDto reportDao);
}
