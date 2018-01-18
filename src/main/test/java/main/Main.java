package main;

import org.tingliang7t.spring.learn.io.ResourceLoader;
import org.tingliang7t.spring.learn.io.reader.xml.XmlBeanDefinitionReader;
import org.tingliang7t.spring.learn.ioc.factory.AutowireBeanFactory;
import org.tingliang7t.spring.learn.ioc.factory.BeanFactory;
import service.Test2Service;


public class Main {

    public static void main(String[] args){

        String resource = "spring-context.xml";

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());

        try {
            beanDefinitionReader.loadBeanDefinitions(resource);

            BeanFactory autowireBeanFactory = new AutowireBeanFactory();
            for (String beanName : beanDefinitionReader.getRegister().keySet()) {
                autowireBeanFactory.registerBeanDefinition(beanName, beanDefinitionReader.getRegister().get(beanName));
            }

            Test2Service test2Service = (Test2Service)autowireBeanFactory.getBean("test2Service");

            test2Service.service();

        }catch (Exception e){

            e.printStackTrace();

        }
    }
}
