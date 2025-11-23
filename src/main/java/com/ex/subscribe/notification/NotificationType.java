package com.ex.subscribe.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NotificationType {

    SMS("문자"),
    CACAO("카카오톡");

    private final String code;
}
