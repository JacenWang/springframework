package com.jacen.study.v4.context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface InputStreamSource {

    InputStream getInputStram() throws IOException;
}
