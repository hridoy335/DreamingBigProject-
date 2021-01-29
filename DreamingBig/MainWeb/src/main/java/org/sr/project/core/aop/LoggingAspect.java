package org.sr.project.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class LoggingAspect {

    //region PRIVATE VARIABLES
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    //endregion

    //region PUBLIC METHODS
    @Around("execution(* org.src.project.*.*.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
    String className  = proceedingJoinPoint.getTarget().getClass().getName();
    String methodName = proceedingJoinPoint.getSignature().getName();
    String fullName = className + " : " + methodName;
    LOGGER.info("Going into {}",fullName);
    long startTime = System.currentTimeMillis();
    Object object = proceedingJoinPoint.proceed();
    long endTime = System.currentTimeMillis();
    long executionTime  = endTime-startTime;
        LOGGER.info("Left out from {} and time taken {} ms", fullName,executionTime);
    long maximumExecutionTime  = 20000; //20 miliseconds
    if(maximumExecutionTime <executionTime){
        LOGGER.info("Take a look into {}",fullName);
    }
    return object;
    }
    //endregion

}
