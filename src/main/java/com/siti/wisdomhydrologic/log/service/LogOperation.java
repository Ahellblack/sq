package com.siti.wisdomhydrologic.log.service;

import com.siti.wisdomhydrologic.log.entity.SysLog;
import com.siti.wisdomhydrologic.log.mapper.SysLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;

@Component
@Aspect
public class LogOperation{
    @Resource
    SysLogMapper sysLogMapper;
    @AfterReturning(returning="rvt",pointcut="@annotation(com.siti.wisdomhydrologic.log.service.OperateLog)")
    public void before(JoinPoint joinPoint,Object rvt) {
       Object[] args= joinPoint.getArgs();
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        if((int)rvt>0){
            sysLogMapper.insertUserOprLog( new SysLog.builder()
            .setUsername("")
            .setOperateDes("")
            .setFreshVal("")
            .setAction("")
            .setPreviousVal("")
            .build());
        }
        /*Method method = sign.getMethod();
        OperateLog annotation = method.getAnnotation(OperateLog.class);*/
        //sysLogMapper.insertUserOprLog();
    }
}
