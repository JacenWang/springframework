package com.jacen.study.v4.context;

public interface BeanDefinitionReador {

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);
}
