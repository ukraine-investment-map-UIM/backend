package com.uim.map.config.web.ports.output;

import java.util.List;

import static com.uim.map.util.GlobalConstants.VALIDATION_FAILED;


public record ValidationErrorResponse(String errorCode, List<ErrorExtension> errors) {
    public ValidationErrorResponse(List<ErrorExtension> errorExtensions) {
        this(VALIDATION_FAILED, errorExtensions);
    }
}
