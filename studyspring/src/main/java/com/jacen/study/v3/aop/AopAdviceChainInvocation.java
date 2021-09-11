package com.jacen.study.v3.aop;


import com.jacen.study.v3.aop.advice.AfterReturningAdvice;
import com.jacen.study.v3.aop.advice.MethodBeforeAdvice;
import com.jacen.study.v3.aop.advice.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AopAdviceChainInvocation {
    private static Method invokeMethod;

    static{
        try {
            invokeMethod = AopAdviceChainInvocation.class.getMethod("invoke",null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Object proxy;
    private Object target;
    private Method method;
    private Object[] args;
    private List<Object> advices;

    public AopAdviceChainInvocation(Object proxy, Object target, Method method, Object[] args, List<Object> advices) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.args = args;
        this.advices = advices;
    }
    //责任链执行记录索引号
    private int i =0;

    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        if(i < this.advices.size()){
            Object advice = this.advices.get(i++);
            if(advice instanceof MethodBeforeAdvice){
                ((MethodBeforeAdvice) advice).before(method,args,target);
            }else if(advice instanceof MethodInterceptor){
                return ((MethodInterceptor) advice).invoke(invokeMethod,null,this);
            }else if(advice instanceof AfterReturningAdvice) {
                Object returnValue = this.invoke();
                ((AfterReturningAdvice)advice).afterReturning(returnValue, method, args, target);
                return returnValue;
            }
            return this.invoke();
        }else{
            return method.invoke(target,args);
        }
    }
}
