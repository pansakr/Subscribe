package com.ex.subscribe.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 404 전용 예외 처리 클래스
 * 404 는 매칭된 컨트롤러를 찾지 못했을때 발생한 예외이므로 annotations = .. 옵션이 있는 예외 핸들러는 건너뜀
 */
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
