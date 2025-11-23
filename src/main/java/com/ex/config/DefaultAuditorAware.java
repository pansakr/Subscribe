package com.ex.config;


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class DefaultAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        String username = "system";

        return Optional.of(username);
    }
}
