package com.jacen.study.v3.aop;


import com.jacen.study.v3.aop.advisor.Advisor;
import com.jacen.study.v3.beans.BeanFactory;

import java.util.List;

public interface AopProxyFactory {

    AopProxy createAopProxy(Object bean, String beanName, List<Advisor> matchAdvisors, BeanFactory beanFactory) throws Throwable;

    static AopProxyFactory getDefaultAopProxyFactory(){
        return new DefaultAopProxyFactory();
    }
}
