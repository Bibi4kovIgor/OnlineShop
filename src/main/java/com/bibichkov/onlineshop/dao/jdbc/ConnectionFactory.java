package com.bibichkov.onlineshop.dao.jdbc;

import java.sql.Connection;
import java.util.Properties;

public class ConnectionFactory {
    private String url;
    private String username;
    private String password;

    public ConnectionFactory(Properties properties) {

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
