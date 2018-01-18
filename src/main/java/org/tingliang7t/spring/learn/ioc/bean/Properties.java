package org.tingliang7t.spring.learn.ioc.bean;

import java.util.*;

public class Properties implements Iterable<Property>{

    private Set<Property> properties;

    public Properties() {
        this.properties = new HashSet<>();
    }

    public Properties(Set<Property> properties){
        this.properties = properties;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    public Iterator<Property> iterator(){
        return properties.iterator();
    }

    public void addProperty(Property property){
        properties.add(property);
    }
}
