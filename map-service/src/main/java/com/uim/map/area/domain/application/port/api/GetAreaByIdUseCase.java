package com.uim.map.area.domain.application.port.api;

import com.uim.api.area.domain.core.model.Area;

import java.util.UUID;

public interface GetAreaByIdUseCase {

    Area getAreaById(UUID self);
}
