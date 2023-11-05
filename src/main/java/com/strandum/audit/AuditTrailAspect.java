package com.strandum.audit;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class AuditTrailAspect {
    @Before("execution(* com.strandum.services.*.*(..))")
    public void setAuditFields(JoinPoint joinPoint) {
        log.info("Method called: " + joinPoint.getSignature().getName());
    }
}