package org.tingliang7t.spring.learn.context;

import org.tingliang7t.spring.learn.ioc.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String name) throws Exception{
        return beanFactory.getBean(name);
    }

    public abstract void refresh() throws Exception;
}

