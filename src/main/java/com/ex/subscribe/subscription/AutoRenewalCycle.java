package com.ex.subscribe.subscription;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AutoRenewalCycle {

    DAY("일"),
    MONTHS("달"),
    YEAR("년");

    private final String code;
}
