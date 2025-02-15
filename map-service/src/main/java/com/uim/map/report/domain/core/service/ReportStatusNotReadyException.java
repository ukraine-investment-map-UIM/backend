package com.uim.map.report.domain.core.service;

import com.uim.map.config.web.ports.output.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.UUID;

public class ReportStatusNotReadyException extends AppException {
    public ReportStatusNotReadyException(String reason, HttpStatusCode status) {
        super(reason, status);
    }

    public ReportStatusNotReadyException(UUID reportId) {
        this("Cannot initialize prd report generation due to wrong status of the report with id=%s".formatted(reportId),
                HttpStatus.BAD_REQUEST);
    }
}
