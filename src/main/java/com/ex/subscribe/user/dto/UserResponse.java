package com.ex.subscribe.user.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private final String email;
    private final String name;
    private final String address;
    private final String phone;

    public UserResponse(String email, String name, String address, String phone){
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

}
