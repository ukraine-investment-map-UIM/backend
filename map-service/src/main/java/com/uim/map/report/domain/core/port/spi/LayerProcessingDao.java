package com.uim.map.report.domain.core.port.spi;

import com.uim.api.layer.domain.core.model.Layer;

import java.util.List;

public interface LayerProcessingDao {
    List<Layer> findLayersByIds(List<String> layerIds);
}
