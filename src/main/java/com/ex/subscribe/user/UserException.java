package com.ex.subscribe.user;

import com.ex.subscribe.global.exception.BusinessErrorCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final BusinessErrorCode errorCode;

    public UserException(BusinessErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}