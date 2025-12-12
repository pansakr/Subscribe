package com.ex.subscribe.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    ADMIN("관리자"),
    USER("회원");

    private final String code;
}
