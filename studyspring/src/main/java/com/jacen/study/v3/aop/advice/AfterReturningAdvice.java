package com.jacen.study.v3.aop.advice;

import java.lang.reflect.Method;

public interface AfterReturningAdvice {

    void afterReturning(Object returnValue, Method method, Object[] args, Object target);
}
