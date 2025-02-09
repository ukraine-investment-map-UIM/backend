package com.uim.map.layer.domain.application.service;

import com.uim.map.config.web.ports.output.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.UUID;

public class LayerNotFoundException extends AppException {
    public LayerNotFoundException(String reason, HttpStatusCode status) {
        super(reason, status);
    }

    public LayerNotFoundException(UUID self) {
        this("Layer with if=%s could not be found".formatted(self), HttpStatus.NOT_FOUND);
    }
}
