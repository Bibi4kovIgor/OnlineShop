package com.bibichkov.onlineshop.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CachedPropertiesReader {
    private final String path;

    private final Properties cachedProperties;

    public CachedPropertiesReader(String path) {
        this.path = path;
        cachedProperties = readProperties();
    }

    public Properties getCachedProperties() {
        return new Properties(cachedProperties);
    }

    private Properties readProperties() {
        try (InputStream inputStream = new FileInputStream(path)){
            cachedProperties.load(inputStream);
            return cachedProperties;
        }
        catch (IOException e){
            throw new RuntimeException("Read properties error", e);
        }
    }
}
