package com.uim.map.report.adapter.persistence.mapper;

import com.uim.map.model.ReportDto;
import com.uim.map.report.adapter.persistence.entity.ReportEntity;
import com.uim.map.report.domain.core.model.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportPersistenceMapper {
    ReportEntity toReportEntity(ReportDto reportDto);

    Report toReport(ReportEntity reportEntity);
}
