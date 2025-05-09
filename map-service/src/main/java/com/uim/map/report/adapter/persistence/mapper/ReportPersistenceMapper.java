package com.uim.map.report.adapter.persistence.mapper;

import com.uim.api.report.domain.core.model.Report;
import com.uim.map.model.ReportDto;
import com.uim.map.report.adapter.persistence.entity.ReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReportPersistenceMapper {
    ReportEntity toReportEntity(ReportDto reportDto);

    Report toReport(ReportEntity reportEntity);

    void toReportEntity(@MappingTarget ReportEntity reportEntity, ReportDto updateReportDto);

    ReportEntity toReportEntity(Report report);
}
