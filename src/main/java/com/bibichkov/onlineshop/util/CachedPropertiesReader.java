package com.bibichkov.onlineshop.util;

import java.util.Properties;

public class CachedPropertiesReader {
    private final String path;

    private Properties cachedProperties;

    public CachedPropertiesReader(String path) {
        this.path = path;
        cachedProperties = readProperties();
    }

    public Properties getCachedProperties() {
        return new Properties(cachedProperties);
    }

    private Properties readProperties() {
        // readLogic
        ConnectionPool connection = BasicConnectionPool.create(
                Utils.getDbParamsFromProps("datasource.driver") +
                        Utils.getDbParamsFromProps("datasource.path"),
                Utils.getDbParamsFromProps("datasource.username"),
                Utils.getDbParamsFromProps("datasource.password"));
        this.cachedProperties = null;
        return null;
    }
}
