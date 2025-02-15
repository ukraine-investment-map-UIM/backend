package com.uim.map.report.domain.core.port.spi;

import com.uim.map.area.domain.core.model.Area;

import java.util.List;

public interface AreaProcessingDao {
    List<Area> findAreasByIds(List<String> areaIds);
}
