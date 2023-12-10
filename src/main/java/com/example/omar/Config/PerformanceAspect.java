package com.example.omar.Config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Component
@Aspect
public class PerformanceAspect {
    private static final Logger logger = LogManager.getLogger(PerformanceAspect.class);

    @Around("execution(* com.example.omar.Service.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;

        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();

        logger.info("Method " + className + "." + methodName + " execution time: " + elapsedTime + " milliseconds.");

        return result;
    }

}
