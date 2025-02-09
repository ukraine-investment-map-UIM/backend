package com.uim.map.report.domain.application.service;

import com.uim.map.config.web.ports.output.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.UUID;

public class ReportNotFoundException extends AppException {

    public ReportNotFoundException(String reason, HttpStatusCode status) {
        super(reason, status);
    }

    public ReportNotFoundException(UUID self) {
        this("Report with if=%s could not be found".formatted(self), HttpStatus.NOT_FOUND);
    }
}
