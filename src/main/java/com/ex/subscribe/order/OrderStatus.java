package com.ex.subscribe.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    COMPLETED("완료"),
    CANCELED("취소"),
    PARTIAL_CANCEL("부분취소"),
    REFUNDED("환불");

    private final String code;
}
