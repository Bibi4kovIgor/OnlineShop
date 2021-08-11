package com.bibichkov.onlineshop.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("SQLite connection problem", e);
        }
    }

    public void closeConnection(Connection connection) {

        if (connection == null) return;

        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Closing connection problem", e);
        }
    }
}
