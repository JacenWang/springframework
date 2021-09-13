package com.jacen.study.v4.context;

import java.net.MalformedURLException;

public interface ResourceLoader {

    Resource getResource(String location) throws MalformedURLException;
}
