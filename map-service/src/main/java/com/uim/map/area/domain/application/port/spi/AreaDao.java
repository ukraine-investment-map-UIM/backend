package com.uim.map.area.domain.application.port.spi;

import com.uim.map.area.domain.core.model.Area;
import com.uim.map.model.AreaDto;
import com.uim.map.model.SelectAreaDto;

import java.util.Optional;

public interface AreaDao {

    Area initializeArea(AreaDto area);

    Optional<Area> findById(String self);

    Area updateAreaCoordinates(Area area, SelectAreaDto selectAreaDto);
}
