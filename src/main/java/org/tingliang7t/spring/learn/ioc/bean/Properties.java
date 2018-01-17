package org.tingliang7t.spring.learn.ioc.bean;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Properties implements Iterable<Property>{

    private List<Property> properties;

    public Properties() {
        this.properties = new LinkedList<Property>();
    }

    public Properties(List<Property> properties){
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public Iterator<Property> iterator(){
        return properties.iterator();
    }
}
