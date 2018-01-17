package org.tingliang7t.spring.learn.io;

import java.net.URL;

public class ResourceLoader {

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
