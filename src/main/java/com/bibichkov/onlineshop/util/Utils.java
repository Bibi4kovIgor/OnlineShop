package com.bibichkov.onlineshop.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class Utils {

    public static String getDbParamsFromProps(String propertyName) {
        Properties dbProps = new Properties();
        try {
            dbProps.load(new FileInputStream("database.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Property does not found!", e);
        }
        return dbProps.getProperty(propertyName);
    }
}
