package com.noway.cardPrintRequestDemo.framework.log;

import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.framework.applicationProperties.AppProperties;
import com.noway.cardPrintRequestDemo.framework.dto.activityLog.ActivityLogDTO;
import com.noway.cardPrintRequestDemo.framework.dto.user.UserDTO;
import com.noway.cardPrintRequestDemo.framework.service.impl.activityLog.ActivityLogImpl;
import com.noway.cardPrintRequestDemo.framework.service.impl.user.UserServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class.getName());


    @Autowired
    ActivityLogImpl activityLogImpl;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AppProperties appProperties;


    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {

    }


    @Pointcut("within(com.noway.cardPrintRequestDemo.cardPrintRequest..*)" +
            " || within(com.noway.cardPrintRequestDemo.cardPrintRequest.service..*)" +
            " || within(com.noway.cardPrintRequestDemo.cardPrintRequest.controller..*)")
    public void applicationPackagePointcut() {
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LOG.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }


    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        try {
            Map<String, Object> argsObject;
            argsObject = convertJoinPointArgsToObject(joinPoint.getArgs());
            Object result = joinPoint.proceed();
            LOG.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);

            saveActivityLogInDB(argsObject, joinPoint);

            return result;
        } catch (IllegalArgumentException e) {
            LOG.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }

    private Map<String, Object> convertJoinPointArgsToObject(Object[] args) {
        Map<String, Object> argsObject = new HashMap<>();
        for (Object arg : args) {
            if (arg instanceof CardPrintRequestDTO) {
                CardPrintRequestDTO cardPrintRequestDTO = (CardPrintRequestDTO) arg;
                argsObject.put("cardPrintRequestDTO", cardPrintRequestDTO);
            }
        }
        return argsObject;
    }

    private void saveActivityLogInDB(Map<String, Object> argsObject, JoinPoint joinPoint) {
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        activityLogDTO.setFunctionName(joinPoint.getSignature().getName());
        UserDTO userDTO = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (argsObject.containsKey("cardPrintRequestDTO")) {
            CardPrintRequestDTO cardPrintRequestDTO = (CardPrintRequestDTO) argsObject.get("cardPrintRequestDTO");
            activityLogDTO.setCardNumber(cardPrintRequestDTO.getCardPan());
        }
        if (userDTO!=null){
            activityLogDTO.setPersonnelCode("1000" + userDTO.getId());
        }
        activityLogDTO.setApplicationType(appProperties.getType());
        activityLogDTO.setIssuedDateAndTime(new Date());
        activityLogDTO.setEnabled(true);

        activityLogImpl.save(activityLogDTO);
    }
}