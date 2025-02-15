package com.uim.map.report.domain.core.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Report {
    private UUID self;
    private List<Area> areas;
    private UUID userId;
    private List<Layer> layers;
    private String url;
    private ReportStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(self, report.self);
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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "self=" + self +
                ", areas=" + areas +
                ", userId=" + userId +
                ", layers=" + layers +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}
