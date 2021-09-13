package com.jacen.study.v4.context;

import com.jacen.study.v4.beans.BeanFactory;
import com.jacen.study.v4.beans.BeanPostProcessor;
import com.jacen.study.v4.beans.PreBuildBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext {

    protected BeanFactory beanFactory;

    public AbstractApplicationContext() {
        this.beanFactory = beanFactory;
        this.beanFactory = new PreBuildBeanFactory();
    }

    @Override
    public Object getBean(String name) throws Throwable {
        return beanFactory.getBean(name);
    }

    @Override
    public void registerBeanPostProcessor(BeanPostProcessor bpp) {
        beanFactory.registerBeanPostProcessor(bpp);
    }
}
