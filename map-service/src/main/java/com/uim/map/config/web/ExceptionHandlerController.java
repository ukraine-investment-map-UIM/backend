package com.uim.map.config.web;

import com.uim.map.config.web.ports.output.AppException;
import com.uim.map.config.web.ports.output.ErrorExtension;
import com.uim.map.config.web.ports.output.ValidationErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.uim.map.infrastructure.util.GlobalConstants.INVALID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @Nullable HttpHeaders headers,
                                                                  @Nullable HttpStatusCode status,
                                                                  @Nullable WebRequest request) {
        List<ErrorExtension> errors = new ArrayList<>(ex.getFieldErrorCount());
        ex.getFieldErrors().forEach(fieldError -> errors.add(new ErrorExtension(fieldError.getDefaultMessage().replaceAll("\\{validatedValue\\}",
                fieldError.getField()), INVALID + fieldError.getField())));
        return new ResponseEntity<>(new ValidationErrorResponse(errors), BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorExtension> handleExpectedException(AppException ex) {
        return new ResponseEntity<>(new ErrorExtension(ex.getReason(), ex.getStatus().toString()), ex.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorExtension> handleDataIntegrityException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ErrorExtension(ex.getMessage(), BAD_REQUEST.toString()), BAD_REQUEST);
    }
}