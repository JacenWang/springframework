package com.jacen.study.v4.context;

import com.jacen.study.v4.beans.BeanDefinitionRegistry;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        this.loadBeanDefinitions(new Resource[]{resource});
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        if( null != resources && resources.length > 0){
            for(Resource r : resources){
                this.parseXml(r);
            }
        }
    }

    private void parseXml(Resource r){
        // TODO xml解析，获取beanDefinition
    }
}
