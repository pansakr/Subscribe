package com.ex.subscribe.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum BusinessErrorCode {

    EMAIL_DUPLICATED("M300", HttpStatus.BAD_REQUEST,"이메일이 중복되었습니다"),
    PHONE_DUPLICATED("M300", HttpStatus.BAD_REQUEST,"휴대폰 번호가 중복되었습니다"),
    INVALID_INPUT_VALUE("U300", HttpStatus.BAD_REQUEST, "요청 값이 올바르지 않습니다"),
    INVALID_PATH("U350", HttpStatus.NOT_FOUND, "요청하신 경로를 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR("U400", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
