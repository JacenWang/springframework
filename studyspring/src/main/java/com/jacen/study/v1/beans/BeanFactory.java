package com.jacen.study.v1.beans;

public interface BeanFactory {
	/**
	 * 获取bean
	 * 
	 * @param name
	 *            bean的名字
	 * @return bean 实例
	 * @throws Exception
	 */
	Object getBean(String name) throws Throwable;
}
