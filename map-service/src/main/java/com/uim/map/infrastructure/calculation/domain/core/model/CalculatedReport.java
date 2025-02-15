package com.uim.map.infrastructure.calculation.domain.core.model;

import com.uim.map.area.domain.core.model.Area;
import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.report.domain.core.model.PdfInitialization;
import com.uim.map.report.domain.core.model.Report;

import java.util.List;

public class CalculatedReport {
    private Report report;
    private List<Area> areas;
    private List<Layer> layers;
    private PdfInitialization initializationParameters;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
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

    public PdfInitialization getInitializationParameters() {
        return initializationParameters;
    }

    public void setInitializationParameters(PdfInitialization initializationParameters) {
        this.initializationParameters = initializationParameters;
    }

    @Override
    public String toString() {
        return "CalculatedReport{" +
                "report=" + report +
                ", areas=" + areas +
                ", layers=" + layers +
                ", initializationParameters=" + initializationParameters +
                '}';
    }
}
