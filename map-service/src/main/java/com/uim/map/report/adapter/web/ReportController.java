package com.uim.map.report.adapter.web;

import com.uim.map.api.ReportApi;
import com.uim.map.config.security.SecurityUtils;
import com.uim.map.model.ReportDto;
import com.uim.map.model.ReportResponse;
import com.uim.map.report.domain.application.port.api.ReportGetByUserIdUseCase;
import com.uim.map.report.domain.application.port.api.ReportProcessingService;
import com.uim.map.report.domain.core.model.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController implements ReportApi {

    private final ReportProcessingService reportProcessingService;
    private final ReportGetByUserIdUseCase reportGetByUserIdUseCase;
    private final ReportApiMapper reportApiMapper;

    @Override
    public ResponseEntity<ReportResponse> create(ReportDto reportDto) {
        Report report = reportProcessingService.create(reportDto);
        ReportResponse reportResponse = reportApiMapper.toReportResponse(report);
        return new ResponseEntity<>(reportResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ReportResponse>> getAll() {
        UUID userId = SecurityUtils.getCurrentUserId();
        List<Report> reports = reportGetByUserIdUseCase.getByUserId(userId);
        return new ResponseEntity<>(reports.stream().map(reportApiMapper::toReportResponse).toList(), HttpStatus.OK);
    }
}
