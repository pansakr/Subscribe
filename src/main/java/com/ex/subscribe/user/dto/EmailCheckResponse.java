package com.ex.subscribe.user.dto;

import lombok.Getter;

@Getter
public class EmailCheckResponse {

    private final boolean exists;

    private EmailCheckResponse(boolean exists){
        this.exists = exists;
    }

    public static EmailCheckResponse success(){
        return new EmailCheckResponse(false);
    }

    public static EmailCheckResponse fail() {
        return new EmailCheckResponse(true);
    }
}
