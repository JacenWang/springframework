package com.jacen.study.v4.beans;

public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException;

	BeanDefinition getBeanDefinition(String beanName);

	boolean containsBeanDefinition(String beanName);

}
