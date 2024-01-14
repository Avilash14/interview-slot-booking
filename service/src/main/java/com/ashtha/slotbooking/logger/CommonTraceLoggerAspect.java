package com.ashtha.slotbooking.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public abstract class CommonTraceLoggerAspect extends BaseLoggerAspect {


    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestData = formatRequestParameters(joinPoint);
        log.info(String.format("Invoking : %s", requestData));

        Object response;

        try {
            response = joinPoint.proceed();
            String jsonResponse = serializeResponseToJson(joinPoint, response);
            String formattedResponse = String.format("Invocation Returned: %s", jsonResponse);
            log.info(formattedResponse);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            log.info("Exception Occurred: "+ex.getMessage());
            throw ex;
        }

        return response;
    }

}
