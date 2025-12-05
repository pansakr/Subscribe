package com.ex.subscribe.global.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, startTime);

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String handlerInfo = getHandlerInfo(handler);

        log.info("[Request] {} {} {}", method, uri, handlerInfo);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long startTime = (Long) request.getAttribute(START_TIME);
        long duration = (startTime != null) ? System.currentTimeMillis() - startTime : -1;
        String uri = request.getRequestURI();

        if (ex != null){
            log.error("[Response] {} duration={}ms EXCEPTION={}", uri, duration, ex.toString());
        } else{
            log.info("[Response] {} duration={}ms", uri, duration);
        }
    }

    private String getHandlerInfo(Object handler){
        if(handler instanceof HandlerMethod handlerMethod){
            String className = handlerMethod.getBeanType().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();
            return className + "#" + methodName;
        }
        return handler.toString();
    }
}
