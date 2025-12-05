package com.ex.subscribe.global.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAop {

    @Pointcut("@within(org.springframework.stereotype.Controller) || " +
              "@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerLayer(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceLayer(){}

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public void repositoryLayer(){}

    @Around("controllerLayer() || serviceLayer() || repositoryLayer()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable{

        String className = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        log.info("[Request] {}#{} args={}", className, methodName, args);

        try{
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            log.info("[Response] {}#{} result={} duration={}", className, methodName,
                    result != null ? result.getClass().getName() : "null", duration);

            return result;

        }catch(Throwable ex){

            log.error("[Error] {}#{} ex={}", className, methodName, ex.toString());

            throw ex;
        }

    }
}
