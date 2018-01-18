package org.tingliang7t.spring.learn.io.reader.xml;

import org.tingliang7t.spring.learn.io.ResourceLoader;
import org.tingliang7t.spring.learn.io.reader.AbstractBeanDefinitionReader;
import org.tingliang7t.spring.learn.ioc.bean.BeanDefinition;
import org.tingliang7t.spring.learn.ioc.bean.BeanReference;
import org.tingliang7t.spring.learn.ioc.bean.Property;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader){
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception{

        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();

        loadBeanDefinitions(inputStream);
    }

    private void loadBeanDefinitions(InputStream inputStream) throws Exception{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);

        registerBeanDefinitions(doc);
    }

    protected void registerBeanDefinitions(Document doc){

        Element root = doc.getDocumentElement();

        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root){

        NodeList nodeList = root.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node instanceof Element){
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    protected void processBeanDefinition(Element element){

        String name = element.getAttribute("id");
        String className = element.getAttribute("class");

        BeanDefinition beanDefinition = new BeanDefinition();

        processProperty(element, beanDefinition);

        beanDefinition.setBeanClassName(className);

        getRegister().put(name, beanDefinition);
    }

    protected void processProperty(Element element, BeanDefinition beanDefinition){

        NodeList propertyNodes = element.getElementsByTagName("property");

        for(int i = 0; i<propertyNodes.getLength(); i++){

            Node node = propertyNodes.item(i);

            if (node instanceof Element){

                Element property = (Element) node;
                String name = property.getAttribute("name");
                String value = property.getAttribute("value");

                if (value != null && value.length() > 0){
                    beanDefinition.getProperties().addProperty(new Property(name, value));
                }else{
                    String ref = property.getAttribute("ref");
                    if (ref == null || ref.length() == 0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }

                    BeanReference beanRef = new BeanReference(ref);
                    beanDefinition.getProperties().addProperty(new Property(name, beanRef));
                }
            }
        }
    }
}
