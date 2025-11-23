package com.ex.subscribe.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    ADMIN("관리자"),
    MEMBER("회원");

    private final String code;
}
