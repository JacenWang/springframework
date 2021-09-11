package com.jacen.study.v3.aop.advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface MethodInterceptor {

    Object invoke(Method method, Object[] args, Object target) throws InvocationTargetException, IllegalAccessException;
}
