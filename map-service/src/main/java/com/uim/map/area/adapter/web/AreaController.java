package com.uim.map.area.adapter.web;

import com.uim.api.area.domain.core.model.Area;
import com.uim.map.api.AreaApi;
import com.uim.map.area.domain.application.port.api.AreaApplicationProcessingService;
import com.uim.map.area.domain.application.port.api.GetAreaByIdUseCase;
import com.uim.map.area.domain.application.port.api.GetAreaByUserIdUseCase;
import com.uim.map.config.security.SecurityUtils;
import com.uim.map.model.AreaDto;
import com.uim.map.model.AreaResponse;
import com.uim.map.model.SelectAreaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AreaController implements AreaApi {

    private final AreaApplicationProcessingService areaApplicationProcessingService;
    private final GetAreaByIdUseCase getAreaByIdUseCase;
    private final GetAreaByUserIdUseCase getAreaByUserIdUseCase;
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

    @Override
    public ResponseEntity<AreaResponse> selectArea(String id, SelectAreaDto selectAreaDto) {
        Area area = getAreaByIdUseCase.getAreaById(UUID.fromString(id));
        area = areaApplicationProcessingService.selectArea(area, selectAreaDto);
        var response = areaMapper.toAreaResponse(area);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AreaResponse>> getAll() {
        UUID userId = SecurityUtils.getCurrentUserId();
        List<Area> areas = getAreaByUserIdUseCase.getByUserId(userId);
        return new ResponseEntity<>(areas.stream().map(areaMapper::toAreaResponse).toList(), HttpStatus.OK);
    }
}
