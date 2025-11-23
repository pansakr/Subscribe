package com.ex.subscribe.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserStatus {

    ACTIVE("활동"),
    WITHDRAWAL("탈퇴");

    private final String code;
}
