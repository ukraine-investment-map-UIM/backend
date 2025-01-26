package com.uim.map.area.domain.application.port.api;

import com.uim.map.area.domain.core.model.Area;

import java.util.List;
import java.util.UUID;

public interface GetAreaByUserIdUseCase {
    List<Area> getByUserId(UUID userId);
}
