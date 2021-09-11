package com.jacen.study.v3.aop;

import com.jacen.study.v3.aop.advisor.Advisor;
import com.jacen.study.v3.beans.BeanFactory;

import java.util.List;

public class DefaultAopProxyFactory implements AopProxyFactory{

    @Override
    public AopProxy createAopProxy(Object bean, String beanName, List<Advisor> matchAdvisors, BeanFactory beanFactory) throws Throwable {
        if(shouldUsdJdkDynamicProxy(bean,beanName)){
            return new JdkDynamicAopProxy(beanName,bean,matchAdvisors,beanFactory);
        }else{
            return new CglibDynamicAopProxy(beanName,bean,matchAdvisors,beanFactory);
        }
    }

    public boolean shouldUsdJdkDynamicProxy(Object bean,String beanName) {
        /* 参考：根据是否实现接口，判断使用是否使用JDK */
        return false;
    }
}
