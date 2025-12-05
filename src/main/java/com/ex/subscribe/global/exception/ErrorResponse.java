package com.ex.subscribe.global.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int httpStatus;
    private final String error;
    private final String code;
    private final String message;
    private final String path;
    private final List<FieldErrorResponse> fieldErrors;

    public static ErrorResponse of(BusinessErrorCode errorCode, String path) {
        return new ErrorResponse(errorCode, path);
    }

    public static ErrorResponse of(BusinessErrorCode errorCode, String path, List<FieldErrorResponse> fieldErrors) {
        return new ErrorResponse(errorCode, path, fieldErrors);
    }

    private ErrorResponse(BusinessErrorCode errorCode, String path){
        this(errorCode, path, null);
    }

    private ErrorResponse(BusinessErrorCode errorCode, String path, List<FieldErrorResponse> fieldErrors){
        this.timestamp = LocalDateTime.now();
        this.httpStatus = errorCode.getHttpStatus().value();
        this.error = errorCode.getHttpStatus().getReasonPhrase();
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

}
