package com.uim.map.report.domain.core.service;

import com.uim.map.config.web.ports.output.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.UUID;

public class ReportIsBrokenException extends AppException {
    public ReportIsBrokenException(String reason, HttpStatusCode status) {
        super(reason, status);
    }

    public ReportIsBrokenException(UUID reportId, String message) {
        this("Report %s has following error message: %s".formatted(reportId, message), HttpStatus.BAD_REQUEST);
    }
}
