package com.uim.map.area.domain.application.service;

import com.uim.map.config.web.ports.output.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.UUID;

public class AreaNotFoundException extends AppException {

    public AreaNotFoundException(String reason, HttpStatusCode status) {
        super(reason, status);
    }

    public AreaNotFoundException(UUID self) {
        this("Area with if=%s could not be found".formatted(self), HttpStatus.NOT_FOUND);
    }
}
