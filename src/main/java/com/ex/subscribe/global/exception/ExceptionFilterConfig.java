package com.ex.subscribe.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class ExceptionFilterConfig {

    @Bean
    public FilterRegistrationBean<ApiGlobalExceptionFilter> apiGlobalExceptionFilter(ObjectMapper objectMapper){

        FilterRegistrationBean<ApiGlobalExceptionFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new ApiGlobalExceptionFilter(objectMapper));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
