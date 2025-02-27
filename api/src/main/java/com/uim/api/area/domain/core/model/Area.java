package com.uim.api.area.domain.core.model;

import com.uim.api.infrastructure.calculation.domain.core.model.Point;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Area {
    private UUID self;
    private UUID userId;
    private List<Point> coordinates;
    private UUID reportId;
    private AreaStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Objects.equals(self, area.self);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self);
    }

    public UUID getSelf() {
        return self;
    }

    public void setSelf(UUID self) {
        this.self = self;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public UUID getReportId() {
        return reportId;
    }

    public void setReportId(UUID reportId) {
        this.reportId = reportId;
    }

    public AreaStatus getStatus() {
        return status;
    }

    public void setStatus(AreaStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Area{" +
                "self=" + self +
                ", userId=" + userId +
                ", coordinates=" + coordinates +
                ", reportId=" + reportId +
                ", status=" + status +
                '}';
    }
}
