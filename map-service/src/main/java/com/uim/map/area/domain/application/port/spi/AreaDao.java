package com.uim.map.area.domain.application.port.spi;

import com.uim.map.area.domain.core.model.Area;
import com.uim.map.model.AreaDto;

import java.util.Optional;

public interface AreaDao {

    Area initializeArea(AreaDto area);

    Optional<Area> findById(String self);
}
