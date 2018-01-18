package org.tingliang7t.spring.learn.ioc.bean;


public class BeanDefinition {

    private Object  bean;
    private Class   beanClass;
    private String  beanClassName;
    private Properties properties = new Properties();

    public void setBean(Object bean){
        this.bean = bean;
    }

    public void setBeanClass(Class beanClass){
        this.beanClass = beanClass;
    }

    public void setBeanClassName(String beanClassName){

        this.beanClassName = beanClassName;

        try{
            beanClass = Class.forName(beanClassName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void setProperties(Properties properties){
        this.properties = properties;
    }

    public Object getBean(){
        return bean;
    }

    public Class getBeanClass(){
        return beanClass;
    }

    public String getBeanClassName(){
        return beanClassName;
    }

    public Properties getProperties() {
        return properties;
    }

}
