package com.ex.subscribe.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class GlobalViewExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exception(Exception e, HttpServletRequest request){

      log.error("[Exception] {} {} {} ", request.getMethod(), request.getRequestURI(), e.getMessage(), e);

      return "error/5xx";
    }
}
