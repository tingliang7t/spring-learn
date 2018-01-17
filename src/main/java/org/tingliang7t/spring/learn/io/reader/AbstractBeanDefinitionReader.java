package org.tingliang7t.spring.learn.io.reader;

import org.tingliang7t.spring.learn.io.ResourceLoader;
import org.tingliang7t.spring.learn.ioc.bean.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private Map<String, BeanDefinition> register;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
        this.register = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegister(){
        return register;
    }

    public ResourceLoader getResourceLoader() {
         return resourceLoader;
    }
}
