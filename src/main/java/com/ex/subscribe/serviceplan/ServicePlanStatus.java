package com.ex.subscribe.serviceplan;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ServicePlanStatus {

    ACTIVATION("활성화"),
    DEACTIVATION("비활성화");

    private final String code;
}
