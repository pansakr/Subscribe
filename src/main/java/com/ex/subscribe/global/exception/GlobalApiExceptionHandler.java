package com.ex.subscribe.global.exception;

import com.ex.subscribe.user.UserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;


@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){

        List<FieldErrorResponse> fieldErrors = e.getBindingResult().getFieldErrors()
                                            .stream()
                                            .map(error -> new FieldErrorResponse(
                                                    error.getField(),
                                                    error.getDefaultMessage()
                                            ))
                                            .toList();

        return ResponseEntity
                .status(e.getStatusCode())
                .body(ErrorResponse.of(BusinessErrorCode.INVALID_INPUT_VALUE, request.getRequestURI(), fieldErrors));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handlerMethodValidationException(HandlerMethodValidationException e, HttpServletRequest request){

        List<? extends MessageSourceResolvable> errors = e.getAllErrors();

        List<FieldErrorResponse> fieldErrors = errors.stream()
                .map(error -> new FieldErrorResponse(
                        "email",
                        error.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity
                .status(e.getStatusCode())
                .body(ErrorResponse.of(BusinessErrorCode.INVALID_INPUT_VALUE, request.getRequestURI(), fieldErrors));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> userException(UserException e, HttpServletRequest request){

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode(), request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e, HttpServletRequest request){

        log.error("[Exception] {} {} {}", request.getMethod(), request.getRequestURI(), e.getMessage(), e);

        return ResponseEntity
                .status(BusinessErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ErrorResponse.of(BusinessErrorCode.INTERNAL_SERVER_ERROR, request.getRequestURI()));
    }

}
