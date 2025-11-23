package com.ex.subscribe.orderitem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderItemStatus {

    COMPLETED("완료"),
    CANCELED("취소"),
    REFUNDED("환불");

    private final String code;
}
