package org.tingliang7t.spring.learn.ioc.factory;

import org.tingliang7t.spring.learn.ioc.bean.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception{
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName)throws Exception{

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + beanName + " is defined");
        }

        Object bean = beanDefinition.getBean();

        if (bean == null){
            bean = createBean(beanDefinition);
        }

        return bean;
    }

    protected abstract Object createBean(BeanDefinition beanDefinition) throws Exception;
}
