package com.jacen.study.v1.beans;

public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException;

	BeanDefinition getBeanDefinition(String beanName);

	boolean constainsBeanDefinition(String beanName);

}
