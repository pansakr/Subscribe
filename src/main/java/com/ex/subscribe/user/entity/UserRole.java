package com.ex.subscribe.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    ROLE_ADMIN("관리자"),
    ROLE_USER("회원");

    private final String code;
}
