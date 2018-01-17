package org.tingliang7t.spring.learn.ioc.factory;

import org.tingliang7t.spring.learn.ioc.bean.BeanDefinition;
import org.tingliang7t.spring.learn.ioc.bean.BeanReference;
import org.tingliang7t.spring.learn.ioc.bean.Properties;
import org.tingliang7t.spring.learn.ioc.bean.Property;

import java.lang.reflect.Field;

public class AutowireBeanFactory extends AbstractBeanFactory{

    @Override
    public Object createBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = createInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyProperties(bean, beanDefinition.getProperties());

        return bean;
    }

    protected Object createInstance(BeanDefinition beanDefinition) throws Exception{
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyProperties(Object bean, Properties properties) throws Exception{

        if (properties == null){
            return;
        }

        for(Property property : properties){
            Field field = bean.getClass().getDeclaredField(property.getName());
            field.setAccessible(true);

            Object value = property.getValue();
            if (value instanceof BeanReference){
                BeanReference beanReference = (BeanReference)value;
                field.set(bean, getBean(beanReference.getName()));
            }else{
                field.set(bean, value);
            }
        }
    }

}
