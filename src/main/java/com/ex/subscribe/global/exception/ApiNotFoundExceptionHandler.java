package com.ex.subscribe.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class ApiNotFoundExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> noResourceFoundException(NoResourceFoundException e,
                                                                  HttpServletRequest request) throws NoResourceFoundException {

        if (!request.getRequestURI().startsWith("/api"))
            throw e;

        return ResponseEntity
                .status(e.getStatusCode())
                .body(ErrorResponse.of(BusinessErrorCode.INVALID_PATH, request.getRequestURI()));
    }
}
