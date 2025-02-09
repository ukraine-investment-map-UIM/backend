package com.uim.map.report.domain.application.port.api;

import com.uim.map.report.domain.core.model.Report;

import java.util.List;
import java.util.UUID;

public interface ReportGetByUserIdUseCase {

    List<Report> getByUserId(UUID userId);

    Report getById(UUID self);
}
