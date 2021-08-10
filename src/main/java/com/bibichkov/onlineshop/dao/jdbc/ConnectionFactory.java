package com.bibichkov.onlineshop.dao.jdbc;

import java.sql.Connection;
import java.util.Properties;

public class ConnectionFactory {
    private final String url;
    private final String username;
    private final String password;

    public ConnectionFactory(Properties properties) {
        url = properties.getProperty("datasource.url");
        username = properties.getProperty("datasource.username");
        password = properties.getProperty("datasource.password");
    }

    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
}
