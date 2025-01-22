package com.uim.map.area.adapter.web;

import com.uim.map.area.domain.core.model.Area;
import com.uim.map.model.AreaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaApiMapper {
    AreaResponse toAreaResponse(Area area);
}
