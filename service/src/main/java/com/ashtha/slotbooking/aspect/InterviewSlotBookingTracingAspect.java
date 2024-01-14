package com.ashtha.slotbooking.aspect;

import com.ashtha.slotbooking.logger.CommonTraceLoggerAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InterviewSlotBookingTracingAspect extends CommonTraceLoggerAspect {

    @Pointcut("execution(* com.ashtha.slotbooking.controller..*(..)))")
    public void slotBookingControllerAspect() {}

    @Pointcut("execution(* com.ashtha.slotbooking.service..*.*(..)))")
    public void slotBookingServiceTrace() {}

    @Around("slotBookingControllerAspect()")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return trace(joinPoint);
    }

    @Around("slotBookingControllerAspect()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        return trace(joinPoint);
    }

}
