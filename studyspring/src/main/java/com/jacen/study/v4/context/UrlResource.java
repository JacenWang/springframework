package com.jacen.study.v4.context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlResource implements Resource{
    private URL url;

    public UrlResource(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStram() throws IOException {
        if(this.url != null){
            return this.url.openStream();
        }
        return null;
    }

    @Override
    public boolean exists() {
        return null != this.url;
    }

    @Override
    public boolean isReadable() {
        return exists();
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public File getFile() {
        return null;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
