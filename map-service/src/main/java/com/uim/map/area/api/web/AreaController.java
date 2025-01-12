package com.uim.map.area.api.web;

import com.uim.map.area.api.ports.input.AreaDto;
import com.uim.map.area.core.domain.Area;
import com.uim.map.area.integration.AreaApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/area")
@RequiredArgsConstructor
public class AreaController {

    private final AreaApplicationService areaApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Area initialize(@RequestBody AreaDto areaDto) {
        return null;
    }
}
