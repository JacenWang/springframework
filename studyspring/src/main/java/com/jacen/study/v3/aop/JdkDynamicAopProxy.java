package com.jacen.study.v3.aop;


import com.jacen.study.v3.aop.advisor.Advisor;
import com.jacen.study.v3.beans.BeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler{

    private static final Log logger = LogFactory.getLog(JdkDynamicAopProxy.class);

    private String beanName;
    private Object target;
    private List<Advisor> matchAgvisors;
    private BeanFactory beanFactory;

    public JdkDynamicAopProxy(String beanName, Object target, List<Advisor> matchAgvisors, BeanFactory beanFactory) {
        this.beanName = beanName;
        this.target = target;
        this.matchAgvisors = matchAgvisors;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getProxy() {
        return this.getProxy(target.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return AopProxyUtils.applyAdvices(target,method,args,matchAgvisors,proxy,beanFactory);
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public List<Advisor> getMatchAgvisors() {
        return matchAgvisors;
    }

    public void setMatchAgvisors(List<Advisor> matchAgvisors) {
        this.matchAgvisors = matchAgvisors;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
