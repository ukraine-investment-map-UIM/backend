package com.uim.map.report.domain.application.service;

import com.uim.map.area.domain.application.port.spi.AreaDao;
import com.uim.map.model.ReportDto;
import com.uim.map.report.domain.application.port.api.ReportProcessingService;
import com.uim.map.report.domain.application.port.spi.ReportDao;
import com.uim.map.report.domain.core.model.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportApplicationService implements ReportProcessingService {

    private final ReportDao reportDao;
    private final AreaDao areaDao;

    @Override
    public Report create(ReportDto reportDto) {
        if (Objects.isNull(reportDto)) {
            reportDto = new ReportDto();
        }
        Report report = reportDao.createReport(reportDto);
        assignAreasWithReportId(report);
        return report;
    }

    private void assignAreasWithReportId(Report report) {
        if (Objects.isNull(report.getAreas())) {
            return;
        }
        report.getAreas().forEach(area -> areaDao.updateAreaReport(area.getCode(), report.getSelf()));
    }
}
