package com.ex.subscribe.global.exception;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class FilterExceptionConfig {

    @Bean
    public FilterRegistrationBean<ApiExceptionFilter> apiGlobalExceptionFilter(HandlerExceptionResolver handlerExceptionResolver){

        FilterRegistrationBean<ApiExceptionFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new ApiExceptionFilter(handlerExceptionResolver));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
