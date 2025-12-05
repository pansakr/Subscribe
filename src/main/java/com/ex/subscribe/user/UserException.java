package com.ex.subscribe.user;

import com.ex.subscribe.global.exception.BusinessErrorCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final BusinessErrorCode errorCode;

    public UserException(BusinessErrorCode errorCode){
        this.errorCode = errorCode;
    }

}
