package com.jacen.study.v3.beans;

public interface BeanFactory{

	Object getBean(String name) throws Throwable;
	
	void registerBeanPostProcessor(BeanPostProcessor bpp);
}
