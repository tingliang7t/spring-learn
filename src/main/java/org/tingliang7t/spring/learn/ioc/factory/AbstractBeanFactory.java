package org.tingliang7t.spring.learn.ioc.factory;

import org.tingliang7t.spring.learn.ioc.bean.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception{
        Object bean = createBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName){
        return beanDefinitionMap.get(beanName).getBean();
    }

    protected abstract Object createBean(BeanDefinition beanDefinition) throws Exception;
}
