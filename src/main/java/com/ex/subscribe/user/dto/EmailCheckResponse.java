package com.ex.subscribe.user.dto;

import lombok.Getter;

@Getter
public class EmailCheckResponse {

    private final boolean available;

    private EmailCheckResponse(boolean available){
        this.available = available;
    }

    public static EmailCheckResponse unavailable(){
        return new EmailCheckResponse(false);
    }

    public static EmailCheckResponse available() {
        return new EmailCheckResponse(true);
    }
}
