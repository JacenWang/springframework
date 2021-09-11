package com.jacen.study.v3.beans;

public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory bf);
}
