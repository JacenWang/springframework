package com.jacen.study.v4.context;

import com.jacen.study.v4.beans.BeanDefinitionRegistry;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class XmlApplicationContext extends AbstractApplicationContext{

    private List<Resource> resources;

    private BeanDefinitionReador reader;

    public XmlApplicationContext(String... loaction) throws MalformedURLException {
        load(loaction);
        this.reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) this.beanFactory);
        reader.loadBeanDefinitions((Resource[])resources.toArray());
    }

    @Override
    public Resource getResource(String location) throws MalformedURLException {
       if(StringUtils.isNotBlank(location)){
           if(location.startsWith(Resource.CLASS_PATH_PREFIX)){
               return new ClassPathResource(location.substring(Resource.CLASS_PATH_PREFIX.length()));
           }else if(location.startsWith(Resource.FILE_PATH_PREFIX)){
               return new FileSystemResource(location.substring(Resource.FILE_PATH_PREFIX.length()));
           }else{
               return new UrlResource(location);
           }
       }
       return null;
    }

    private void load(String... location) throws MalformedURLException {
        if(null==resources){
            resources = new ArrayList<Resource>();
        }
        if(null != location && location.length > 0){
            for(String lo :location){
                Resource re = this.getResource(lo);
                if(null!=re){
                    resources.add(re);
                }
            }
        }
    }
}
