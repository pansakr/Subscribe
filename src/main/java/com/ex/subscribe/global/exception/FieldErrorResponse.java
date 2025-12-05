package com.ex.subscribe.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FieldErrorResponse {

    private final String field;
    private final String message;
}
