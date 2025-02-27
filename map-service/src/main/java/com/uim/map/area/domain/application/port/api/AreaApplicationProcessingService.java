package com.uim.map.area.domain.application.port.api;

import com.uim.api.area.domain.core.model.Area;
import com.uim.map.model.AreaDto;
import com.uim.map.model.SelectAreaDto;

public interface AreaApplicationProcessingService {
    Area initializeArea(AreaDto areaDto);

    Area selectArea(Area area, SelectAreaDto selectAreaDto);
}
