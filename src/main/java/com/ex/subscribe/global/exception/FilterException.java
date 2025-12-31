package com.ex.subscribe.global.exception;

import lombok.Getter;

@Getter
public class FilterException extends RuntimeException{

    private final BusinessErrorCode errorCode;

    public FilterException(BusinessErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
