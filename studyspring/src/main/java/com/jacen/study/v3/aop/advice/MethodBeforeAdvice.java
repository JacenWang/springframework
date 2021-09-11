package com.jacen.study.v3.aop.advice;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice {

    void before(Method method, Object[] args , Object object);
}
