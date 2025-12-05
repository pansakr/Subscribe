package com.ex.subscribe.global.exception;

import com.ex.subscribe.user.UserException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validException(MethodArgumentNotValidException e, HttpServletRequest request){

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

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> userException(UserException e, HttpServletRequest request){

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode(), request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(BusinessErrorCode.INVALID_INPUT_VALUE, request.getRequestURI()));
    }

}
