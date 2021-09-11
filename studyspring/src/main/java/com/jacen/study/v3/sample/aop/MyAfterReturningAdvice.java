package com.jacen.study.v3.sample.aop;


import com.jacen.study.v3.aop.advice.AfterReturningAdvice;

import java.lang.reflect.Method;

public class MyAfterReturningAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target){
		System.out.println(this + " 对 " + target + " 做了后置增强，得到的返回值=" + returnValue);
	}

}
