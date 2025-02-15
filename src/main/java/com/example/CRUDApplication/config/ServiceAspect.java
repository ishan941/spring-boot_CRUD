package com.example.CRUDApplication.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

      private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    // Logging before method execution
    @Before(value = "execution(* com.example.CRUDApplication.controller.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        logger.info("Method called: " + joinPoint.getSignature().toShortString());
        // Optionally log additional info like method arguments, user info, etc.
    }

    // Logging after method execution
    @After(value = "execution(* com.example.CRUDApplication.controller.*.*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        logger.info("Method executed: " + joinPoint.getSignature().toShortString());
        // Optionally log additional info like status, response time, etc.
    }

    // Measure the execution time using @Around
    @Around(value = "execution(* com.example.CRUDApplication.controller.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Proceed with the method execution
        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logger.info("Method execution time: " + (endTime - startTime) + " ms for " + proceedingJoinPoint.getSignature().toShortString());

        return result;
    }

    // Log the return values of methods using @AfterReturning
    @AfterReturning(value = "execution(* com.example.CRUDApplication.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method executed successfully: " + joinPoint.getSignature().toShortString());
        logger.info("Return value: " + result);
        // Optionally log successful responses or status code
    }

    // Log exceptions thrown by methods using @AfterThrowing
    @AfterThrowing(value = "execution(* com.example.CRUDApplication.controller.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception thrown in method: " + joinPoint.getSignature().toShortString(), exception);
        // Optionally log the exception message, user ID, etc.
    }

    
}
