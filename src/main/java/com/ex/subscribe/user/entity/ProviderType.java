package com.ex.subscribe.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum ProviderType {

    GOOGLE("google"),
    NAVER("naver");

    private final String provider;

    /**
     * 매개변수와 문자열이 일치하는 ProviderType(enum) 반환
     */
    public static Optional<ProviderType> find(String registrationId){
        return Arrays.stream(values())
                .filter(p -> p.provider.equalsIgnoreCase(registrationId))
                .findFirst();
    }
}
