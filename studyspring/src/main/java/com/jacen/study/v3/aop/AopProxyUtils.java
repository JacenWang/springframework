package com.jacen.study.v3.aop;


import com.jacen.study.v3.aop.advisor.Advisor;
import com.jacen.study.v3.aop.advisor.PointcutAdvisor;
import com.jacen.study.v3.beans.BeanFactory;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AopProxyUtils {

    public static Object applyAdvices(Object target, Method method, Object[] args, List<Advisor> matchAdvisors, Object proxy, BeanFactory beanFactory) throws Throwable {
        //
        List<Object> advices = AopProxyUtils.getShouldApplyAdvices(target.getClass(),method,matchAdvisors,beanFactory);
        if(CollectionUtils.isEmpty(advices)){
            return method.invoke(target,args);
        }else{
            //责任链执行增强
            AopAdviceChainInvocation chain = new AopAdviceChainInvocation(proxy,target,method,args,advices);
            return chain.invoke();
        }
    }

    /**
     * 获取与方法匹配的切面advices
     * @param beanClass
     * @param method
     * @param matchAdvisors
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public static List<Object> getShouldApplyAdvices(Class<?> beanClass,Method method,List<Advisor> matchAdvisors,BeanFactory beanFactory) throws Throwable {
        if(CollectionUtils.isEmpty(matchAdvisors)){
            return null;
        }
        List<Object> advices = new ArrayList<>();
        for(Advisor ad:matchAdvisors){
            if(ad instanceof PointcutAdvisor){
                if(((PointcutAdvisor) ad).getPointcut().matchMethod(method,beanClass)){
                    advices.add(beanFactory.getBean(ad.getAdviceBeanName()));
                }
            }
        }
        return advices;
    }
}
