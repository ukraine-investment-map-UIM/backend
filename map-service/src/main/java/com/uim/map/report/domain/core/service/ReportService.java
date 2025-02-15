package com.uim.map.report.domain.core.service;

import com.uim.map.area.domain.core.model.Area;
import com.uim.map.infrastructure.calculation.domain.core.model.CalculatedReport;
import com.uim.map.layer.domain.core.model.Layer;
import com.uim.map.report.domain.core.model.PdfInitialization;
import com.uim.map.report.domain.core.model.Report;
import com.uim.map.report.domain.core.model.ReportStatus;
import com.uim.map.report.domain.core.port.spi.AreaProcessingDao;
import com.uim.map.report.domain.core.port.spi.LayerProcessingDao;
import com.uim.map.report.domain.core.port.spi.ReportProcessingDao;

import java.util.List;

public class ReportService {

    private final ReportProcessingDao reportDao;
    private final AreaProcessingDao areaDao;
    private final LayerProcessingDao layerDao;

    public ReportService(ReportProcessingDao reportDao, AreaProcessingDao areaDao, LayerProcessingDao layerDao) {
        this.reportDao = reportDao;
        this.areaDao = areaDao;
        this.layerDao = layerDao;
    }

    public void validatePdfInitializationGeneration(Report report) {
        if (!report.getStatus().equals(ReportStatus.LAYERED)) {
            throw new ReportStatusNotReadyException(report.getSelf());
        }
    }

    public CalculatedReport calculate(Report report, PdfInitialization pdfInitialization) {
        validatePdfInitializationGeneration(report);
        CalculatedReport calculatedReport = new CalculatedReport();
        calculatedReport.setInitializationParameters(pdfInitialization);

        report = reportDao.updateStatus(report, ReportStatus.PENDING);
        calculatedReport.setReport(report);
        calculatedReport.setAreas(getAreasFromReport(report));
        calculatedReport.setLayers(getLayersFromReport(report));

//        TODO calculate each area and layer intersection square

        return calculatedReport;
    }

    private List<Layer> getLayersFromReport(Report report) {
        List<String> layerIds = report.getLayers().stream()
                .map(com.uim.map.report.domain.core.model.Layer::getCode)
                .toList();
        List<Layer> layers = layerDao.findLayersByIds(layerIds);
        if (layers.size() != layerIds.size()) {
            throw new ReportIsBrokenException(report.getSelf(), "there is wrong amount of layers in the report collection");
        }
        return layers;
    }

    private List<Area> getAreasFromReport(Report report) {
        List<String> areaIds = report.getAreas().stream()
                .map(com.uim.map.report.domain.core.model.Area::getCode)
                .toList();
        List<Area> areas = areaDao.findAreasByIds(areaIds);
        if (areas.size() != areaIds.size()) {
            throw new ReportIsBrokenException(report.getSelf(), "there is wrong amount of areas in the report collection");
        }
        return areas;
    }
}
