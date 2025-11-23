package com.ex.subscribe.subscription;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SubscriptionStatus {

    ACTIVE("활성화"),
    CANCELLED("취소"),
    EXPIRED("만료");

    private final String code;
}
