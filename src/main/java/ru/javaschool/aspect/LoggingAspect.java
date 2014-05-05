package ru.javaschool.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Annotated class for logging
 */
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * This vethod implements logging action before methods, which are included in the packages
     * added to the execution..
     *
     * @param point - where to join
     */
    @Before("execution(* ru.javaschool.controller..*.*(..))" + " || execution(* ru.javaschool.dao..*.*(..))" + " || execution(* ru.javaschool.services..*.*(..))")
    public void logBefore(JoinPoint point) {
        Object[] args = point.getArgs();
        StringBuilder loggerStr = new StringBuilder();
        loggerStr.append(point.getSignature().getDeclaringTypeName()).append(".").append(point.getSignature().getName()).append(" its args(");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                loggerStr.append(", ");
            }
            loggerStr.append(args[i]);
        }
        loggerStr.append(")");
        logger.info(loggerStr);
    }

    /**
     * This vethod implements logging action after returning result methods, which are included in the packages
     * added to the execution..
     *
     * @param point - where to join
     */
    @AfterReturning(pointcut = "execution(* ru.javaschool.controller..*.*(..))" + " || execution(* ru.javaschool.dao..*.*(..))" + " || execution(* ru.javaschool.services..*.*(..))",
            returning = "returnValue")
    public void logAfter(JoinPoint point, Object returnValue) {
        StringBuilder loggerStr = new StringBuilder();
        loggerStr.append(point.getSignature().getDeclaringTypeName()).append(".").append(point.getSignature().getName()).append(" returned ");
        loggerStr.append(returnValue);
        logger.info(loggerStr);
    }

    @Pointcut("execution(public * *Throwable(..))")
    void throwableMethod() {}

    /**
     * For exception handling
     * @param pjp - join point
     */
    @Around("throwableMethod()")
    public void swallowThrowing(ProceedingJoinPoint pjp) {
        try {
            pjp.proceed();
        } catch (Throwable e) {
            logger.debug("swallow " + e.toString());
        }
    }


}
