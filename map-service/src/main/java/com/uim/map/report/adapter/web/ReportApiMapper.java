package com.uim.map.report.adapter.web;

import com.uim.map.model.ReportDto;
import com.uim.map.model.ReportResponse;
import com.uim.map.report.domain.core.model.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportApiMapper {
    Report toReport(ReportDto reportDto);

    ReportResponse toReportResponse(Report report);
}
