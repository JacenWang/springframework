package com.jacen.study.v3.aop.pointcut;

import java.lang.reflect.Method;

public interface Pointcut {

    boolean matchsClass(Class<?> targetClass);

    boolean matchMethod(Method method, Class<?> targetClass);
}
