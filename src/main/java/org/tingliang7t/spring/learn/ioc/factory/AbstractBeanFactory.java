package org.tingliang7t.spring.learn.ioc.factory;

import org.tingliang7t.spring.learn.ioc.bean.BeanDefinition;
import org.tingliang7t.spring.learn.ioc.bean.BeanPostProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    private List<String> singletonBeanNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception{
        beanDefinitionMap.put(beanName, beanDefinition);
        beanDefinitionNames.add(beanName);
        singletonBeanNames.add(beanName);
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
            bean = initializeBean(bean, beanName);
        }

        return bean;
    }

    protected Object initializeBean(Object bean, String beanName) throws Exception{

        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }

        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }

        return bean;
    }

    public void preInstantiateSingletonBeans() throws Exception{

        for(String beanName : singletonBeanNames){
            getBean(beanName);
        }
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception{
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List getBeansForType(Class type) throws Exception{
        List beans = new ArrayList<Object>();

        for(String beanDefinitionName : beanDefinitionNames){
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }

        return beans;
    }

    protected abstract Object createBean(BeanDefinition beanDefinition) throws Exception;
}
