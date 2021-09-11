package com.jacen.study.v3.aop;

import com.jacen.study.v3.aop.advisor.Advisor;
import com.jacen.study.v3.beans.BeanDefinition;
import com.jacen.study.v3.beans.BeanFactory;
import com.jacen.study.v3.beans.DefaultBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class CglibDynamicAopProxy implements AopProxy,MethodInterceptor {

    public static final Log logger = LogFactory.getLog(CglibDynamicAopProxy.class);

    private static Enhancer enhancer = new Enhancer();

    private String beanName;
    private Object target;
    private List<Advisor> matchAdvisors;
    private BeanFactory beanFactory;

    public CglibDynamicAopProxy(String beanName, Object target, List<Advisor> matchAdvisors, BeanFactory beanFactory) {
        this.beanName = beanName;
        this.target = target;
        this.matchAdvisors = matchAdvisors;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getProxy() {
        return this.getProxy(target.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        Class<?> superClass = this.target.getClass();
        enhancer.setSuperclass(superClass);
        enhancer.setInterfaces(this.getClass().getInterfaces());
        enhancer.setCallback(this);
        Constructor<?> constructor = null;
        try {
            constructor = superClass.getConstructor(new Class<?>[]{});
        } catch (NoSuchMethodException e) {
           // e.printStackTrace();
        }
        if(null != constructor){
            return enhancer.create();
        }else{
            BeanDefinition bd = ((DefaultBeanFactory)beanFactory).getBeanDefinition(beanName);
            return enhancer.create(bd.getConstructor().getParameterTypes(),bd.getConstructorArgumentRealValues());
        }
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return AopProxyUtils.applyAdvices(target,method,args,matchAdvisors,proxy,beanFactory);
    }

    public static Enhancer getEnhancer() {
        return enhancer;
    }

    public static void setEnhancer(Enhancer enhancer) {
        CglibDynamicAopProxy.enhancer = enhancer;
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

    public List<Advisor> getMatchAdvisors() {
        return matchAdvisors;
    }

    public void setMatchAdvisors(List<Advisor> matchAdvisors) {
        this.matchAdvisors = matchAdvisors;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
