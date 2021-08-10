package com.bibichkov.onlineshop.util;

import jakarta.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class Utils {

    public static Map<String, Object> createProductsMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

    public static String getDbParamsFromProps(String propertyName) {
        Properties dbProps = new Properties();
        try {
            dbProps.load(new FileInputStream("database.properties"));
        } catch (IOException e) {
            System.out.println("Property does not found!");
            e.printStackTrace();
        }
        return dbProps.getProperty(propertyName);
    }
}
