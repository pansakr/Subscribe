package com.ex.subscribe.user.dto;

import lombok.Getter;

@Getter
public class EmailCheckResponse {

    private final boolean duplicate;

    private EmailCheckResponse(boolean duplicate){
        this.duplicate = duplicate;
    }

    public static EmailCheckResponse success(){
        return new EmailCheckResponse(false);
    }

}
