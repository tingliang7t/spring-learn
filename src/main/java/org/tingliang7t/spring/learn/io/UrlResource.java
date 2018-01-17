package org.tingliang7t.spring.learn.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url){
        this.url = url;
    }

    @Override
    public InputStream getInputStream()throws IOException{
        URLConnection connection = url.openConnection();
        connection.connect();
        return connection.getInputStream();
    }
}
