package com.uim.map.area.domain.application.service;

import com.uim.map.area.domain.core.model.Area;
import com.uim.map.model.AreaDto;

public interface AreaApplicationProcessingService {
    Area initializeArea(AreaDto areaDto);
}
