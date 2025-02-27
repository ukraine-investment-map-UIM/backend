package com.uim.map.report.domain.application.service;

import com.uim.map.area.domain.application.port.spi.AreaDao;
import com.uim.api.infrastructure.calculation.domain.core.model.CalculatedReport;
import com.uim.map.model.ReportDto;
import com.uim.map.report.domain.application.port.api.ReportGetByUserIdUseCase;
import com.uim.map.report.domain.application.port.api.ReportProcessingService;
import com.uim.map.report.domain.application.port.spi.EventPublisher;
import com.uim.map.report.domain.application.port.spi.ReportDao;
import com.uim.api.report.domain.core.model.PdfInitialization;
import com.uim.api.report.domain.core.model.Report;
import com.uim.map.report.domain.core.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportApplicationService implements
        ReportProcessingService,
        ReportGetByUserIdUseCase {

    private final ReportDao reportDao;
    private final AreaDao areaDao;
    private final ReportService reportService;
    private final EventPublisher<CalculatedReport> eventPublisher;

    @Value("${spring.kafka.topics.pdf-generation-request}")
    private String pdfGenerationTopic;

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
        report.getAreas().forEach(area -> areaDao.updateAreaReport(area.getCode(), report.getSelf().toString()));
    }

    @Override
    public Report updateReportById(UUID reportId, ReportDto updateReportDto) {
        Report report = getById(reportId);
        report = reportDao.updateReport(report, updateReportDto);
        return report;
    }

    @Override
    public Report initiatePdfGeneration(UUID reportId, PdfInitialization pdfInitialization) {
        Report report = getById(reportId);
        // todo calculate
        CalculatedReport calculatedReport = reportService.calculate(report, pdfInitialization);
        // todo send send kafka message
        eventPublisher.publish(calculatedReport.getReport().getSelf().toString(), calculatedReport, pdfGenerationTopic);
        return calculatedReport.getReport();
    }

    @Override
    public List<Report> getByUserId(UUID userId) {
        return reportDao.findAllByUserId(userId);
    }

    @Override
    public Report getById(UUID self) {
        return reportDao.findById(self)
                .orElseThrow(() -> new ReportNotFoundException(self));
    }
}
