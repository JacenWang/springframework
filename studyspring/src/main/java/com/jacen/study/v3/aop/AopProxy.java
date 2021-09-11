package com.jacen.study.v3.aop;

public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
