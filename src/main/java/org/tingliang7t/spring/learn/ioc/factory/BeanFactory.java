package org.tingliang7t.spring.learn.ioc.factory;

import org.tingliang7t.spring.learn.ioc.bean.BeanDefinition;

public interface BeanFactory {

    Object getBean(String beanName);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
