package com.uim.map.area.adapter.web;

import com.uim.map.api.AreaApi;
import com.uim.map.area.domain.application.port.api.GetAreaByIdUseCase;
import com.uim.map.area.domain.application.service.AreaApplicationProcessingService;
import com.uim.map.area.domain.core.model.Area;
import com.uim.map.model.AreaDto;
import com.uim.map.model.AreaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AreaController implements AreaApi {

    private final AreaApplicationProcessingService areaApplicationProcessingService;
    private final GetAreaByIdUseCase getAreaByIdUseCase;
    private final AreaApiMapper areaMapper;


    @Override
    public ResponseEntity<AreaResponse> initializeArea() {
        AreaDto areaDto = new AreaDto();
        Area area = areaApplicationProcessingService.initializeArea(areaDto);
        AreaResponse response = areaMapper.toAreaResponse(area);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AreaResponse> findAreaById(String id) {
        Area area = getAreaByIdUseCase.getAreaById(UUID.fromString(id));
        var response = areaMapper.toAreaResponse(area);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
