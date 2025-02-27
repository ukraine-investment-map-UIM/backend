package com.uim.map.area.domain.core.service;

import com.uim.api.area.domain.core.model.Area;
import com.uim.map.config.security.SecurityUtils;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.UUID;

public class AreaService {
    public void validateSelectedReportValue(@NonNull Area area, UUID reportId) {
        if (Objects.nonNull(area.getReportId()) && Objects.nonNull(reportId)) { // todo remove when report is ready
            throw new IllegalStateException("Report is not allowed to be changed once it has been set");
        }
        if (Objects.isNull(area.getReportId()) && Objects.nonNull(reportId)) {
            area.setReportId(reportId);
        }
    }

    public void validateAccess(Area area) {
        if (!SecurityUtils.getCurrentUserId().equals(area.getUserId())) {
            throw new IllegalStateException("Not allowed to change not your areas");
        }
    }
}
