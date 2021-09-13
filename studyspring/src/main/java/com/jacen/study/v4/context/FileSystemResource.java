package com.jacen.study.v4.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileSystemResource implements Resource{

    private File file;

    public FileSystemResource(String fileName){
        this.file = new File(fileName);
    }

    public FileSystemResource(File file) {
        this.file = file;
    }

    @Override
    public InputStream getInputStram() throws FileNotFoundException {
        return new FileInputStream(this.file);
    }

    @Override
    public boolean exists() {
        return null == this.file? false: file.exists();
    }

    @Override
    public boolean isReadable() {
        return null ==this.file?false:file.canRead();
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public File getFile() {
        return file;
    }
}
