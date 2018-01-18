package org.tingliang7t.spring.learn.context;

import org.tingliang7t.spring.learn.io.ResourceLoader;
import org.tingliang7t.spring.learn.io.reader.xml.XmlBeanDefinitionReader;
import org.tingliang7t.spring.learn.ioc.factory.AbstractBeanFactory;
import org.tingliang7t.spring.learn.ioc.factory.AutowireBeanFactory;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception{
        this(configLocation, new AutowireBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception{
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception{

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        beanDefinitionReader.loadBeanDefinitions(configLocation);

        for(String beanName : beanDefinitionReader.getRegister().keySet()){
            beanFactory.registerBeanDefinition(beanName, beanDefinitionReader.getRegister().get(beanName));
        }

        beanFactory.preInstantiateSingletons();
    }
}
